package org.renevangool.springcore.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Scope("singleton")
public class StringRepository {
    private Map<String, Integer> storedStrings;

    public StringRepository() {
        this.storedStrings = new HashMap<>();
    }

    public void add(String str, Integer wordCount) {
        this.storedStrings.put(str, wordCount);
        System.out.println(storedStrings.containsKey(str));
    }

    public Map<String, Integer> getStoredStrings() {
        return this.storedStrings;
    }

    public Integer getWordCount(String str) {
        Integer returnValue = this.storedStrings.get(str);

        if (returnValue == null) {
            return 0;
        }

        return this.storedStrings.get(str);
    }
}
