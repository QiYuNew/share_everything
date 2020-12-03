package edu.northeastern.cs5200.daos;

public interface PriviledgeDao {
    //1
    void assignWebsitePrivilege(int developerId, int websiteId, String priviledge);
    //2
    void assignPagePriviledge(int developerId, int pageId, String priviledge);
    //3
    void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge);
    //4
    void deletePagePriviledge(int developerId, int pageId, String priviledge);

}
