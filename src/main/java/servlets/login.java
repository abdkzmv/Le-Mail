package servlets;

import database.main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginError");
        String username = request.getParameter("username");
        String password = request.getParameter("user_password");
        boolean loginError = false;
        if(Context.getUserDao().checkUsernamePassword(username,password)) {
            request.getSession().setAttribute("loginError",false);
            request.getSession().setAttribute("loggedInUser",
                    Context.getUserDao().getByUsername(username)
                    );
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("password",password);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } else {
            request.getSession().setAttribute("loginError",true);
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
