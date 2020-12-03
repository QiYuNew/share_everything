package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriviledgeImpl implements PriviledgeDao {

    public PriviledgeImpl() {

    }

    private static String ass_web_p = "insert into website_priviledge (developer_id, website_id, priviledge) " +
            "values (?,?,?)";
    private static String ass_pg_p = "insert into page_priviledge (developer_id, page_id, priviledge) " +
            "values (?,?,?)";
    private static String delete_web_p = "delete from website_priviledge where developer_id=? and " +
            "website_id=? and priviledge=?";
    private static String deleye_pg_p = "delete from page_priviledge where developer_id=? and " +
            "page_id=? and priviledge=?";

    @Override
    public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(ass_web_p);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, websiteId);
            pstmt.setString(3, priviledge);
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
    public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(ass_pg_p);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, pageId);
            pstmt.setString(3, priviledge);
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
    public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_web_p);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, websiteId);
            pstmt.setString(3, priviledge);
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
    public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleye_pg_p);
            pstmt.setInt(1, developerId);
            pstmt.setInt(2, pageId);
            pstmt.setString(3, priviledge);
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
