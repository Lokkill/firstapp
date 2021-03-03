package ru.geekbrains.servlet.data.product;

import ru.geekbrains.servlet.data.category.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "findAllProduct", query = "from Product"),
        @NamedQuery(name = "countAllProduct", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteByIdProduct", query = "delete from Product p where p.id = :id")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255, nullable = false, updatable = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", scale = 2, precision = 10)
    private BigDecimal price;
    @Column(name = "count")
    private int count;
    @Column(name = "active")
    private boolean active;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public Product copy(Product product) {
        Product copyProduct = new Product();
        copyProduct.setName(product.name);
        copyProduct.setDescription(product.description);
        copyProduct.setPrice(product.price);
        return copyProduct;
    }
}
