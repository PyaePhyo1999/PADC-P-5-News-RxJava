package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;

import java.util.List;

/**
 * Created by Acer on 6/8/2018.
 */
@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertNews(NewsVO news);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertNews(NewsVO... news);

    @Query("SELECT * FROM news")
    LiveData<List<NewsVO>> getNews();

    @Query("DELETE FROM news")
    void deleteAll();


}
