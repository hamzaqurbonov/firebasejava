package com.example.firebasejava;

public class ChildItem {
    private int imageResId;
    private String text;

    String img;
     int categoryId;

    public ChildItem( String img, String text, int categoryId) {
        this.imageResId = imageResId;
        this.text = text;
        this.img = img;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }
}

