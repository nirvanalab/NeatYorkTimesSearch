package com.task.vidhurvoora.neatnytviewer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.task.vidhurvoora.neatnytviewer.Adapter.ArticleAdapter;
import com.task.vidhurvoora.neatnytviewer.Fragment.ArticleSearchFragment;
import com.task.vidhurvoora.neatnytviewer.Model.Article;
import com.task.vidhurvoora.neatnytviewer.Model.ArticleFilterCriteria;
import com.task.vidhurvoora.neatnytviewer.Model.ArticleFilterSettingsManager;
import com.task.vidhurvoora.neatnytviewer.Model.ArticleManager;
import com.task.vidhurvoora.neatnytviewer.Model.ArticleSearchResponseHandler;
import com.task.vidhurvoora.neatnytviewer.R;
import com.task.vidhurvoora.neatnytviewer.Utility.EndlessRecyclerViewScrollListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ArticleSearchActivity extends AppCompatActivity implements ArticleSearchFragment.OnArticleFilterCriteriaUpdateListener {

    private ArrayList<Article> articles = new ArrayList<Article>();
    private ArticleAdapter articleAdapter;
    private String currentQuery;
    private Toolbar toolbar;
    ArticleFilterCriteria searchFilterCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neat_nytmain);

        //get the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbArticleMain);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchFilterCriteria = ArticleFilterSettingsManager.getSharedInstance().fetchFilterCriteria(getApplicationContext());

        //fetch the first load
        fetchArticles(currentQuery,0,searchFilterCriteria);

        RecyclerView rvArticles = (RecyclerView) findViewById(R.id.rvArticles);
        articleAdapter = new ArticleAdapter(getApplicationContext(),articles);
        rvArticles.setAdapter(articleAdapter);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rvArticles.setLayoutManager(gridLayoutManager);

        rvArticles.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                    fetchArticles(currentQuery,page,searchFilterCriteria);
            }
        });

        articleAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Article article = articles.get(position);
                Intent intent = new Intent(ArticleSearchActivity.this,ArticleDetailActivity.class);
                intent.putExtra("article", Parcels.wrap(article));
                startActivity(intent);
            }
        });


    }


    private void setupSearch(MenuItem searchItem){
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentQuery = query;
                clearArticles();
                //fetch new articles
                fetchArticles(query,0,searchFilterCriteria);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if ( newText.isEmpty() ) {
                    clearArticles();
                    currentQuery = null;
                    fetchArticles(null,0,searchFilterCriteria);
                }
                return true;
            }


        });
    }

    private void clearArticles() {
        //clear the current articles
        int existingSize = articles.size();
        articles.clear();
        articleAdapter.notifyItemRangeRemoved(0,existingSize);
    }

    private void showSearchFilterDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ArticleSearchFragment searchFragment = ArticleSearchFragment.newInstance();
        searchFragment.mListener = this;
        searchFragment.show(fragmentManager,"fragment_article_search");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        setupSearch(searchItem);
//        MenuItem filterItem = menu.findItem(R.id.action_search_filter);
//        setupSearchFilter(filterItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search_filter) {
            showSearchFilterDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchArticles(String query, int page,ArticleFilterCriteria filterCriteria){
        if (filterCriteria == null ) {
            ArticleManager.getSharedInstance().fetchArticleSearchResults(query, page, new ArticleSearchResponseHandler() {
                @Override
                public void articleSearchResults(boolean isSuccess, ArrayList<Article> newArticles) {
                    if (isSuccess) {
                        int existingSize = articles.size();
                        articles.addAll(newArticles);
                        articleAdapter.notifyItemRangeInserted(existingSize,newArticles.size());
                    }
                }
            });
        }
        else {
            ArticleManager.getSharedInstance().fetchArticleSearchResults(query, page,filterCriteria, new ArticleSearchResponseHandler() {
                @Override
                public void articleSearchResults(boolean isSuccess, ArrayList<Article> newArticles) {
                    if (isSuccess) {
                        int existingSize = articles.size();
                        articles.addAll(newArticles);
                        articleAdapter.notifyItemRangeInserted(existingSize,newArticles.size());
                    }
                }
            });
        }
    }


    @Override
    public void onArticleFilterCriteriaUpdated(ArticleFilterCriteria filterCriteria) {
       clearArticles();
        searchFilterCriteria = filterCriteria;
        fetchArticles(currentQuery,0,filterCriteria);
    }
}
