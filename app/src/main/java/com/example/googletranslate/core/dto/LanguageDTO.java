package com.example.googletranslate.core.dto;

import java.util.Locale;

public class LanguageDTO {

    private Integer id;

    private String name;

    private String languageCode;

    private String localeCode;

    private Integer drawableId;

    public LanguageDTO(Integer id, String name, String languageCode, Integer drawableId) {
        this.id = id;
        this.name = name;
        this.languageCode = languageCode;
        this.drawableId = drawableId;
    }

    public LanguageDTO(Integer id, String name, String languageCode, String localeCode, Integer drawableId) {
        this.id = id;
        this.name = name;
        this.languageCode = languageCode;
        this.localeCode = localeCode;
        this.drawableId = drawableId;
    }

    public LanguageDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Integer getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}
