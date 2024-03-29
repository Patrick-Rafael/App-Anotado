package com.example.checkapp.model;

import android.widget.CheckBox;

import java.io.Serializable;

public class Checks implements Serializable {

    private String textTitle, textDescription, textDate;
    private Long id;


    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    public Checks() {
    }

    public Checks(String textTitleChecks, Long id) {
        this.textTitle = textTitleChecks;

        this.id = id;

    }

    public String getTextTitle() {
        return textTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }


}
