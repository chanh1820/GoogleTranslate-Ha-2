package com.example.googletranslate.core.dto;

public class UnitDTO {

    private Integer id;

    private String name;

    private String title;

    public UnitDTO() {
    }

    public UnitDTO(Integer id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
