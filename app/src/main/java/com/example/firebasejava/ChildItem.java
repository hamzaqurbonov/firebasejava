package com.example.firebasejava;

public class ChildItem {
    private int imageResId;
    private String text;

    public ChildItem(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

}

