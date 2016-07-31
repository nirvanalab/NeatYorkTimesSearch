package com.task.vidhurvoora.neatnytviewer.Model;

import java.util.Set;

/**
 * Created by vidhurvoora on 7/30/16.
 */
public class ArticleFilterCriteria
{
    String beginDate;
    String endDate;
    Boolean shouldSortByNewest;
    Set<String> newsDeskComponents;

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getShouldSortByNewest() {
        return shouldSortByNewest;
    }

    public Set<String> getNewsDeskComponents() {
        return newsDeskComponents;
    }

    //setters
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setShouldSortByNewest(Boolean shouldSortByNewest) {
        this.shouldSortByNewest = shouldSortByNewest;
    }

    public void setNewsDeskComponents(Set<String> newsDeskComponents) {
        this.newsDeskComponents = newsDeskComponents;
    }
}
