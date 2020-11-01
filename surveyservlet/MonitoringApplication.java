
import java.io.IOException;
import java.sql.*;
import java.util.Timer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitoringApplication {

    public HashSet<String> l1 = new HashSet<>();

    MonitoringApplication() {

    }
    int id = 0;

    MonitoringApplication(String username) {

        ResultSet rs = null;
        try {
            Connection con1;
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay");
            PreparedStatement preparedStatement = con1.prepareStatement("Select userid from user where username= ? ");
            preparedStatement.setString(1, username);
            ResultSet result1 = preparedStatement.executeQuery();
            if (result1.next()) {
                id = result1.getInt("userid");
            }
            PreparedStatement preparedStatement1 = con1.prepareStatement("Select site_name from monitor where userid = ? ");
            preparedStatement1.setInt(1, id);
            rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                String dup = rs.getString("site_name");
                l1.add(dup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String username, String site_name) throws SQLException {
        int id = 0;
        try {
            Connection con1;
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay");
            PreparedStatement preparedStatement = con1.prepareStatement("Select userid from user where username= ? ");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("userid");
            }
            PreparedStatement preparedStatement1 = con1.prepareStatement("delete from monitor where userid= ? and site_name= ? ");
            preparedStatement1.setInt(1, id);
            preparedStatement1.setString(2, site_name);
            int k = preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement2 = con1.prepareStatement("delete from monitor_application where userid= ? and site_name= ? ");
            preparedStatement2.setInt(1, id);
            preparedStatement2.setString(2, site_name);
            int j = preparedStatement2.executeUpdate();
            l1.remove(site_name);
            con1.close();
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inserturl(String username, String site_name) {
        int result = 0;
        String stack_url = site_name;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay");
            PreparedStatement preparedStatement = con.prepareStatement("insert into monitor(userid,site_name) values((select userid from user where username = ?),?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, stack_url);
            result = preparedStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void monitor(String username) {
        Timer time = new Timer();
        Url_checking lg = new Url_checking(id);
        time.schedule(lg, 0, 120000);
    }
}
