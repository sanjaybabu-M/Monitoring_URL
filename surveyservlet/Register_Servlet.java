
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email_id = request.getParameter("email_id");
        Userlist us = new Userlist();
        int i = us.signup(username, password, email_id);
        try (PrintWriter out = response.getWriter()) {
            if (i == 1) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        }
    }
}
