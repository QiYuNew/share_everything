package edu.northeastern.cs5200;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

import java.sql.Date;
import java.util.*;

public class hw_jdbc_qi_yu {

    public static void main(String args[]) {

        System.out.println("Yu Qi 001304349");
        System.out.println("It is working");

        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        java.sql.Date date = new java.sql.Date(javaTime);
//        System.out.println(date.toString());

        //-------------------------------------------
        //dev - user
        DeveloperImpl devdao = new DeveloperImpl();

        Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null);
        Developer bob = new Developer("5432trew", 23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null);
        Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null);

        devdao.createDeveloper(alice);
        devdao.createDeveloper(bob);
        devdao.createDeveloper(charlie);
        // I create userdao
        UserImpl udao = new UserImpl();

        User dan = new User(45, "Dan", "Martin");
        dan.setUsername("dan");
        dan.setPassword("dan");
        dan.setEmail("dan@martin.com");
        dan.setUseragreement(true);
        User ed = new User(56, "Ed", "Karaz");
        ed.setUsername("ed");
        ed.setPassword("ed");
        ed.setEmail("ed@kar.com");
        ed.setUseragreement(true);

        udao.createUser(dan);
        udao.createUser(ed);
        System.out.print("=====");
        //-------------------------------------------
        // web
        WebsiteImpl webdao = new WebsiteImpl();
        Website fb = new Website(123, "Facebook", "an online social media and social networking service", date, date,1234234);
        Website tt = new Website(234, "Twitter", "an online news and social networking service", date, date,4321543);
        Website wiki = new Website(345, "Wikipedia", "a free online encyclopedia", date, date,3456654);
        Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", date, date,6543345);
        Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", date, date,5433455);
        Website g = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", date, date,4322345);
        // no dev here, set -1
        webdao.createWebsiteForDeveloper(-1, fb);
        webdao.createWebsiteForDeveloper(-1, tt);
        webdao.createWebsiteForDeveloper(-1, wiki);
        webdao.createWebsiteForDeveloper(-1, cnn);
        webdao.createWebsiteForDeveloper(-1, cnet);
        webdao.createWebsiteForDeveloper(-1, g);
        System.out.print("=====");
        // web - role
        RoleImpl rldao = new RoleImpl();
        rldao.assignWebsiteRole(alice.getId(), fb.getId(), "owner");
        rldao.assignWebsiteRole(bob.getId(), fb.getId(), "editor");
        rldao.assignWebsiteRole(charlie.getId(), fb.getId(), "admin");

        rldao.assignWebsiteRole(bob.getId(), tt.getId(), "owner");
        rldao.assignWebsiteRole(charlie.getId(), tt.getId(), "editor");
        rldao.assignWebsiteRole(alice.getId(), tt.getId(), "admin");

        rldao.assignWebsiteRole(charlie.getId(), wiki.getId(), "owner");
        rldao.assignWebsiteRole(alice.getId(), wiki.getId(), "editor");
        rldao.assignWebsiteRole(bob.getId(), wiki.getId(), "admin");

        rldao.assignWebsiteRole(alice.getId(), cnn.getId(), "owner");
        rldao.assignWebsiteRole(bob.getId(), cnn.getId(), "editor");
        rldao.assignWebsiteRole(charlie.getId(), cnn.getId(), "admin");

        rldao.assignWebsiteRole(bob.getId(), cnet.getId(), "owner");
        rldao.assignWebsiteRole(charlie.getId(), cnet.getId(), "editor");
        rldao.assignWebsiteRole(alice.getId(), cnet.getId(), "admin");

        rldao.assignWebsiteRole(charlie.getId(), g.getId(), "owner");
        rldao.assignWebsiteRole(alice.getId(), g.getId(), "editor");
        rldao.assignWebsiteRole(bob.getId(), g.getId(), "admin");
        System.out.print("=====");
        //-------------------------------------------
        // page
        PageImpl pgdao = new PageImpl();
        Page home = new Page(123, "Home", "Landing Page", Date.valueOf("2020-01-06"), Date.valueOf("2020-02-20"), 123434);
        Page about = new Page(234, "About", "Website description", Date.valueOf("2020-01-06"), Date.valueOf("2020-02-20"), 234545);
        Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", Date.valueOf("2020-01-06"), Date.valueOf("2020-02-20"), 345656);
        Page pref = new Page(456, "Preferences", "Where users can configure preferences", Date.valueOf("2020-01-06"), Date.valueOf("2020-02-20"), 456776);
        Page profile = new Page(567, "Profile", "Users can configure their personal information", Date.valueOf("2020-01-06"), Date.valueOf("2020-02-20"), 567878);

        pgdao.createPageForWebsite(cnet.getId(), home);
        pgdao.createPageForWebsite(g.getId(), about);
        pgdao.createPageForWebsite(wiki.getId(), contact);
        pgdao.createPageForWebsite(cnn.getId(), pref);
        pgdao.createPageForWebsite(cnet.getId(), profile);
        System.out.print("=====");
        // page - role
        RoleImpl pgrl = new RoleImpl();
        pgrl.assignPageRole(alice.getId(), home.getId(), "editor");
        pgrl.assignPageRole(bob.getId(), home.getId(), "reviewer");
        pgrl.assignPageRole(charlie.getId(), home.getId(), "writer");

        pgrl.assignPageRole(bob.getId(), about.getId(), "editor");
        pgrl.assignPageRole(charlie.getId(), about.getId(), "reviewer");
        pgrl.assignPageRole(alice.getId(), about.getId(), "writer");

        pgrl.assignPageRole(charlie.getId(), contact.getId(), "editor");
        pgrl.assignPageRole(alice.getId(), contact.getId(), "reviewer");
        pgrl.assignPageRole(bob.getId(), contact.getId(), "writer");

        pgrl.assignPageRole(alice.getId(), pref.getId(), "editor");
        pgrl.assignPageRole(bob.getId(), pref.getId(), "reviewer");
        pgrl.assignPageRole(charlie.getId(), pref.getId(), "writer");

        pgrl.assignPageRole(bob.getId(), profile.getId(), "editor");
        pgrl.assignPageRole(charlie.getId(), profile.getId(), "reviewer");
        pgrl.assignPageRole(alice.getId(), profile.getId(), "writer");
        System.out.print("=====");
        //-------------------------------------------
        // widget
        WidgetImpl wgdao = new WidgetImpl();
        // no suitable method
        Widgets hd123 = new Widgets();
        hd123.setId(123);
        hd123.setName("head123");
        hd123.setDtype(Widgets.type.Heading);
        hd123.setText("Welcome");
        hd123.setOrderr(0);
        hd123.setPage_id(home.getId());

        Widgets post234 = new Widgets();
        post234.setId(234);
        post234.setName("post234");
        post234.setDtype(Widgets.type.Html);
        post234.setText("<p>Lorem</p>");
        post234.setOrderr(0);
        post234.setPage_id(about.getId());

        Widgets hd345 = new Widgets();
        hd345.setId(345);
        hd345.setName("head345");
        hd345.setDtype(Widgets.type.Heading);
        hd345.setText("Hi");
        hd345.setOrderr(1);
        hd345.setPage_id(contact.getId());

        Widgets i456 = new Widgets();
        i456.setId(456);
        i456.setName("intro456");
        i456.setDtype(Widgets.type.Html);
        i456.setText("<h1>Hi</h1>");
        i456.setOrderr(2);
        i456.setPage_id(contact.getId());

        Widgets img345 = new Widgets();
        img345.setId(567);
        img345.setName("image345");
        img345.setDtype(Widgets.type.Image);
        img345.setOrderr(3);
        img345.setWidth(50);
        img345.setHeight(100);
        img345.setImage_src("/img/567.png");
        img345.setPage_id(contact.getId());

        Widgets v456 = new Widgets();
        v456.setId(678);
        v456.setName("video456");
        v456.setDtype(Widgets.type.YouTube);
        v456.setOrderr(0);
        v456.setWidth(400);
        v456.setHeight(300);
        v456.setYoutube_url("https://youtu.be/ h67VX51QXiQ");
        v456.setPage_id(pref.getId());

        wgdao.createWidgetForPage(home.getId(), hd123);
        wgdao.createWidgetForPage(about.getId(), post234);
        wgdao.createWidgetForPage(contact.getId(), hd345);
        wgdao.createWidgetForPage(contact.getId(), i456);
        wgdao.createWidgetForPage(contact.getId(), img345);
        wgdao.createWidgetForPage(pref.getId(), v456);
        System.out.print("=====");
        //-------------------------------------------
        // update
        //1
        charlie.setPhone("333-444-5555");
        devdao.updateDeveloper(charlie.getId(), charlie);
        //2
        hd345.setOrderr(3);
        i456.setOrderr(1);
        img345.setOrderr(2);
        wgdao.updateWidget(hd345.getId(), hd345);
        wgdao.updateWidget(i456.getId(), i456);
        wgdao.updateWidget(img345.getId(), img345);
        //3
        Collection<Page> pgs = pgdao.findPagesForWebsite(cnet.getId());
        for(Page x: pgs){
            x.setTitle("CNET - "+x.getTitle());
            pgdao.updatePage(x.getId(), x);
        }
        //4
        pgrl.deletePageRole(charlie.getId(), home.getId(), "writer");
        pgrl.assignPageRole(charlie.getId(), home.getId(), "reviewer");
        pgrl.deletePageRole(bob.getId(), home.getId(), "reviewer");
        pgrl.assignPageRole(bob.getId(), home.getId(), "writer");
        System.out.print("=====");
        //-------------------------------------------
        // delete
        //1
        alice.setAddress("none");
        devdao.updateDeveloper(alice.getId(), alice);
        //2
        int max_o = 0;
        int max_o_id = 0;
        for(Widgets x: wgdao.findWidgetsForPage(contact.getId())){
            if(max_o < x.getOrderr())
            {
                max_o = x.getOrderr();
                max_o_id = x.getId();
            }
        }
        wgdao.deleteWidget(max_o_id);
        //3
        Date max_d = Date.valueOf("1000-01-01");
        int max_d_id = 0;
        for(Page x: pgdao.findPagesForWebsite(wiki.getId())){
            int result = max_d.compareTo(x.getUpdated());
            if(result < 0){
                max_d = x.getUpdated();
                max_d_id = x.getId();
            }
        }
        pgdao.deletePage(max_d_id);
        //4
        webdao.deleteWebsite(cnet.getId());

        //-------------------------------------------
        System.out.println("Done!");
    }
}
