package edu.northeastern.cs5200.models;

import java.util.*;

public class Priviledge {
    private int developer_id = -1;
    private int website_id = -1;
    private int page_id = -1;
    private String priviledge = null;

    private Developer developer;
    private Website website;
    private Page page;

    public Priviledge() {

    }

    public int getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }

    public int getWebsite_id() {
        return website_id;
    }

    public void setWebsite_id(int website_id) {
        this.website_id = website_id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public String getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(String priviledge) {
        this.priviledge = priviledge;
    }
}
