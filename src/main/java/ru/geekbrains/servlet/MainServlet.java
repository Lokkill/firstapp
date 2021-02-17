package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet",urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New request");

        resp.getWriter().printf("<h1>This is a %s </h1>", getServletInfo());
        resp.getWriter().println("<ul>");
        resp.getWriter().println("<li> <a href='" + getServletContext().getContextPath() + "/main'>Главная страница</a></li>");
        resp.getWriter().println("<li> <a href='" + getServletContext().getContextPath() + "/catalog'>Каталог товаров</a></li>");
        resp.getWriter().println("<li> <a href='" + getServletContext().getContextPath() + "/product'>Товар</a></li>");
        resp.getWriter().println("<li> <a href='" + getServletContext().getContextPath() + "/cart'>Корзина</a></li>");
        resp.getWriter().println("<li> <a href='" + getServletContext().getContextPath() + "/order'>Оформить заказ</a></li>");
        resp.getWriter().println("</ul>");

    }

    @Override
    public String getServletInfo() {
        return "MainServlet";
    }
}
