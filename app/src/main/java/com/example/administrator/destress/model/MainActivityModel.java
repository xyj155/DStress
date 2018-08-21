package com.example.administrator.destress.model;

import com.example.administrator.destress.base.BaseGson;
import com.example.administrator.destress.contract.MainActivityContract;
import com.example.administrator.destress.gson.Article;
import com.example.administrator.destress.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public class MainActivityModel implements MainActivityContract.Model {
    @Override
    public Observable<BaseGson<Article>> queryArticle() {
        return RetrofitUtil.getInstance().getServerices().queryArticle();
    }
}
