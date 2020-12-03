package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;
import edu.northeastern.cs5200.models.Website;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;

public class WebsiteImpl implements WebsiteDao {

    public WebsiteImpl(){

    }

    private static String create_web_for_dev = "insert into website (id, name, description," +
            "created, updated, visits) values (?,?,?,?,?,?)";
    private static String find_all_web = "select * from website";
    private static String find_web_for_dev = "select * from website where developer_id=?";
    private static String find_web_byid = "select * from website where id=?";
    private static String update_web = "update website set name=?, description=?," +
            "created=?, updated=?, visits=?, developer_id=? where id=?";
    private static String delete_web = "delete from website where id=?";

    @Override
    public void createWebsiteForDeveloper(int developerId, Website website) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(create_web_for_dev);
            pstmt.setInt(1, website.getId());
            pstmt.setString(2, website.getName());
            pstmt.setString(3, website.getDescription());
            pstmt.setDate(4, website.getCreated());
            pstmt.setDate(5, website.getUpdated());
            pstmt.setInt(6, website.getVisited());
//            pstmt.setInt(7, developerId);
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
    public Collection<Website> findAllWebsites() {
        Collection<Website> webs = new ArrayList<Website>();
        try {
            Connection conn = YuConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(find_all_web);
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("updated");
                int v = res.getInt("visits");
                int dev_id = res.getInt("developer_id");
                Website new_web = new Website(id, name, des, create, update, v);
                new_web.setDeveloper_id(dev_id);
                webs.add(new_web);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return webs;
    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> webs = new ArrayList<Website>();
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_web_for_dev);
            pstmt.setInt(1, developerId);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("updated");
                int v = res.getInt("visits");
                int dev_id = res.getInt("developer_id");
                Website new_web = new Website(id, name, des, create, update, v);
                new_web.setDeveloper_id(dev_id);
                webs.add(new_web);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return webs;
    }

    @Override
    public Website findWebsiteById(int websiteId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_web_byid);
            pstmt.setInt(1, websiteId);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("updated");
                int v = res.getInt("visits");
                int dev_id = res.getInt("developer_id");
                Website new_web = new Website(id, name, des, create, update, v);
                new_web.setDeveloper_id(dev_id);
                return new_web;
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
    public int updateWebsite(int websiteId, Website website) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(update_web);
            pstmt.setString(1, website.getName());
            pstmt.setString(2, website.getDescription());
            pstmt.setDate(3, website.getCreated());
            pstmt.setDate(4, website.getUpdated());
            pstmt.setInt(5, website.getVisited());
            pstmt.setInt(6, website.getDeveloper_id());
            pstmt.setInt(7, websiteId);
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
    public int deleteWebsite(int websiteId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_web);
            pstmt.setInt(1, websiteId);
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
