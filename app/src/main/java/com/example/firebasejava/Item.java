package com.example.firebasejava;

public class Item {
    private long id;
    private String name;
    private String url;

    public Item(long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    // Getter ва Setter методлар
    public long getId() { return id; }
    public String getName() { return name; }
    public String getUrl() { return url; }
}
