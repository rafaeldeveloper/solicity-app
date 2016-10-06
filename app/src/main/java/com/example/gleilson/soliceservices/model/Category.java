package com.example.gleilson.soliceservices.model;

/**
 * Created by gleilson on 14/09/16.
 */
public class Category {

    private String name;
    private String url_image;
    private int qty;

    public Category(String name, String url_image, int qty) {
        this.name = name;
        this.url_image = url_image;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return url_image;
    }

    public void setUrlImage(String url_image) {
        this.url_image = url_image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
