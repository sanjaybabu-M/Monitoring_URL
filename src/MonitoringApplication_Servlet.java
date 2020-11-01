
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MonitoringApplication_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            String site_name = request.getParameter("site_name");
            MonitoringApplication ma = new MonitoringApplication();
            ma.inserturl(username, site_name);
            response.sendRedirect("AddURL.jsp");
        }
    }
}
