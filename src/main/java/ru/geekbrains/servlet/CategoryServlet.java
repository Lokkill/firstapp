package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.data.category.Category;
import ru.geekbrains.servlet.data.category.CategoryData;
import ru.geekbrains.servlet.data.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category/*")
public class CategoryServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private CategoryData categoryData;

    @Override
    public void init() throws ServletException {
        this.categoryData = (CategoryData) getServletContext().getAttribute("categoryData");
        if (categoryData == null) {
            throw new ServletException("Users not initialize error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categories", categoryData.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            Category category = checkCorrectData(req, resp);
            if (category == null){
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")){
            Category category = checkCorrectData(req, resp);
            if (category == null){
                return;
            }
            categoryData.deleteById(category.getId());
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")){
            req.setAttribute("user", new User("", ""));
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        }

    }

    private Category checkCorrectData(HttpServletRequest req, HttpServletResponse resp){
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            return null;
        }
        Category category = categoryData.findById(id);
        if (category == null) {
            resp.setStatus(404);
            return null;
        }
        return category;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }

        Category category = new Category(req.getParameter("title"));
        category.setId(id);
        categoryData.saveOrUpdate(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/category");
    }

    @Override
    public String getServletInfo() {
        return "CategoryServlet";
    }
}
