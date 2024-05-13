package com.example.demo.controller;

import com.example.demo.dto.DictionaryRs;
import com.example.demo.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping("/{dictionaryCode}")
    List<DictionaryRs> getDictionary(@PathVariable String dictionaryCode) {
        return dictionaryService.getDictionary(dictionaryCode);
    }
}
