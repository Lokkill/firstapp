package ru.geekbrains.servlet.data.category;

import ru.geekbrains.servlet.data.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "findAllCategory", query = "from Category"),
        @NamedQuery(name = "countAllCategory", query = "select count(*) from Category"),
        @NamedQuery(name = "deleteByIdCategory", query = "delete from Category category where category.id = :id")
})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Product> products;

    public Category(){}

    public Category(Long id, String title) {
        this.id = id;
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
        return new ArrayList<>(products);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}






