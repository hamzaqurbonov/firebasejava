package com.example.firebasejava;

//public class ItemModel {
//    private String id;
//    private String url;
//
//    public ItemModel(String id, String url) {
//        this.id = id;
//        this.url = url;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//}

//public class ItemModel {
//    private long id;
//    private String name;
//    private String url;
//    private long subcategoryId;
//
//    // Конструктор, геттер ва сеттерлар
//    public ItemModel(long id, String name, String url, long subcategoryId) {
//        this.id = id;
//        this.name = name;
//        this.url = url;
//        this.subcategoryId = subcategoryId;
//    }
//
//    public long getId() { return id; }
//    public String getName() { return name; }
//    public String getUrl() { return url; }
//    public long getSubcategoryId() { return subcategoryId; }
//}

public class ItemModel {
    private long itemId;
    private String name;
    private String url;
    private long subcategoryId;

    public ItemModel(long itemId, String name, String url, long subcategoryId) {
        this.itemId = itemId;
        this.name = name;
        this.url = url;
        this.subcategoryId = subcategoryId;
    }

    // Getter ва Setter методлари
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
