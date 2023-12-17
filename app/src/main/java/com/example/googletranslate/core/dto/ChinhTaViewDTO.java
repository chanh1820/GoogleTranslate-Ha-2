package com.example.googletranslate.core.dto;

public class ChinhTaViewDTO {

    private Integer unitId;

    private Integer index;

    private Integer listSize;

    private VocabularyDTO vocabularyVO;
    public ChinhTaViewDTO(Integer index) {
        this.index = index;
    }

    public ChinhTaViewDTO() {
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public VocabularyDTO getVocabularyVO() {
        return vocabularyVO;
    }

    public void setVocabularyVO(VocabularyDTO vocabularyVO) {
        this.vocabularyVO = vocabularyVO;
    }

    public void increaseIndex(){
        index++;
    }
}
