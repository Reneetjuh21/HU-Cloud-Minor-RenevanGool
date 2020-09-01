package org.renevangool.springcore.service.impl;

import org.renevangool.springcore.model.CountedString;
import org.renevangool.springcore.repository.StringRepository;
import org.renevangool.springcore.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Service
@Profile("dev")
public class StringCapsService implements StringService {

    private final StringRepository stringRepository;

    @Autowired
    public StringCapsService(StringRepository stringRepository) {
        this.stringRepository = stringRepository;
    }

    public String doEnvironmentFunction(String str) {
        return str.toUpperCase();
    }

    public int countWordsInString(String str) {
        int wordCount = this.stringRepository.getWordCount(str);

        if (wordCount > 0) {
            System.out.println("String been found in memory");
            return wordCount;
        }

        if (str == null || str.isEmpty()) {
            return 0;
        }

        StringTokenizer tokens = new StringTokenizer(str);
        this.stringRepository.add(str, tokens.countTokens());

        return tokens.countTokens();
    }
}
