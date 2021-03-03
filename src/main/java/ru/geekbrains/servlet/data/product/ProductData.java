package ru.geekbrains.servlet.data.product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductData {

    @PersistenceContext(unitName = "ds")
    private EntityManager manager;

    @Resource
    private UserTransaction transaction;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                transaction.begin();
                saveOrUpdate(new Product(null, "Iphone 7", "This is Iphone 7", new BigDecimal(25000)));
                saveOrUpdate(new Product(null, "Iphone 12", "This is Iphone 12", new BigDecimal(120000)));
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public Long countAll() {
        return manager.createNamedQuery("countAllProduct", Long.class).getSingleResult();
    }

    public List<Product> findAll() {
        return manager.createNamedQuery("findAllProduct", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return manager.find(Product.class, id);
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            manager.persist(product);
        }
        manager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        manager.createNamedQuery("deleteByIdProduct", Product.class).setParameter("id", id).executeUpdate();
    }

}
