package com.task.vidhurvoora.neatnytviewer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.task.vidhurvoora.neatnytviewer.Model.ApiClasses.Multimedia;
import com.task.vidhurvoora.neatnytviewer.Model.Article;
import com.task.vidhurvoora.neatnytviewer.R;

import java.util.ArrayList;

/**
 * Created by vidhurvoora on 7/26/16.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    // Define listener member variable
    private static OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivArticleImage;
        TextView tvHeading;

        public ViewHolder(final View itemView) {
            super(itemView);

            ivArticleImage =  (ImageView) itemView.findViewById(R.id.ivArticleImage);
            tvHeading = (TextView) itemView.findViewById(R.id.tvHeading);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    //constructor
    private  Context mContext;
    private  ArrayList<Article> mArticles;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        this.mContext = context;
        this.mArticles = articles;
    }

    public Context getmContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(getmContext());
        View articleView = inflater.inflate(R.layout.article_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(articleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Article article = mArticles.get(position);
        holder.tvHeading.setText(article.getHeadline());
        ArrayList<Multimedia> mediaList = article.getMediaItems();
        if (mediaList!=null && mediaList.size() > 0 ) {
            //get first item for now
            Multimedia media = mediaList.get(0);
            String mediaUrl = media.getUrl();
            if ( mediaUrl != null ) {
                Picasso.with(getmContext()).load(mediaUrl).into(holder.ivArticleImage);
            }

        }


    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

}
