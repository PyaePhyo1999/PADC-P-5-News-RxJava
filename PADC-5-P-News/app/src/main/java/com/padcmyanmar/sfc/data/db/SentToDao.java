package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;

import java.util.List;

/**
 * Created by Acer on 6/10/2018.
 */
@Dao
public interface SentToDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSentTo(SentToVO sentTos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertSentTos(List<SentToVO> sentTos);

    @Query("SELECT * FROM SentToAction")
    LiveData<List<SentToVO>> getSentTo();

    @Query("DELETE FROM SentToAction")
    void deleteAll();
}
