package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Page;
import java.util.Collection;

public interface PageDao {
    //1
    void createPageForWebsite(int websiteId, Page page);
    //2
    Collection<Page> findAllPages();
    //3
    Page findPageById(int pageId);
    //4
    Collection<Page> findPagesForWebsite(int websiteId);
    //5
    int updatePage(int pageId, Page page);
    //6
    int deletePage(int pageId);

}
