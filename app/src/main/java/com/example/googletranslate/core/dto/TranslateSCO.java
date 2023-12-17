package com.example.googletranslate.core.dto;

public class TranslateSCO {

    private String action;

    private String sourceText;

    private String sourceLang;

    private String targetLang;

    public TranslateSCO(String action, String sourceText, String sourceLang, String targetLang) {
        this.action = action;
        this.sourceText = sourceText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

    public TranslateSCO() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }
}
