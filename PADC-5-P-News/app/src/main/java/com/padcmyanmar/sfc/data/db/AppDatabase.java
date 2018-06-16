package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.Telephony;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;

/**
 * Created by Acer on 6/8/2018.
 */
@Database(entities = {NewsVO.class, ActedUserVO.class,
        CommentActionVO.class,
        FavoriteActionVO.class,
        PublicationVO.class,
        SentToVO.class} ,version = 1 )
  public abstract class AppDatabase extends RoomDatabase {

  public static final String DB_NAME = "News_DB";

  private static AppDatabase INSTANCE;

  public abstract NewsDao newsDao();
  public abstract PublicationDao publicationDao();
  public abstract FavouriteDao favouriteDao();
  public abstract ActedUserDao actedUserDao();
  public abstract CommentActionDao commentActionDao();
  public abstract SentToDao sentToDao();



  public static AppDatabase getNewsDatabase(Context context){
      if (INSTANCE == null){
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DB_NAME)
                  .allowMainThreadQueries()
                  .build();
      }
      return INSTANCE;
  }

  public static void destroyInstance(){
      INSTANCE = null;
  }


}
