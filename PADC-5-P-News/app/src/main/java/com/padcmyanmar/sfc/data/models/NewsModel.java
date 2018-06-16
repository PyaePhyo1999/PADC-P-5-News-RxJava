package com.padcmyanmar.sfc.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.db.ActedUserDao;
import com.padcmyanmar.sfc.data.db.AppDatabase;
import com.padcmyanmar.sfc.data.db.PublicationDao;
import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kotlin.Function;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {
    private static NewsModel objInstance;
    private AppDatabase mAppDatabase;
    private int mmNewsPageIndex = 1;
    private PublishSubject<List<NewsVO>> mNewsSubject;
    private List<NewsVO> mNews;

    public NewsModel(Context context) {
        mNewsSubject = PublishSubject.create();
        mAppDatabase = AppDatabase.getNewsDatabase(context);
        EventBus.getDefault().register(this);
        startLoadingMMNews();

    }

    public static NewsModel getInstance() {
        if(objInstance == null) {
           return objInstance;
        }
        throw new RuntimeException("Error");
    }
    public static void initDatabase(Context context) {
      objInstance = new NewsModel(context);
    }

    public void initPublishSubject(PublishSubject<List<NewsVO>> newsSubject){
        this.mNewsSubject = newsSubject;
    }

    public void startLoadingMMNews() {
       // MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);
        Single<GetNewsResponse> getNewsResponseSingle = getNewsResponseSingle();
        getNewsResponseSingle
                .subscribeOn(Schedulers.io())
                .map(new io.reactivex.functions.Function<GetNewsResponse, List<NewsVO>>() {
                    @Override
                    public List<NewsVO> apply(GetNewsResponse getNewsResponse) throws Exception {
                        return getNewsResponse.getNewsList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<NewsVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<NewsVO> newsVOs) {

                        mNewsSubject.onNext(newsVOs);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(SFCNewsApp.LOG_TAG, "onError: " + e.getMessage());
                    }
                } );

    }
    public Single<GetNewsResponse> getNewsResponseSingle(){
        SFCNewsApp rxJavaApp = new SFCNewsApp();
        return rxJavaApp.getNewsApi().loadMMNews(mmNewsPageIndex,AppConstants.ACCESS_TOKEN);


    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mAppDatabase.newsDao().deleteAll();
        for (NewsVO news : event.getLoadNews()) {

           long insertPublication = mAppDatabase.publicationDao().insertPublication(news.getPublication());
           Log.d(SFCNewsApp.LOG_TAG,"insert total publication" + insertPublication);
           for(FavoriteActionVO favoriteActionVO : news.getFavoriteActions()){
               long insertFavouriteActed = mAppDatabase.actedUserDao().insertActedUser(favoriteActionVO.getActedUser());
               long [] insertFavourite = mAppDatabase.favouriteDao().insertFavourites(news.getFavoriteActions());
           }
            for(CommentActionVO commentActionVO : news.getCommentActions()){
                long insertCommentActed = mAppDatabase.actedUserDao().insertActedUser(commentActionVO.getActedUser());
                long [] insertComment = mAppDatabase.commentActionDao().insertCommentActions( news.getCommentActions());
            }
            for(SentToVO sentToVO : news.getSentToActions()){
                long insertFavouriteSender = mAppDatabase.actedUserDao().insertActedUser(sentToVO.getSender());
                long insertFavouriteReceiver = mAppDatabase.actedUserDao().insertActedUser(sentToVO.getReceiver());
                long [] insertSentTo = mAppDatabase.sentToDao().insertSentTos( news.getSentToActions());
            }




           long  insertNews = mAppDatabase.newsDao().insertNews(news);
           Log.d(SFCNewsApp.LOG_TAG,"insert total publication" + insertNews);
        }
        }

    }



