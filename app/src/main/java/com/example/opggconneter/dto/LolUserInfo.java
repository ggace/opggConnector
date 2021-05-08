package com.example.opggconneter.dto;


public class LolUserInfo {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LolUserInfo(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public LolUserInfo(){

    }

    @Override
    public String toString() {
        return "LolUserInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
