package com.example.googletranslate.core.dto;

public class ClassRoomDTO {

    private Integer id;
    private String classCode;
    private String className;

    public ClassRoomDTO() {
    }

    public ClassRoomDTO(Integer id, String classCode, String className) {
        this.id = id;
        this.classCode = classCode;
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

