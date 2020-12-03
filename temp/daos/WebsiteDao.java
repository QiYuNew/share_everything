package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Website;

import java.util.Collection;

public interface WebsiteDao {
    //1
    void createWebsiteForDeveloper(int developerId, Website website);
    //2
    Collection<Website> findAllWebsites();
    //3
    Collection<Website> findWebsitesForDeveloper(int developerId);
    //4
    Website findWebsiteById(int websiteId);
    //5
    int updateWebsite(int websiteId, Website website);
    //6
    int deleteWebsite(int websiteId);
}
