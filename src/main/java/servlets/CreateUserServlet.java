package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.User;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("get createUser");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int age = Integer.parseInt(req.getParameter("age"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (new UserService().createUser(new User(age, name, password))) {
            resp.setStatus(200);
        }
        else {
            resp.setStatus(400);
        }
    }
}
