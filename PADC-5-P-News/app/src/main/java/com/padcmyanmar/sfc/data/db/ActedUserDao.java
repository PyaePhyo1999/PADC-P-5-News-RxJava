package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

/**
 * Created by Acer on 6/10/2018.
 */
@Dao
public interface ActedUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertActedUser(ActedUserVO  actionUser);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertActedUsers(List<ActedUserVO> actionUser);

    @Query("SELECT * FROM ActedUser")
    LiveData<List<ActedUserVO>> getActionUser();

    @Query("DELETE FROM ActedUser")
    void deleteAll();
}
