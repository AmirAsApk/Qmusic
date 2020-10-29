package com.qmusic.qmusic.ui.category;

public class Category_Model {
    String text1;
    String text2;
    String text3;
    String tag1;
    String tag2;

    public Category_Model(String text1, String text2, String text3, String tag1, String tag2) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public String getText1() {
        return text1;
    }


    public String getText2() {
        return text2;
    }


    public String getText3() {
        return text3;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }
}
