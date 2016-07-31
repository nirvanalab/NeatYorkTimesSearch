package com.task.vidhurvoora.neatnytviewer.Model;

import com.task.vidhurvoora.neatnytviewer.Model.ApiClasses.Doc;
import com.task.vidhurvoora.neatnytviewer.Model.ApiClasses.Multimedia;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by vidhurvoora on 7/25/16.
 */
@Parcel
public class Article
{
    public String web_url;
    public String snippet;
    public ArrayList<Multimedia> mediaItems;
    public String headline;
    public String source;

    public Article(){

    }

    public  Article(Doc articleDoc) {

        this.web_url = articleDoc.getWebUrl();
        this.snippet = articleDoc.getSnippet();
        this.headline = articleDoc.getHeadline().getMain();
        this.mediaItems = (ArrayList) articleDoc.getMultimedia();
        this.source = articleDoc.getSource();
    }

    public String getWeb_url() {
        return web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getHeadline() {
        return headline;
    }

    public ArrayList<Multimedia> getMediaItems() {
        return mediaItems;
    }

    public String getSource() {
        return source;
    }
}
