package com.example.firebasejava;

import java.util.ArrayList;
import java.util.List;

public class Subcategory {
    private long id;
    private String name;
    private List<Item> items;

    public Subcategory(long id, String name) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<>();
    }

    // Getter ва Setter методлар
    public long getId() { return id; }
    public String getName() { return name; }
    public List<Item> getItems() { return items; }

    public void addItem(Item item) {
        this.items.add(item);
    }
}

//public class Subcategory {
//    private long id;
//    private String name;
//    private long categoryId;
//
//    // Конструктор, геттер ва сеттерлар
//    public Subcategory(long id, String name, long categoryId) {
//        this.id = id;
//        this.name = name;
//        this.categoryId = categoryId;
//    }
//
//    public long getId() { return id; }
//    public String getName() { return name; }
//    public long getCategoryId() { return categoryId; }
//}
