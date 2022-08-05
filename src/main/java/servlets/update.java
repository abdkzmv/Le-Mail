package servlets;

import database.main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update", value = "/update")
public class update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("full_name");
        String username = (String) request.getSession().getAttribute("username");
        String email = request.getParameter("email");
        String password = request.getParameter("user_password");
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.getSession().setAttribute("fillBlanks",true);
            request.getRequestDispatcher("settings.jsp").forward(request,response);
            return;
        }
        else {
            Context.getUserDao().updateUser(
                    Context.getUserDao().generateUser(
                            name, username, email, password
                    )
            );
            request.getSession().setAttribute("success", true);
            request.getRequestDispatcher("settings.jsp").forward(request,response);
        }
    }
}
