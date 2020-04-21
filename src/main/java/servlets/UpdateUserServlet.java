package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("get updateUser");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int age = Integer.parseInt(req.getParameter("age"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (UserService.getInstance().updateUser(new User(age, name, password, role))) {
            resp.setStatus(200);
        }
        else {
            resp.setStatus(400);
        }
        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
