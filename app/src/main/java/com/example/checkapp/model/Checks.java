package com.example.checkapp.model;

import android.widget.CheckBox;

public class Checks {

    private String textTitleChecks;
    private Long id;
    private CheckBox checkBoxChecks;

    public Checks() {
    }

    public Checks(String textTitleChecks, String textDescriptionChecks, Long id) {
        this.textTitleChecks = textTitleChecks;

        this.id = id;

    }

    public String getTextTitleChecks() {
        return textTitleChecks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextTitleChecks(String textTitleChecks) {
        this.textTitleChecks = textTitleChecks;
    }


    public CheckBox getCheckBoxChecks() {
        return checkBoxChecks;
    }

    public void setCheckBoxChecks(CheckBox checkBoxChecks) {
        this.checkBoxChecks = checkBoxChecks;
    }
}
