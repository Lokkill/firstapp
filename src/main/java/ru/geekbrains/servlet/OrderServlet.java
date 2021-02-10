package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);
    private final String path = getServletContext().getContextPath();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New {} request", this.getServletName());
        resp.getWriter().printf("<h1>This is a %s </h1>", getServletInfo());

        resp.getWriter().printf("<ul>" +
                "<li ref='"+ path +"/main'>Главная страница</li>" +
                "<li ref='"+ path +"/catalog'>Каталог товаров</li>" +
                "<li ref='"+ path +"/product'>Товар</li>" +
                "<li ref='"+ path +"/card'>Корзина</li>" +
                "<li ref='"+ path +"/order'>Оформить заказ</li>" +
                "</ul");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
