package ru.geekbrains.servlet.controller;

import ru.geekbrains.servlet.data.cart.Cart;
import ru.geekbrains.servlet.data.product.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    @Inject
    private Cart cart;


    public List<Product> getAllProducts(){
        return cart.getAllProducts();
    }

    public void addToCart(Product product) {
        cart.addToCart(product);
    }

    public void deleteFromCart(Product product) {
        cart.deleteFromCart(product);
    }

//    public String openCart(){
//        return "/cart.xhtml?faces-redirect-true";
//    }
}
