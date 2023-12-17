package com.example.googletranslate.view.doctienganh;

import com.example.googletranslate.core.dto.LanguageDTO;

public class DocNgonNguViewDTO {

    private LanguageDTO languageDTO;

    public DocNgonNguViewDTO(LanguageDTO languageDTO) {
        this.languageDTO = languageDTO;
    }

    public DocNgonNguViewDTO() {
    }

    public LanguageDTO getLanguageDTO() {
        return languageDTO;
    }

    public void setLanguageDTO(LanguageDTO languageDTO) {
        this.languageDTO = languageDTO;
    }
}
