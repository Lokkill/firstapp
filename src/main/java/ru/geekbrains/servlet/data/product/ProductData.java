package ru.geekbrains.servlet.data.product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductData {
    private static Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private AtomicLong atomicLong = new AtomicLong(0);

    public List<Product> findAll(){
        return new ArrayList<>(productMap.values());
    }

    public Product findById(Long id){
        return productMap.get(id);
    }

    public void saveOrUpdate(Product product){
        if (product.getId() == null){
            Long id = atomicLong.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    public void deleteById(Long id){
        productMap.remove(id);
    }

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Product("Iphone 7",
                "Old and not sensational",
                new BigDecimal(20000)));
        this.saveOrUpdate(new Product("Iphone 12",
                "New and sensational",
                new BigDecimal(120000)));
    }
}
