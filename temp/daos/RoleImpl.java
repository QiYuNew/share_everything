package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;

import java.sql.*;

public class RoleImpl implements RoleDao {

    public RoleImpl() {

    }

    private static String ass_web_role = "insert into website_role (developer_id, website_id, role) " +
            "values (?,?,?)";
    private static String ass_pg_role = "insert into page_role (developer_id, page_id, role) " +
            "values (?,?,?)";
    private static String delete_web_role = "delete from website_role where developer_id=? and " +
            "website_id=? and role=?";
    private static String delete_pg_role = "delete from page_role where developer_id=? and " +
            "page_id=? and role=?";


    @Override
    public void assignWebsiteRole(int developerId, int websiteId, String role) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(ass_web_role);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, websiteId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }

    @Override
    public void assignPageRole(int developerId, int pageId, String role) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(ass_pg_role);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, pageId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }

    @Override
    public void deleteWebsiteRole(int developerId, int websiteId, String role) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_web_role);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, websiteId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }

    @Override
    public void deletePageRole(int developerId, int pageId, String role) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_pg_role);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, pageId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
    }
}
