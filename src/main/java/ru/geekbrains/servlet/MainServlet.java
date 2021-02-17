package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet implements Servlet {
    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);
    private transient ServletConfig servletConfig;
    private final String path = getServletConfig().getServletContext().getContextPath();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request");

        servletResponse.getWriter().printf("<h1>This is a %s </h1>", getServletInfo());
        servletResponse.getWriter().printf("<ul>" +
                "<li ref='"+ path +"/main'>Главная страница</li>" +
                "<li ref='"+ path +"/catalog'>Каталог товаров</li>" +
                "<li ref='"+ path +"/product'>Товар</li>" +
                "<li ref='"+ path +"/card'>Корзина</li>" +
                "<li ref='"+ path +"/order'>Оформить заказ</li>" +
                "</ul");
    }

    @Override
    public String getServletInfo() {
        return "MainServlet";
    }

    @Override
    public void destroy() {
        logger.info("New {} request", getServletInfo());
    }
}
