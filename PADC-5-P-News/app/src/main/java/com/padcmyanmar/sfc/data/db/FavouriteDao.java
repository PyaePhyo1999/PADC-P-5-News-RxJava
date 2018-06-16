package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;

import java.util.List;

/**
 * Created by Acer on 6/10/2018.
 */
@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavourite(FavoriteActionVO favourites);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertFavourites(List<FavoriteActionVO> favourites);

    @Query("SELECT * FROM FavouriteAction")
    LiveData<List<FavoriteActionVO>> getFavourite();

    @Query("DELETE FROM FavouriteAction")
    void deleteAll();
}
