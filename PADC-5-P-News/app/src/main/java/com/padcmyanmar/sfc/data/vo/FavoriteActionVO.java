package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "FavouriteAction",foreignKeys =
        {@ForeignKey(entity = NewsVO.class,
        parentColumns = "newsId",
        childColumns = "news_id"),
        @ForeignKey(entity = ActedUserVO.class,
                parentColumns = "acted_user_id",
                childColumns = "action_user_id")})


public class FavoriteActionVO {
    @NonNull
    @PrimaryKey
    @SerializedName("favorite-id")
    private String favoriteId;

    @ColumnInfo(name = "news_id")
    private String newsId;

    @ColumnInfo(name = "action_user_id")
    private String actionUserId;

    @ColumnInfo(name = "favouriteDate")
    @SerializedName("favorite-date")
    private String favoriteDate;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(String actionUserId) {
        this.actionUserId = actionUserId;
    }

    public void setFavoriteDate(String favoriteDate) {
        this.favoriteDate = favoriteDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }
}
