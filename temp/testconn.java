package edu.northeastern.cs5200;

import edu.northeastern.cs5200.YuConnection;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.sql.*;

@RestController
public class testconn {

    @GetMapping("testconn")
    public void test() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = YuConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("show tables;");
            while(res.next()){
                String r = res.getString("Tables_in_cs5200_spring2020_qi_yu_jdbc_a3");
                System.out.println(r);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("testdis")
    public void disconntest() {
        YuConnection.closeConnection();
    }

}
