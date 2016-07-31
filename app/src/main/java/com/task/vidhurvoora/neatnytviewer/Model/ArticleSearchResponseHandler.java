package com.task.vidhurvoora.neatnytviewer.Model;

import java.util.ArrayList;

/**
 * Created by vidhurvoora on 7/25/16.
 */

public interface ArticleSearchResponseHandler {
    public void articleSearchResults(boolean isSuccess, ArrayList<Article> articles);
}
