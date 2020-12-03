package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.YuConnection;
import edu.northeastern.cs5200.models.Widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;

public class WidgetImpl implements WidgetDao {

    public WidgetImpl(){

    }

    private static String create_wg_for_pg = "insert into widget (page_id, id, name, width, height," +
            "cssClass, cssStyle, text, orderr, youtube_url, youtube_shareble, youtube_expandable," +
            "image_src, html_html, heading_size, dtype) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String find_all_wg = "select * from widget";
    private static String find_wg_byid = "select * from widget where id=?";
    private static String find_wg_for_pg = "select * from widget where page_id=?";
    private static String update_wg = "update widget set page_id=?, name=?, width=?, height=?," +
            "cssClass=?, cssStyle=?, text=?, orderr=?, youtube_url=?, youtube_shareble=?, " +
            "youtube_expandable=?, image_src=?, html_html=?, heading_size=?, dtype=?" +
            " where id=?";
    private static String delete_wg = "delete from widget where id=?";

    @Override
    public void createWidgetForPage(int pageId, Widgets widget) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(create_wg_for_pg);
            pstmt.setInt(1, pageId);
            pstmt.setInt(2, widget.getId());
            pstmt.setString(3, widget.getName());
            pstmt.setInt(4, widget.getWidth());
            pstmt.setInt(5, widget.getHeight());
            pstmt.setString(6, widget.getCssClass());
            pstmt.setString(7, widget.getCssStyle());
            pstmt.setString(8, widget.getText());
            pstmt.setInt(9, widget.getOrderr());
            pstmt.setString(10, widget.getYoutube_url());
            pstmt.setBoolean(11, widget.isYoutube_shareble());
            pstmt.setBoolean(12, widget.isYoutube_expandable());
            pstmt.setString(13, widget.getImage_src());
            pstmt.setString(14, widget.getHtml_html());
            pstmt.setInt(15, widget.getHeading_size());
            pstmt.setString(16, widget.getDtype().toString());
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
    public Collection<Widgets> findAllWidgets() {
        Collection<Widgets> wgs = new ArrayList<Widgets>();
        try {
            Connection conn = YuConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(find_all_wg);
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                int w = res.getInt("width");
                int h = res.getInt("height");
                String cssC = res.getString("cssClass");
                String cssS = res.getString("cssStyle");
                String txt = res.getString("text");
                int o = res.getInt("orderr");
                String Y_url = res.getString("youtube_url");
                Boolean Y_s = res.getBoolean("youtube_shareble");
                Boolean Y_e = res.getBoolean("youtube_expandable");
                String i_src = res.getString("image_src");
                String ht = res.getString("html_html");
                int head_s = res.getInt("heading_size");
                int pg_id = res.getInt("page_id");
                Widgets.type tp = Widgets.type.valueOf(res.getString("dtype"));
                Widgets new_wg = new Widgets(id, name, w, h, cssC, cssS, txt, o, head_s, ht, i_src, Y_url, Y_s, Y_e, tp);
                new_wg.setPage_id(pg_id);
                wgs.add(new_wg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return wgs;
    }

    @Override
    public Widgets findWidgetById(int widgetId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_wg_byid);
            pstmt.setInt(1, widgetId);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                int w = res.getInt("width");
                int h = res.getInt("height");
                String cssC = res.getString("cssClass");
                String cssS = res.getString("cssStyle");
                String txt = res.getString("text");
                int o = res.getInt("orderr");
                String Y_url = res.getString("youtube_url");
                Boolean Y_s = res.getBoolean("youtube_shareble");
                Boolean Y_e = res.getBoolean("youtube_expandable");
                String i_src = res.getString("image_src");
                String ht = res.getString("html_html");
                int head_s = res.getInt("heading_size");
                int pg_id = res.getInt("page_id");
                Widgets.type tp = Widgets.type.valueOf(res.getString("dtype"));
                Widgets new_wg = new Widgets(id, name, w, h, cssC, cssS, txt, o, head_s, ht, i_src, Y_url, Y_s, Y_e, tp);
                new_wg.setPage_id(pg_id);
                return new_wg;
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
    public Collection<Widgets> findWidgetsForPage(int pageId) {
        Collection<Widgets> wgs = new ArrayList<Widgets>();
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(find_wg_for_pg);
            pstmt.setInt(1, pageId);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                int w = res.getInt("width");
                int h = res.getInt("height");
                String cssC = res.getString("cssClass");
                String cssS = res.getString("cssStyle");
                String txt = res.getString("text");
                int o = res.getInt("orderr");
                String Y_url = res.getString("youtube_url");
                Boolean Y_s = res.getBoolean("youtube_shareble");
                Boolean Y_e = res.getBoolean("youtube_expandable");
                String i_src = res.getString("image_src");
                String ht = res.getString("html_html");
                int head_s = res.getInt("heading_size");
                int pg_id = res.getInt("page_id");
                Widgets.type tp = Widgets.type.valueOf(res.getString("dtype"));
                Widgets new_wg = new Widgets(id, name, w, h, cssC, cssS, txt, o, head_s, ht, i_src, Y_url, Y_s, Y_e, tp);
                new_wg.setPage_id(pg_id);
                wgs.add(new_wg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            YuConnection.closeConnection();
        }
        return wgs;
    }

    @Override
    public int updateWidget(int widgetId, Widgets widget) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(update_wg);
            pstmt.setInt(16, widgetId);
            pstmt.setInt(1, widget.getPage_id());
            pstmt.setString(2, widget.getName());
            pstmt.setInt(3, widget.getWidth());
            pstmt.setInt(4, widget.getHeight());
            pstmt.setString(5, widget.getCssClass());
            pstmt.setString(6, widget.getCssStyle());
            pstmt.setString(7, widget.getText());
            pstmt.setInt(8, widget.getOrderr());
            pstmt.setString(9, widget.getYoutube_url());
            pstmt.setBoolean(10, widget.isYoutube_shareble());
            pstmt.setBoolean(11, widget.isYoutube_expandable());
            pstmt.setString(12, widget.getImage_src());
            pstmt.setString(13, widget.getHtml_html());
            pstmt.setInt(14, widget.getHeading_size());
            pstmt.setString(15, widget.getDtype().toString());
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
    public int deleteWidget(int widgetId) {
        try {
            Connection conn = YuConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(delete_wg);
            pstmt.setInt(1, widgetId);
            return  pstmt.executeUpdate();
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
