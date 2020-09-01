package org.renevangool.springcore.model;

import org.springframework.beans.factory.annotation.Value;

public class CountedString {

    private String str;
    private int wordCount;

    @Value("#{wordCount > 0}")
    private boolean wordCountHigherThanZero;

    @Value("#{str <= ''}")
    private boolean emptyOrNull;

    public CountedString(
            String str,
            Integer wordCount
    ) {
        this.str = str;
        this.wordCount = wordCount;
    }


    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public boolean isWordCountHigherThanZero() {
        return this.wordCountHigherThanZero;
    }

    public boolean isEmptyOrNull() {
        return this.emptyOrNull;
    }
}
