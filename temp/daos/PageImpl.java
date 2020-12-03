package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;
import edu.northeastern.cs5200.models.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;

public class PageImpl implements PageDao {

    public PageImpl(){

    }

    private static String create_page_for_web = "insert into page (website_id, id, title, description," +
            "created, upadated, views) values (?,?,?,?,?,?,?)";
    private static String find_all_pages = "select * from page";
    private static String find_page_byid = "select * from page where id=?";
    private static String find_pages_for_web = "select * from page where website_id=?";
    private static String update_page = "update page set website_id=?, title=?, description=?," +
            "created=?, upadated=?, views=? where id=?";
    private static String delete_page = "delete from page where id=?";

    @Override
    public void createPageForWebsite(int websiteId, Page page) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(create_page_for_web);
            pstmt.setInt(1, websiteId);
            pstmt.setInt(2, page.getId());
            pstmt.setString(3, page.getTitle());
            pstmt.setString(4, page.getDescription());
            pstmt.setDate(5, page.getCreated());
            pstmt.setDate(6, page.getUpdated());
            pstmt.setInt(7, page.getViews());
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
    public Collection<Page> findAllPages() {
        Collection<Page> pgs = new ArrayList<Page>();
        try {
            Connection conn = YuConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(find_all_pages);
            while (res.next()){
                int id = res.getInt("id");
                String tit = res.getString("title");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("upadated");
                int v = res.getInt("views");
                int web_id = res.getInt("website_id");
                Page pg = new Page(id, tit, des, create, update, v);
                pg.setWebsite_id(web_id);
                pgs.add(pg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return pgs;
    }

    @Override
    public Page findPageById(int pageId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_page_byid);
            pstmt.setInt(1, pageId);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                int id = res.getInt("id");
                String tit = res.getString("title");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("upadated");
                int v = res.getInt("views");
                int web_id = res.getInt("website_id");
                Page pg = new Page(id, tit, des, create, update, v);
                pg.setWebsite_id(web_id);
                return pg;
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
    public Collection<Page> findPagesForWebsite(int websiteId) {
        Collection<Page> pgs = new ArrayList<Page>();
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_pages_for_web);
            pstmt.setInt(1, websiteId);
            ResultSet res = pstmt.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                String tit = res.getString("title");
                String des = res.getString("description");
                Date create = res.getDate("created");
                Date update = res.getDate("upadated");
                int v = res.getInt("views");
                int web_id = res.getInt("website_id");
                Page pg = new Page(id, tit, des, create, update, v);
                pg.setWebsite_id(web_id);
                pgs.add(pg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return pgs;
    }

    @Override
    public int updatePage(int pageId, Page page) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(update_page);
            pstmt.setInt(1, page.getWebsite_id());
            pstmt.setString(2, page.getTitle());
            pstmt.setString(3, page.getDescription());
            pstmt.setDate(4, page.getCreated());
            pstmt.setDate(5, page.getUpdated());
            pstmt.setInt(6, page.getViews());
            pstmt.setInt(7, pageId);
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
    public int deletePage(int pageId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_page);
            pstmt.setInt(1, pageId);
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
