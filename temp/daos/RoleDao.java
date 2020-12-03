package edu.northeastern.cs5200.daos;

public interface RoleDao {
    //1                                     why roleid? There is no roleid in table
    void assignWebsiteRole(int developerId, int websiteId, String role);
    //2
    void assignPageRole(int developerId, int pageId, String role);
    //3
    void deleteWebsiteRole(int developerId, int websiteId, String role);
    //4
    void deletePageRole(int developerId, int pageId, String role);

}
