package servlets;


import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String name = req.getParameter("name");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();
        if (session != null &&
                session.getAttribute("name") != null &&
                session.getAttribute("password") != null) {

            final String role = (String) session.getAttribute("role");
            moveToMenu(req, res, role);


        } else if (UserService.getInstance().validate(name, password)) {

            final String role = UserService.getInstance().getRoleByName(name);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("name", name);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, null);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {


        if (role == null) {

            req.getRequestDispatcher("login_page.jsp").forward(req, res);

        } else if (role.equals("user")) {

            req.getRequestDispatcher("/user").forward(req, res);

        } else if (role.equals("admin")) {

            req.getRequestDispatcher("/admin").forward(req, res);
        }
    }
}
