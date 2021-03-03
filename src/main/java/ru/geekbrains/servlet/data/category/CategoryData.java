package ru.geekbrains.servlet.data.category;

import ru.geekbrains.servlet.data.product.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CategoryData {

    @PersistenceContext(unitName = "ds")
    private EntityManager manager;

    @Resource
    private UserTransaction transaction;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                transaction.begin();
                saveOrUpdate(new Category(null, "Apple"));
                saveOrUpdate(new Category(null, "Samsung"));
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public Long countAll(){
        return manager.createNamedQuery("countAllCategory", Long.class).getSingleResult();
    }

    public List<Category> findAll(){
        return manager.createNamedQuery("findAllCategory", Category.class).getResultList();
    }

    public Category findById(Long id){
        return manager.find(Category.class, id);
    }

    @Transactional
    public void saveOrUpdate(Category category){
        if (category.getId() == null){
            manager.persist(category);
        }
        manager.merge(category);
    }

    @Transactional
    public void deleteById(Long id){
        manager.createNamedQuery("deleteByIdCategory", Category.class).setParameter("id", id).executeUpdate();
    }

}
