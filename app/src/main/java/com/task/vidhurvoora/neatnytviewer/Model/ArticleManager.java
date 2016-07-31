package com.task.vidhurvoora.neatnytviewer.Model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.vidhurvoora.neatnytviewer.Model.ApiClasses.Doc;

import java.util.ArrayList;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleManager {

    private String searchUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
    private static String searchBaseUrl = "https://api.nytimes.com/svc/";
    private static NYTEndpointInterface endpointInterface;
    private String NYTApiKey = "f1f748e9621d4cf8a311ba32e2f25159";
    private static ArticleManager sInstance;

    public static synchronized ArticleManager getSharedInstance() {
        if ( sInstance == null ) {
            sInstance = new ArticleManager();
            setupRetrofit();
        }
        return sInstance;
    }

    private static void setupRetrofit(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(searchBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endpointInterface = retrofit.create(NYTEndpointInterface.class);
    }

    //parses through the search response and generates the Article
    private ArrayList<Article> fetchArticlesFrom(ArticleSearchResponse searchResponse)
    {
        ArrayList<Doc> docs = (ArrayList) searchResponse.getResponse().getDocs();
        ArrayList<Article> articles = new ArrayList<Article>();
        for (Doc doc : docs ) {
            Article article = new Article(doc);
            articles.add(article);
        }
        return articles;
    }


    /*public void fetchArticleSearchResults( String query) {
        AsyncHttpClient client =  new AsyncHttpClient();
        RequestParams params =  new RequestParams();
        params.put("api-key",NYTApiKey);
        params.put("q",query);
        client.get(searchUrl, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Search Failure!: ",responseString.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new GsonBuilder().create();
                ArticleSearchResponse response = gson.fromJson(responseString,ArticleSearchResponse.class);
                ArrayList<Multimedia> media = (ArrayList<Multimedia>) response.getResponse().getDocs().get(0).getMultimedia();
                Log.d("Search Success!: ",response.toString());
            }
        });

    }*/

    public void fetchArticleSearchResults( String query,int page,final ArticleSearchResponseHandler handler) {
        Call<ArticleSearchResponse> searchResponse =  endpointInterface.searchForArticles(NYTApiKey
                ,query
                ,page
               );
        searchResponse.enqueue(new Callback<ArticleSearchResponse>() {
            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                ArticleSearchResponse articleSearchResponse = response.body();
                ArrayList<Article> articles = fetchArticlesFrom(articleSearchResponse);
                handler.articleSearchResults(true,articles);
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {
                handler.articleSearchResults(false,null);
            }
        });
    }

    public void fetchArticleSearchResults( String query,int page, ArticleFilterCriteria filterCriteria,final ArticleSearchResponseHandler handler) {

        String newsDeskQuery = null;
        Set<String> newsDeskValues = filterCriteria.getNewsDeskComponents();
        if ( newsDeskValues != null && !newsDeskValues.isEmpty()) {
            newsDeskQuery = "news_desk:(";
            if ( newsDeskValues != null ) {
                for (String category : newsDeskValues ) {
                    newsDeskQuery += "\"" + category +"\" ";
                }
            }
            newsDeskQuery += ")";
        }

        String sortOrder = filterCriteria.getShouldSortByNewest() ? "newest" : "oldest";

        Call<ArticleSearchResponse> searchResponse =  endpointInterface.searchForArticles(NYTApiKey
                                                                        ,query
                                                                        ,page
                                                                        ,filterCriteria.getBeginDate()
                                                                        ,newsDeskQuery
                                                                        ,sortOrder);
        searchResponse.enqueue(new Callback<ArticleSearchResponse>() {
            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                ArticleSearchResponse articleSearchResponse = response.body();
                ArrayList<Article> articles = fetchArticlesFrom(articleSearchResponse);
                handler.articleSearchResults(true,articles);
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {
                handler.articleSearchResults(false,null);
            }
        });
    }
}
