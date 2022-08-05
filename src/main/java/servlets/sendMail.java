package servlets;

import database.entity.Mail;
import database.main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "sendMail", value = "/sendMail")
public class sendMail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mail m = new Mail();
        m.setFrom(
                Context.getUserDao().getByUsername(
                        String.valueOf(
                                request.getSession().getAttribute("username")
                        )
                )
        );
        m.setTo(
                Context.getUserDao().getByUsername(
                        String.valueOf(
                                request.getParameter("option_user")
                        )
                )
        );
        m.setSubject(
                String.valueOf(
                        request.getParameter("newsubject")
                )
        );
        m.setText(
                String.valueOf(
                        request.getParameter("newmessage")
                )
        );
        if(m.getText().isEmpty() || m.getSubject().isEmpty()) {
            request.getSession().setAttribute("sentSuccessfully", false);
            request.getRequestDispatcher("new.jsp").forward(request,response);
            return;
        }
        Context.getMailDao().addMail(m);
        request.getSession().setAttribute("sentSuccessfully",true);
        request.getRequestDispatcher("new.jsp").forward(request,response);
    }
}
