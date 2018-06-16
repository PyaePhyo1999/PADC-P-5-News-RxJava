package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

/**
 * Created by Acer on 6/10/2018.
 */
@Dao
public interface CommentActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCommentAction(CommentActionVO comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertCommentActions(List<CommentActionVO> comments);

    @Query("SELECT * FROM CommentAction")
    LiveData<List<CommentActionVO>> getComments();

    @Query("DELETE FROM CommentAction")
    void deleteAll();
}
