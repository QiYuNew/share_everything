package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Widgets;
import java.util.Collection;

public interface WidgetDao {
    //1
    void createWidgetForPage(int pageId, Widgets widget);
    //2
    Collection<Widgets> findAllWidgets();
    //3
    Widgets findWidgetById(int widgetId);
    //4
    Collection<Widgets> findWidgetsForPage(int pageId);
    //5
    int updateWidget(int widgetId, Widgets widget);
    //6
    int deleteWidget(int widgetId);

}
