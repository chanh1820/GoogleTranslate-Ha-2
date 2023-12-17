package com.example.googletranslate.core.dto;

public class ManhGhepDTO {
    private String content;

    private String result;

    public ManhGhepDTO(String content, String result) {
        this.content = content;
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
