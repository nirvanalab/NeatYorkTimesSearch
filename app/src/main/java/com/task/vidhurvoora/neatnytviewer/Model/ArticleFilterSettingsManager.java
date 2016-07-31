package com.task.vidhurvoora.neatnytviewer.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.task.vidhurvoora.neatnytviewer.R;

/**
 * Created by vidhurvoora on 7/30/16.
 */

public class ArticleFilterSettingsManager
{
    private static ArticleFilterSettingsManager sInstance;

    public static synchronized ArticleFilterSettingsManager getSharedInstance() {
        if ( sInstance == null ) {
            sInstance = new ArticleFilterSettingsManager();

        }
        return sInstance;
    }

    public void saveFilterCriteria(ArticleFilterCriteria criteria, Context context) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = pref.edit();
        if ( criteria.beginDate != null ) {
            edit.putString("begin_date", criteria.beginDate);
        }
        if ( criteria.endDate != null ) {
            edit.putString("end_date", criteria.endDate);
        }
        if ( criteria.shouldSortByNewest != null ) {
            edit.putBoolean("should_sort_newest",criteria.shouldSortByNewest);
        }
        if ( criteria.newsDeskComponents != null ) {
            edit.putStringSet("news_desk_components", criteria.newsDeskComponents);
        }
        Boolean isSuccess = edit.commit();
        Log.d("Success","Blah");
//        edit.apply();
    }

    public ArticleFilterCriteria fetchFilterCriteria(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        ArticleFilterCriteria filterCriteria = new ArticleFilterCriteria();
        filterCriteria.beginDate = pref.getString("begin_date","");
        filterCriteria.endDate = pref.getString("end_date","");
        filterCriteria.shouldSortByNewest = pref.getBoolean("should_sort_newest",true);
        filterCriteria.newsDeskComponents = pref.getStringSet("news_desk_components",null);
        return filterCriteria;
    }

    public String getQueryNewsDeskComponent(int id) {
        switch (id) {
            case R.id.cbCategoryArts:
                return "Arts";
            case R.id.cbCategoryFashion:
                return "Fashion & Style";
            case R.id.cbCategorySports:
                return "Sports";
        }

        return null;
    }

}
