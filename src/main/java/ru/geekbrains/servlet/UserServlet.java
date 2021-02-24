package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.data.user.User;
import ru.geekbrains.servlet.data.user.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private UserData userData;

    @Override
    public void init() throws ServletException {
        this.userData = (UserData) getServletContext().getAttribute("userData");
        if (userData == null) {
            throw new ServletException("Users not initialize error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userData.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            User user = checkCorrectData(req, resp);
            if (user == null){
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")){
            User user = checkCorrectData(req, resp);
            if (user == null){
                return;
            }
            userData.deleteById(user.getId());
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")){
            req.setAttribute("user", new User("", ""));
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        }

    }

    private User checkCorrectData(HttpServletRequest req, HttpServletResponse resp){
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            return null;
        }
        User user = userData.findById(id);
        if (user == null) {
            resp.setStatus(404);
            return null;
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }

        User user = new User(req.getParameter("username"), req.getParameter("password"));
        user.setId(id);
        userData.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

    @Override
    public String getServletInfo() {
        return "UserServlet";
    }

}
