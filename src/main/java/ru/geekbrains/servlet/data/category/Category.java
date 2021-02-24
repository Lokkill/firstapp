package ru.geekbrains.servlet.data.category;

import ru.geekbrains.servlet.data.product.Product;

import java.util.ArrayList;

public class Category {
    private Long id;
    private String title;
    private ArrayList<Product> products;

    public Category(String title) {
        this.title = title;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}






