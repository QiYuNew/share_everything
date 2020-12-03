package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.*;

public class Website {
    private int id = -1;
    private int developer_id = -1;
    private String name = null;
    private String description = null;
    private Date created = null;
    private Date updated = null;
    private int visited = -1;

    private Collection<Page> pages;
    public void addpage(Page page){
        this.pages.add(page);
    }
    public void removepage(Page page){
        this.pages.remove(page);
    }

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

    public Website() {

    }

    public Website(int id, String name, String description, Date created,
        Date updated, int visited) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.visited = visited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public Collection<Page> getPages() {
        return pages;
    }

    public void setPages(Collection<Page> pages) {
        this.pages = pages;
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
