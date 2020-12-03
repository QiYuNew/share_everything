package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;
import edu.northeastern.cs5200.models.Developer;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;

public class DeveloperImpl implements DeveloperDao {

    public DeveloperImpl() {

    }

    private static String insert_person = "insert into person (id, firstname, lastname, " +
            "username, password, email, dob, address, phone) " +
            "values (?,?,?,?,?,?,?,?,?)";
    private static String insert_dev = "insert into developer (id, developer_key) " +
            "values (?,?)";
    private static String find_all_dev = "select * from person, developer " +
            "where developer.id = person.id";
    private static String find_dev_by_id = "select * from person, developer " +
            "where developer.id = person.id and developer.id = ?";
    private static String find_dev_by_username = "select * from person, developer " +
            "where developer.id = person.id and person.username = ?";
    private static String find_dev_by_Credentials = "select * from person, developer " +
            "where developer.id = person.id and person.username = ? and person.password = ?";
    private static String update_dev = "update person inner join developer on person.id = developer.id " +
            "set person.firstname=?, person.lastname=?, person.username=?, person.password=?," +
            "person.email=?, person.dob=?, person.address=?, person.phone=?," +
            "developer.developer_key=? where developer.id=?";
    private static String delete_dev = "delete from person where id = ?";

    @Override
    public void createDeveloper(Developer developer) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmtperson = conn.prepareStatement(insert_person);
            PreparedStatement pstmtdev = conn.prepareStatement(insert_dev);
            pstmtperson.setInt(1, developer.getId());
            pstmtperson.setString(2,developer.getFirstname());
            pstmtperson.setString(3,developer.getLastname());
            pstmtperson.setString(4,developer.getUsername());
            pstmtperson.setString(5,developer.getPassword());
            pstmtperson.setString(6,developer.getEmail());
            pstmtperson.setDate(7,developer.getDob());
            pstmtperson.setString(8,developer.getAddress());
            pstmtperson.setString(9,developer.getPhone());
            pstmtperson.executeUpdate();
            //
            pstmtdev.setInt(1,developer.getId());
            pstmtdev.setString(2,developer.getDeveloper_key());
            pstmtdev.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }

    @Override
    public Collection<Developer> findAllDevelopers() {
        Collection<Developer> devs = new ArrayList<Developer>();
        try {
            Connection conn = YuConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(find_all_dev);
            while (res.next()){
                int id = res.getInt("id");
                String firstn = res.getString("firstname");
                String lastn = res.getString("lastname");
                String uname = res.getString("username");
                String pw = res.getString("password");
                String email = res.getString("email");
                Date dob = res.getDate("dob");
                String add = res.getString("address");
                String ph = res.getString("phone");
                String dev_key = res.getString("developer_key");
                Developer new_dev = new Developer(dev_key, id, firstn, lastn, uname, pw, email, dob, add, ph);
                devs.add(new_dev);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return devs;
    }

    @Override
    public Developer findDeveloperByID(int developerID) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_dev_by_id);
            pstmt.setInt(1, developerID);
            ResultSet res = pstmt.executeQuery();
            if (res.next()){
                int id = res.getInt("id");
                String firstn = res.getString("firstname");
                String lastn = res.getString("lastname");
                String uname = res.getString("username");
                String pw = res.getString("password");
                String email = res.getString("email");
                Date dob = res.getDate("dob");
                String add = res.getString("address");
                String ph = res.getString("phone");
                String dev_key = res.getString("developer_key");
                return new Developer(dev_key, id, firstn, lastn, uname, pw, email, dob, add, ph);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return null;
    }

    @Override
    public Developer findDeveloperByUsername(String username) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_dev_by_username);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String firstn = res.getString("firstname");
                String lastn = res.getString("lastname");
                String uname = res.getString("username");
                String pw = res.getString("password");
                String email = res.getString("email");
                Date dob = res.getDate("dob");
                String add = res.getString("address");
                String ph = res.getString("phone");
                String dev_key = res.getString("developer_key");
                return new Developer(dev_key, id, firstn, lastn, uname, pw, email, dob, add, ph);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return null;
    }

    @Override
    public Developer findDeveloperByCredentials(String username, String password) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_dev_by_Credentials);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String firstn = res.getString("firstname");
                String lastn = res.getString("lastname");
                String uname = res.getString("username");
                String pw = res.getString("password");
                String email = res.getString("email");
                Date dob = res.getDate("dob");
                String add = res.getString("address");
                String ph = res.getString("phone");
                String dev_key = res.getString("developer_key");
                return new Developer(dev_key, id, firstn, lastn, uname, pw, email, dob, add, ph);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return null;
    }

    @Override
    public int updateDeveloper(int developerID, Developer developer) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(update_dev);
            pstmt.setString(1, developer.getFirstname());
            pstmt.setString(2, developer.getLastname());
            pstmt.setString(3, developer.getUsername());
            pstmt.setString(4, developer.getPassword());
            pstmt.setString(5, developer.getEmail());
            pstmt.setDate(6, developer.getDob());
            pstmt.setString(7, developer.getAddress());
            pstmt.setString(8, developer.getPhone());
            pstmt.setString(9, developer.getDeveloper_key());
            pstmt.setInt(10, developerID);
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return 0;
    }

    @Override
    public int deleteDeveloper(int developerID) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_dev);
            pstmt.setInt(1, developerID);
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return 0;
    }
}
