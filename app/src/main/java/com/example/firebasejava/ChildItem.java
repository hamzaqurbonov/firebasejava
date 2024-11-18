package com.example.firebasejava;

public class ChildItem {
    private int imageResId;
    private String text;

    String img;

    public ChildItem( String img, String text) {
        this.imageResId = imageResId;
        this.text = text;
        this.img = img;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }
}

