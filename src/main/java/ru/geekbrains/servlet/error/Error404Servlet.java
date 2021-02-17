package ru.geekbrains.servlet.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Error404Servlet", urlPatterns = "/error_404")
public class Error404Servlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(Error404Servlet.class);
    private final String path = getServletContext().getContextPath();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New {} request", this.getServletName());
        resp.getWriter().println("<h1>Error 404 </h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
