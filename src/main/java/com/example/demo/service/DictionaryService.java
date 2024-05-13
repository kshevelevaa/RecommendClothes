package com.example.demo.service;

import com.example.demo.dto.DictionaryRs;
import com.example.demo.entity.enums.CategoryType;
import com.example.demo.entity.enums.NamedDictionary;
import com.example.demo.entity.enums.PressureUnit;
import com.example.demo.entity.enums.Sex;
import com.example.demo.entity.enums.TempUnit;
import com.example.demo.entity.enums.VisibilityUnit;
import com.example.demo.entity.enums.WindUnit;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictionaryService {
    private static final Map<String, NamedDictionary[]> DICTIONARIES = Map.of(
            CategoryType.CODE, CategoryType.values(),
            Sex.CODE, Sex.values(),
            WindUnit.CODE, WindUnit.values(),
            TempUnit.CODE, TempUnit.values(),
            PressureUnit.CODE, PressureUnit.values(),
            VisibilityUnit.CODE, VisibilityUnit.values()
    );

    public List<DictionaryRs> getDictionary(String code) {
        NamedDictionary[] dictionary = DICTIONARIES.get(code);
        return Arrays.stream(dictionary).map(this::createDictionaryRs)
                .collect(Collectors.toList());
    }

    private DictionaryRs createDictionaryRs(NamedDictionary namedDictionary) {
        return new DictionaryRs(namedDictionary.getName(), namedDictionary.toString());
    }
}
