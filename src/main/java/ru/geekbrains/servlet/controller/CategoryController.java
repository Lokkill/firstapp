package ru.geekbrains.servlet.controller;

import ru.geekbrains.servlet.data.category.Category;
import ru.geekbrains.servlet.data.category.CategoryData;
import ru.geekbrains.servlet.data.product.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryData categoryData;

    private Category category;

    private List<Category> categories;

    public void getCategoryData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryData.findAll();
    }

    public List<Category> getAllCategories(){
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect-true";
    }


    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect-true";
    }

    public void deleteCategory(Product product) {
        categoryData.deleteById(product.getId());
    }

    public String saveCategory() {
        categoryData.saveOrUpdate(this.category);
        return "/category.xhtml?faces-redirect-true";
    }
}
