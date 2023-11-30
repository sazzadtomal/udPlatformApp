package com.udPlatform;

import java.io.Serializable;

public class Entity implements Serializable{
    private String FName;
    private String LName;

    public Entity(String FName, String LName) {
        this.FName = FName;
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }





}
