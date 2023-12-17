package com.example.googletranslate.core.dto;

import java.io.Serializable;

public class QuestionDTO implements Serializable {
    private  int _id;
    private  String question;
    private  String answerA;
    private  String answerB;
    private  String answerC;
    private  String answerD;
    private  String result;
    private  String subject;
    private  String image;
    private String traloi;
    public int choiceID= -1; //hỗ trợ check Id của radiogroup

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public QuestionDTO(int _id, String question, String answerA, String answerB, String answerC, String answerD, String result, String subject, String image, String traloi) {
        this._id = _id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.result = result;
        this.subject = subject;
        this.image = image;
        this.traloi= traloi;
    }

    public QuestionDTO(String question, String answerA, String answerB, String answerC, String answerD, String result) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.result = result;
    }

    public QuestionDTO() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }
}
