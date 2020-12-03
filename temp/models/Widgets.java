package edu.northeastern.cs5200.models;

public class Widgets {
    private Page page = null;

    private int id = -1;
    private int page_id = -1;
    private String name = null;
    private int width = -1;
    private int height = -1;
    private String cssClass = null;
    private String cssStyle = null;
    private String text = null;
    private int orderr = -1;
    private String youtube_url = null;
    private boolean youtube_shareble = false;
    private boolean youtube_expandable = false;
    private String image_src = null;
    private String html_html = null;
    private int heading_size = 2;
    private type dtype = null;
    public enum type{
        YouTube, Image, Html, Heading
    }

    public Widgets() {

    }

    public Widgets(int id, String name, int width, int height, String cssStyle,
                   String cssClass, String text, int orderr){
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.cssStyle = cssStyle;
        this.cssClass = cssClass;
        this.text = text;
        this.orderr = orderr;
    }

    public Widgets(int id, String name, int width, int height, String cssStyle, String cssClass, String text, int orderr,
                   int size, String html, String src ,String url, boolean shareble, boolean expandable, type dtype){
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.cssStyle = cssStyle;
        this.cssClass = cssClass;
        this.text = text;
        this.orderr = orderr;
        this.heading_size = size;
        this.html_html = html;
        this.image_src = src;
        this.youtube_url = url;
        this.youtube_shareble = shareble;
        this.youtube_expandable = expandable;
        this.dtype = dtype;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrderr() {
        return orderr;
    }

    public void setOrderr(int orderr) {
        this.orderr = orderr;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    public boolean isYoutube_shareble() {
        return youtube_shareble;
    }

    public void setYoutube_shareble(boolean youtube_shareble) {
        this.youtube_shareble = youtube_shareble;
    }

    public boolean isYoutube_expandable() {
        return youtube_expandable;
    }

    public void setYoutube_expandable(boolean youtube_expandable) {
        this.youtube_expandable = youtube_expandable;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getHtml_html() {
        return html_html;
    }

    public void setHtml_html(String html_html) {
        this.html_html = html_html;
    }

    public int getHeading_size() {
        return heading_size;
    }

    public void setHeading_size(int heading_size) {
        this.heading_size = heading_size;
    }

    public type getDtype() {
        return dtype;
    }

    public void setDtype(type dtype) {
        this.dtype = dtype;
    }
}
