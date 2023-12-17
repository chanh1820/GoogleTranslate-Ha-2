package com.example.googletranslate.core.dto;

public class VocabularyDTO {

    private Integer id;

    private String vocabularyViet;

    private String vocabularyEng;

    private String spell;

    private String wordType;

    private String exampleViet;

    private String exampleEng;

    private String imageFile;

    private String soundFile;

    private Integer unitId;

    public VocabularyDTO() {
    }

    public VocabularyDTO(Integer id, String vocabularyViet, String vocabularyEng, String spell, String wordType, String exampleViet, String exampleEng, String imageFile, String soundFile, Integer unitId) {
        this.id = id;
        this.vocabularyViet = vocabularyViet;
        this.vocabularyEng = vocabularyEng;
        this.spell = spell;
        this.wordType = wordType;
        this.exampleViet = exampleViet;
        this.exampleEng = exampleEng;
        this.imageFile = imageFile;
        this.soundFile = soundFile;
        this.unitId = unitId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVocabularyViet() {
        return vocabularyViet;
    }

    public void setVocabularyViet(String vocabularyViet) {
        this.vocabularyViet = vocabularyViet;
    }

    public String getVocabularyEng() {
        return vocabularyEng;
    }

    public void setVocabularyEng(String vocabularyEng) {
        this.vocabularyEng = vocabularyEng;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getExampleViet() {
        return exampleViet;
    }

    public void setExampleViet(String exampleViet) {
        this.exampleViet = exampleViet;
    }

    public String getExampleEng() {
        return exampleEng;
    }

    public void setExampleEng(String exampleEng) {
        this.exampleEng = exampleEng;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }
}
