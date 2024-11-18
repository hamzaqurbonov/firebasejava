package com.example.firebasejava;

import java.util.ArrayList;
import java.util.List;

//public class Category {
//    private long id;
//    private String name;
//    private List<Subcategory> subcategories;
//
//    public Category(long id, String name) {
//        this.id = id;
//        this.name = name;
//        this.subcategories = new ArrayList<>();
//    }
//
//    // Getter ва Setter методлар
//    public long getId() { return id; }
//    public String getName() { return name; }
//    public List<Subcategory> getSubcategories() { return subcategories; }
//
//    public void addSubcategory(Subcategory subcategory) {
//        this.subcategories.add(subcategory);
//    }
//}

//public class Category {
//    private long id;
//    private String name;
//
//    // Конструктор, геттер ва сеттерлар
//    public Category(long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public long getId() { return id; }
//    public String getName() { return name; }
//}


public class Category {
    private int id;
    private String name;
    private List<Subcategory> subcategories;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.subcategories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }
}

