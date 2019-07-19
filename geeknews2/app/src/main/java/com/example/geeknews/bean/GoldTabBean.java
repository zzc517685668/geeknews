package com.example.geeknews.bean;

import java.io.Serializable;

public class GoldTabBean implements Serializable {
    private String titles;
    private boolean isSelected;

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GoldTabBean(String titles, boolean isSelected) {
        this.titles = titles;
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "GoldTabBean{" +
                "titles='" + titles + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
