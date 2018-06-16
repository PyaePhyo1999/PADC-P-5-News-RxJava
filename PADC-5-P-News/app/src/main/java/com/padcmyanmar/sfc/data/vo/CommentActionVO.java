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
//@Entity(tableName = "CommentAction")
@Entity(tableName = "CommentAction",foreignKeys = {@ForeignKey(entity = NewsVO.class,
        parentColumns = "newsId",
        childColumns = "news_id"),

        @ForeignKey(entity = ActedUserVO.class,
        parentColumns = "acted_user_id",
        childColumns = "action_user_id")})


   public class CommentActionVO {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "comment_action_id")
    @SerializedName("comment-id")
    private String commentId;

    @ColumnInfo(name = "action_user_id")
    private String actionUserId;

    @ColumnInfo(name = "news_id")
    private String newsId;


    @ColumnInfo(name = "comment")
    @SerializedName("comment")
    private String comment;

    @ColumnInfo(name="commentDate")
    @SerializedName("comment-date")
    private String commentDate;



    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setActionUserId(String actionUserId) {
        this.actionUserId = actionUserId;
    }

    public String getActionUserId() {
        return actionUserId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
