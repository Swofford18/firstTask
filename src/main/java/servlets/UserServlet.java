package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("role") != null && session.getAttribute("role").equals("user")) {
            List<User> list = UserService.getInstance().getAllUsers();
            req.setAttribute("users", list);

            req.getRequestDispatcher("user_page.jsp").forward(req, resp);
        }
        else {

            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("role") != null && session.getAttribute("role").equals("user")) {
            List<User> list = UserService.getInstance().getAllUsers();
            req.setAttribute("users", list);

            req.getRequestDispatcher("user_page.jsp").forward(req, resp);
        }
        else {

            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}