package ru.geekbrains.servlet.listener;

import ru.geekbrains.servlet.data.category.CategoryData;
import ru.geekbrains.servlet.data.product.Product;
import ru.geekbrains.servlet.data.product.ProductData;
import ru.geekbrains.servlet.data.user.User;
import ru.geekbrains.servlet.data.user.UserData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductData products = new ProductData();
        products.saveOrUpdate(new Product("Iphone 7",
                "Old and not sensational",
                new BigDecimal(20000)));
        products.saveOrUpdate(new Product("Iphone 12",
                "New and sensational",
                new BigDecimal(120000)));
        sce.getServletContext().setAttribute("productData", products);

        UserData users = new UserData();
        users.saveOrUpdate(new User("admin", "admin"));
        sce.getServletContext().setAttribute("userData", users);

        CategoryData categoryData = new CategoryData();
        categoryData.saveOrUpdate(new ru.geekbrains.servlet.data.category.Category("IPhone's"));
        sce.getServletContext().setAttribute("categoryData", categoryData);
    }
}
