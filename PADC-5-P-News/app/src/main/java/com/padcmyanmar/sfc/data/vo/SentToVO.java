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
@Entity(tableName = "SentToAction",
        foreignKeys = {@ForeignKey(entity = NewsVO.class,
                parentColumns = "newsId",
                childColumns = "news_id"),
                @ForeignKey(entity = ActedUserVO.class,
                        parentColumns = "acted_user_id",
                        childColumns = "senderUserId"),
                @ForeignKey(entity = ActedUserVO.class,
                        parentColumns = "acted_user_id",
                        childColumns = "receiverUserId")})

public class SentToVO {
    @NonNull
    @PrimaryKey
    @SerializedName("send-to-id")
    private String sendToId;

    @ColumnInfo(name="sentDate")
    @SerializedName("sent-date")
    private String sentDate;

    @ColumnInfo(name = "news_id")
    private String newsId;

    @ColumnInfo(name = "action_user_id")
    private int actionUserId;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO sender;

    @Ignore
    @SerializedName("received-user")
    private ActedUserVO receiver;

    private String senderUserId;

    private String receiverUserId;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }



    public String getNewsId() {
        return newsId;
    }

    public int getActionUserId() {
        return actionUserId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSendToId(String sendToId) {
        this.sendToId = sendToId;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setActionUserId(int actionUserId) {
        this.actionUserId = actionUserId;
    }

    public void setSender(ActedUserVO sender) {
        this.sender = sender;
    }

    public void setReceiver(ActedUserVO receiver) {
        this.receiver = receiver;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;


    }

    public ActedUserVO getSender() {
        return sender;
    }

    public ActedUserVO getReceiver() {
        return receiver;
    }
}
