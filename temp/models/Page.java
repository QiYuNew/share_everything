package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.*;

public class Page {
    private Website website = null;

    private int id = -1;
    private int website_id = -1;
    private String title = null;
    private String description = null;
    private Date created = null;
    private Date updated = null;
    private int views = -1;

    private Collection<Widgets> widgets;
    public void addwidget(Widgets widget){
        this.widgets.add(widget);
    };
    public void removewidget(Widgets widget){
        this.widgets.remove(widget);
    };

    private Collection<Role> roles;
    public void addrole(Role role){
        this.roles.add(role);
    };
    public void removerole(Role role){
        this.roles.remove(role);
    };

    private Collection<Priviledge> priviledges;
    public void addpriviledge(Priviledge priviledge){
        this.priviledges.add(priviledge);
    };
    public void removepriviledge(Priviledge priviledge){
        this.priviledges.remove(priviledge);
    };


    public Page() {

    }

    public Page(int id, String title, String description, Date created, Date updated,
                int views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.views = views;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebsite_id() {
        return website_id;
    }

    public void setWebsite_id(int website_id) {
        this.website_id = website_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Collection<Widgets> getWidgets() {
        return widgets;
    }

    public void setWidgets(Collection<Widgets> widgets) {
        this.widgets = widgets;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Priviledge> getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(Collection<Priviledge> priviledges) {
        this.priviledges = priviledges;
    }
}
