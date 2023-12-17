package com.example.googletranslate.core.dto;

public class QuestionP4DTO {

    private String id;

    private String ExampleViet;

    private String vocabularyEng;

    private String imageFile;

    private String soundFile;

    private String answerA;

    private String answerB;

    private String result;

    private String traLoi;


    public int choiceID= -1; //hỗ trợ check Id của radiogroup

    public QuestionP4DTO(String id, String vocabularyViet, String vocabularyEng, String imageFile, String soundFile, String answerA, String answerB, String result, String traLoi, int choiceID) {
        this.id = id;
        this.ExampleViet = vocabularyViet;
        this.vocabularyEng = vocabularyEng;
        this.imageFile = imageFile;
        this.soundFile = soundFile;
        this.answerA = answerA;
        this.answerB = answerB;
        this.result = result;
        this.traLoi = traLoi;
        this.choiceID = choiceID;
    }

    public QuestionP4DTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExampleViet() {
        return ExampleViet;
    }

    public void setExampleViet(String exampleViet) {
        ExampleViet = exampleViet;
    }

    public String getVocabularyEng() {
        return vocabularyEng;
    }

    public void setVocabularyEng(String vocabularyEng) {
        this.vocabularyEng = vocabularyEng;
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

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }
}
