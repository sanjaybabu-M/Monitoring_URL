
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Userlist us = new Userlist();
            boolean i = false;
            try {
                i = us.login(username, password);
            } catch (SQLException ex) {
                Logger.getLogger(Login_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (i == true) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                MonitoringApplication ma = new MonitoringApplication(username);
                ma.monitor(username);
                response.sendRedirect("AddURL.jsp");

            } else {
                response.sendRedirect("Login.jsp");
            }
        }
    }
}
