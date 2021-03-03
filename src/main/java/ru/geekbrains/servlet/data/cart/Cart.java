package ru.geekbrains.servlet.data.cart;

import ru.geekbrains.servlet.data.product.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class Cart implements Serializable {
    private Map<Long, Product> productMap = new HashMap<>();
    private Product productCart;

    private AtomicLong atomicLong = new AtomicLong(0);

    public List<Product> getAllProducts(){
        return new ArrayList<>(productMap.values());
    }

    public void addToCart(Product product) {
        productCart = product.copy(product);
        Long id = atomicLong.incrementAndGet();
        productCart.setId(id);
        productMap.put(id, productCart);
    }

    public void deleteFromCart(Product product) {
        productMap.remove(product.getId());
    }

}
