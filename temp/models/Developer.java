package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.*;

public class Developer extends Person{
    private String developer_key = null;

    private Collection<Website> websites;
    public void addwebsite(Website website) {
        this.websites.add(website);
    }
    public void removewebsite(Website website) {
        this.websites.remove(website);
    }

    private Collection<Role> roles;
    public void addrole(Role role) {
        this.roles.add(role);
    }
    public void removerole(Role role) {
        this.roles.remove(role);
    }

    private Collection<Priviledge> priviledges;
    public void addpriviledge(Priviledge priviledge) {
        this.priviledges.add(priviledge);
    }
    public void removepriviledge(Priviledge priviledge) {
        this.priviledges.remove(priviledge);
    }

    public Developer() {
        super();
    }

    public Developer(String dev_key, int dev_id, String fstname, String latname) {
        super();
        this.developer_key = dev_key;
        super.setId(dev_id);
        super.setFirstname(fstname);
        super.setLastname(latname);
    }

    public Developer(String dev_key, int dev_id, String fstname, String latname,
                     String usname, String pasw, String email, Date dob) {
        super();
        this.developer_key = dev_key;
        super.setId(dev_id);
        super.setFirstname(fstname);
        super.setLastname(latname);
        super.setUsername(usname);
        super.setPassword(pasw);
        super.setEmail(email);
        super.setDob(dob);
    }

    public Developer(String dev_key, int dev_id, String fstname, String latname,
                     String usname, String pasw, String email, Date dob,
                     String add, String p) {
        super();
        this.developer_key = dev_key;
        super.setId(dev_id);
        super.setFirstname(fstname);
        super.setLastname(latname);
        super.setUsername(usname);
        super.setPassword(pasw);
        super.setEmail(email);
        super.setDob(dob);
        super.setAddress(add);
        super.setPhone(p);
    }

    public String getDeveloper_key() {
        return developer_key;
    }

    public void setDeveloper_key(String developer_key) {
        this.developer_key = developer_key;
    }

    public Collection<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(Collection<Website> websites) {
        this.websites = websites;
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
