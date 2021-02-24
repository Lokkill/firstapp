package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.data.product.Product;
import ru.geekbrains.servlet.data.product.ProductData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ProductServlet", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductData productData;

    @Override
    public void init() throws ServletException {
        this.productData = (ProductData) getServletContext().getAttribute("productData");
        if (productData == null) {
            throw new ServletException("Products not initialize error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productData.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            Product product = checkCorrectData(req, resp);
            if (product == null){
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")){
            Product product = checkCorrectData(req, resp);
            if (product == null){
                return;
            }
            productData.deleteById(product.getId());
            resp.sendRedirect(getServletContext().getContextPath() + "/product");
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")){
            req.setAttribute("product", new Product("", "", new BigDecimal(0)));
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        }

    }

    private Product checkCorrectData(HttpServletRequest req, HttpServletResponse resp){
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            return null;
        }
        Product product = productData.findById(id);
        if (product == null) {
            resp.setStatus(404);
            return null;
        }
        return product;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;
        BigDecimal price;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }

        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }

        Product product = new Product(req.getParameter("name"), req.getParameter("description"), price);
        product.setId(id);
        productData.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }

    @Override
    public String getServletInfo() {
        return "ProductServlet";
    }
}
