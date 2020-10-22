
import java.util.*;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userlist extends MonitoringApplication {

    public static final String SALT = "my-salt-text";
    public String user_name;
    public String password;
    public String email_id;
    public int rand_int;

    public int signup(String username, String password, String email_id) {
        Random rand = new Random();
        rand_int = rand.nextInt(1000);
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay");
            PreparedStatement ps = con.prepareStatement("insert into user(userid,username,password,email_id) values(?,?,?,?)");
            ps.setInt(1, rand_int);
            ps.setString(2, username);
            ps.setString(3, hashedPassword);
            ps.setString(4, email_id);
            i = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        if (i == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public Boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        Boolean isAuthenticated = false;
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        String storedPasswordHash = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/surveysparrow?autoReconnect=true&useSSL=false", "root", "sanjay")) {
                PreparedStatement preparedStatement = conn.prepareStatement("select * from user where username = ? and password = ? ");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    storedPasswordHash = result.getString("password");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        if (hashedPassword.equals(storedPasswordHash)) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hash.toString();
    }
}
