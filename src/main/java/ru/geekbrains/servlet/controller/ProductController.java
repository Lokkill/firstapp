package ru.geekbrains.servlet.controller;

import ru.geekbrains.servlet.data.product.Product;
import ru.geekbrains.servlet.data.product.ProductData;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
    @Inject
    private ProductData productData;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts() {
        return productData.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public void deleteProduct(Product product) {
        productData.deleteById(product.getId());
    }

    public String saveProduct() {
        productData.saveOrUpdate(this.product);
        return "/product.xhtml?faces-redirect-true";
    }
}
