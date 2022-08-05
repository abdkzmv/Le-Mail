package servlets;

import database.main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("fillBlanks");
        request.getSession().removeAttribute("problemExists");
        String name = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("user_password");
        boolean problemExists = false;
        if((name==null || username==null || email==null || password==null) ||
                (name.isEmpty()||username.isEmpty()||email.isEmpty()||password.isEmpty())){
            request.getSession().setAttribute("fillBlanks",true);
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }
        if(Context.getUserDao().isUsernameAvailable(username)) {
            problemExists = false;
            request.getSession().setAttribute("problemExists", problemExists);
            Context.getUserDao().addUser(
                    Context.getUserDao().generateUser(name,username,email,password)
            );
            request.getRequestDispatcher("register.jsp").forward(request,response);
        } else {
            problemExists = true;
            request.getSession().setAttribute("problemExists", problemExists);
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }
}
