
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete_Servlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String s = String.valueOf(session.getAttribute("username"));
            String sid = request.getParameter("site_name");
            MonitoringApplication ma = new MonitoringApplication(s);
            ma.delete(s, sid);
            response.sendRedirect("Tablelist.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
