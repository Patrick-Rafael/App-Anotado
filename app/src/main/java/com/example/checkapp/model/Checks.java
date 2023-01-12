package com.example.checkapp.model;

import android.widget.CheckBox;

public class Checks {

    private String textTitleChecks, textDescriptionChecks;
    private CheckBox checkBoxChecks;

    public Checks() {
    }

    public Checks(String textTitleChecks, String textDescriptionChecks) {
        this.textTitleChecks = textTitleChecks;
        this.textDescriptionChecks = textDescriptionChecks;

    }

    public String getTextTitleChecks() {
        return textTitleChecks;
    }

    public void setTextTitleChecks(String textTitleChecks) {
        this.textTitleChecks = textTitleChecks;
    }

    public String getTextDescriptionChecks() {
        return textDescriptionChecks;
    }

    public void setTextDescriptionChecks(String textDescriptionChecks) {
        this.textDescriptionChecks = textDescriptionChecks;
    }

    public CheckBox getCheckBoxChecks() {
        return checkBoxChecks;
    }

    public void setCheckBoxChecks(CheckBox checkBoxChecks) {
        this.checkBoxChecks = checkBoxChecks;
    }
}
