package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "ActedUser")

public class ActedUserVO {
    @NonNull
    @PrimaryKey

    @ColumnInfo(name="acted_user_id")
    @SerializedName("user-id")
    private String userId;

    @ColumnInfo(name = "name")
    @SerializedName("user-name")
    private String userName;

    @ColumnInfo(name = "profile_image")
    @SerializedName("profile-image")
    private String profileImage;


    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
