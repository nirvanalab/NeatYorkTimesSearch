package com.task.vidhurvoora.neatnytviewer.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.task.vidhurvoora.neatnytviewer.Model.Article;
import com.task.vidhurvoora.neatnytviewer.R;

import org.parceler.Parcels;

public class ArticleDetailActivity extends AppCompatActivity {

    private WebView wvArticleDetail;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbArticleDetail);
        setSupportActionBar(toolbar);

        wvArticleDetail = (WebView) findViewById(R.id.wvArticleDetail);
        configureWebview();

        article = Parcels.unwrap(getIntent().getParcelableExtra("article"));
        if (article != null  && article.web_url != null ) {
            wvArticleDetail.loadUrl(article.web_url);
        }

    }

    private void configureWebview() {
        //various webview configuration settings
        wvArticleDetail.setWebViewClient(new MyBrowser());
        wvArticleDetail.getSettings().setLoadsImagesAutomatically(true);
        wvArticleDetail.getSettings().setJavaScriptEnabled(true);
        wvArticleDetail.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvArticleDetail.getSettings().setSupportZoom(true);
        wvArticleDetail.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        wvArticleDetail.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page
    }
    // Manages the behavior when URLs are loaded
    private class MyBrowser extends WebViewClient {
//        @Override
//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                view.loadUrl(request.getUrl().toString());
//            }
//            return true;
//        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_detail_menu,menu);
        //get the share menu item
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        //get the share action provider
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        //creation share intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,wvArticleDetail.getUrl());
        //set the share intent to the action provider
        shareActionProvider.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }
}
