package database.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mailservice";
            String user = "root";
            String password = "12345678";
            Connection c = DriverManager.getConnection(url,user,password);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
