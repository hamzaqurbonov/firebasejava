package com.example.firebasejava;

public class ItemModel {
    private long itemId;
    private String id, url, img, name, old;
    private long subcategoryId;

    public ItemModel(long itemId, String id, String url, String img, String name, String old,long subcategoryId) {
        this.itemId = itemId;
        this.id = id;
        this.url = url;
        this.img = img;
        this.name = name;
        this.old = old;
        this.subcategoryId = subcategoryId;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getOld() {
        return old;
    }

    public long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

}
