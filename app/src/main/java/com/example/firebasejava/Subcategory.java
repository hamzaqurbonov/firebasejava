package com.example.firebasejava;

import java.util.ArrayList;
import java.util.List;


public class Subcategory {
    private int id;
    private String name;
    private String img;
    private int categoryId;

    public Subcategory(int id, String name, String img, int categoryId) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getCategoryId() {
        return categoryId;
    }

}

