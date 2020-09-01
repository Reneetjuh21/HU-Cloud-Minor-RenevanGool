package org.renevangool.springcore.service.impl;

import org.renevangool.springcore.model.CountedString;
import org.renevangool.springcore.repository.StringRepository;
import org.renevangool.springcore.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Service
@Profile("prod")
public class StringReverseService implements StringService {

    private StringRepository stringRepository;

    @Autowired
    public StringReverseService(StringRepository stringRepository) {
        this.stringRepository = stringRepository;
    }

    public String doEnvironmentFunction(String str) {
        StringBuilder revertedString = new StringBuilder();

        for(int i = str.length() - 1; i >= 0; i--)
        {
            revertedString.append(str.charAt(i));
        }

        return revertedString.toString();
    }

    public int countWordsInString(String str) {
        CountedString countedString = new CountedString(str, this.stringRepository.getWordCount(str));

        if (countedString.isEmptyOrNull()) {
            return 0;
        }

        if (countedString.isWordCountHigherThanZero()) {
            System.out.println("String been found in memory");
            return countedString.getWordCount();
        }

        StringTokenizer tokens = new StringTokenizer(str);
        countedString.setWordCount(tokens.countTokens());

        this.stringRepository.add(countedString.getStr(), countedString.getWordCount());

        return countedString.getWordCount();
    }
}
