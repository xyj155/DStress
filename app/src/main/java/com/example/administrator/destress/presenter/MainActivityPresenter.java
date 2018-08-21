package com.example.administrator.destress.presenter;

import android.util.Log;

import com.example.administrator.destress.base.BaseGson;
import com.example.administrator.destress.base.BaseObserver;
import com.example.administrator.destress.contract.MainActivityContract;
import com.example.administrator.destress.gson.Article;
import com.example.administrator.destress.model.MainActivityModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.Model model = new MainActivityModel();
    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }

    private static final String TAG = "MainActivityPresenter";

    @Override
    public void queryArticle() {
        model.queryArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<Article>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: " + "请求错误！");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<Article> articleBaseGson) {
                        view.setArticle(articleBaseGson.getData());
                    }
                });
    }
}
