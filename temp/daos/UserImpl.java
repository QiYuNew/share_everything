package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;
import edu.northeastern.cs5200.models.User;

import java.sql.*;

public class UserImpl implements UserDao {

    public UserImpl() {

    }

    private static String insert_person = "insert into person (id, firstname, lastname, " +
            "username, password, email, dob, address, phone) " +
            "values (?,?,?,?,?,?,?,?,?)";
    private static String insert_user = "insert into user (id, useragreement) " +
            "values (?,?)";

    @Override
    public void createUser(User user) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmtperson = conn.prepareStatement(insert_person);
            PreparedStatement pstmtuser = conn.prepareStatement(insert_user);
            pstmtperson.setInt(1, user.getId());
            pstmtperson.setString(2, user.getFirstname());
            pstmtperson.setString(3, user.getLastname());
            pstmtperson.setString(4, user.getUsername());
            pstmtperson.setString(5, user.getPassword());
            pstmtperson.setString(6, user.getEmail());
            pstmtperson.setDate(7, user.getDob());
            pstmtperson.setString(8, user.getAddress());
            pstmtperson.setString(9, user.getPhone());
            pstmtperson.executeUpdate();
            //
            pstmtuser.setInt(1, user.getId());
            pstmtuser.setBoolean(2, user.isUseragreement());
            pstmtuser.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }

}
