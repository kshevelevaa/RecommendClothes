package com.example.demo.service;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.JwtResponseDto;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final long expirationNumber; // default 86400000, is one day

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration-time}") long expirationNumber) {
        this.userRepository = userRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.passwordEncoder = passwordEncoder;
        this.expirationNumber = expirationNumber;
    }

    private String getTokenForUser(UserDetails user) {
        return Jwts.builder()
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationNumber))
                .claim("username", user.getUsername())
                .compact();
    }

    private Jws<Claims> verifyTokenAndGetJws(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }

    public void authorizeUserByToken(String token) {
        String username = verifyTokenAndGetJws(token).getBody().get("username", String.class);

        UserDetails user = (UserDetails) userRepository.findByUsername(username);

        if (user == null) {
            String message = "User with username %s in claims of JWT not found";
//            log.error(String.format(message, username));
            throw new RuntimeException(String.format(message));
        }

        UsernamePasswordAuthenticationToken passwordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
    }

    public JwtResponseDto getJwtDtoByAuthRequest(AuthRequestDto authRequestDto) {
        UserDetails user = (UserDetails) userRepository.findByUsername(authRequestDto.username());

        String authPassword = authRequestDto.password();

        if (passwordEncoder.matches(authPassword, user.getPassword())) {
            String token = getTokenForUser(user);

            Jws<Claims> jws =
                    Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            ZoneId zoneId = ZoneId.systemDefault();
            OffsetDateTime issuedAt =
                    OffsetDateTime.ofInstant(jws.getBody().getIssuedAt().toInstant(), zoneId);
            OffsetDateTime expiration =
                    OffsetDateTime.ofInstant(jws.getBody().getExpiration().toInstant(), zoneId);

            return new JwtResponseDto(token, issuedAt, expiration);
        } else {
            throw new RuntimeException("Authentication error!");
        }
    }
}
