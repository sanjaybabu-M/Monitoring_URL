
import java.io.IOException;
import java.sql.*;
import java.util.TimerTask;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

public class Url_checking extends TimerTask {

    int pub_id;
    List<String> l2 = new ArrayList<>();

    Url_checking(List<String> l1, int id) {
        l2.addAll(l1);
        pub_id = id;
    }
    Url_checking()
    {
        
    }

    public void urldelete(String site_name) {
        int y = l2.indexOf("site_name");
        l2.remove(y);
        System.out.println("del : "+l2);
    }

    @Override
    public void run() {
        System.out.println("l2 : "+l2);
        System.out.println("");
        String test = "";
        for (int i = 0; i < l2.size(); i++) {

            test = l2.get(i);
            System.out.println("TEST URL : " + test);
            boolean check = true;
            String suc_staus = "SUCCESS";
            String fail_status = "FAILURE";
            long start = 0;
            long finish = 0;
            try {
                Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp1);
                URL url = (URL) new URL(test.trim());
                HttpURLConnection httpUrlConnect = (HttpURLConnection) url.openConnection();
                httpUrlConnect.setConnectTimeout(5000);
                start = System.currentTimeMillis();
                httpUrlConnect.connect();
                if (httpUrlConnect.getResponseCode() == 200) {
                    finish = System.currentTimeMillis();
                    System.out.println(test + " - " + httpUrlConnect.getResponseMessage() + " took " + (finish - start) + " Milli Seconds.");
                } else {
                    check = false;
                }
                if (httpUrlConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                    check = false;
                    System.out.println(test + " - " + httpUrlConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            long res = finish - start;
            System.out.println("Total Time for page load - " + (finish - start));
            double res1 = (double) res;
            res1 = res1 / 1000.0;
            String status = "";
            if (res1 > 0.05 || res1 < 0) {
                if (res1 < 0 || check == false) {
                    res1 = 0;
                    status = fail_status;
                    System.out.println("STATUS : " + fail_status);
                } else {
                    status = suc_staus;
                    System.out.println("STATUS : " + suc_staus);
                }
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2;
                con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay");
                PreparedStatement ps = con2.prepareStatement("insert into monitor_application(userid,site_name,status,milliseconds,seconds) values(?,?,?,?,?)");
                ps.setInt(1, pub_id);
                ps.setString(2, test);
                ps.setString(3, status);
                ps.setLong(4, res);
                ps.setDouble(5, res1);
                int j = ps.executeUpdate();
                ps = con2.prepareStatement("update monitor set last_updated=? where site_name=?");
                Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
                ps.setTimestamp(1, timestamp2);
                ps.setString(2, test);
                int k = ps.executeUpdate();
                System.out.println("");
                System.out.println("");
                con2.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }
}
