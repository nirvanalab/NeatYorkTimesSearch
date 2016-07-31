package com.task.vidhurvoora.neatnytviewer.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vidhurvoora on 7/26/16.
 */
public interface NYTEndpointInterface
{
    @GET("search/v2/articlesearch.json")
    Call<ArticleSearchResponse> searchForArticles( @Query("api-key") String apiKey
                                                   ,@Query("q") String query
                                                    ,@Query("page") int page
                                                    ,@Query("begin_date") String beginDate
                                                    ,@Query("fq") String newsDeskValues
                                                    ,@Query("sort") String sort);
    @GET("search/v2/articlesearch.json")
    Call<ArticleSearchResponse> searchForArticles( @Query("api-key") String apiKey
            ,@Query("q") String query
            ,@Query("page") int page
    );
}
