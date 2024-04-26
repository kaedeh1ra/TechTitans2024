package com.example.techtitans.content_activities;

import android.net.Uri;
import androidx.annotation.DrawableRes;


public class MemoGameCard {

    private final int matchingId;
    private final int resImageID;
    private final Uri imageUri;

    public MemoGameCard(int matchingId, @DrawableRes int resImageID, Uri imageUri){
        this.matchingId = matchingId;
        this.resImageID = resImageID;
        this.imageUri = imageUri;
    }


    public int getMatchingId() {
        return matchingId;
    }


    public int getResImageID() {
        return resImageID;
    }

    public Uri getImageUri(){ return imageUri; }
}
