package com.example.administrator.destress.contract;

import com.example.administrator.destress.base.BaseGson;
import com.example.administrator.destress.gson.Article;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public interface MainActivityContract {
    interface Model {
        Observable<BaseGson<Article>> queryArticle();
    }

    interface View {
        void setArticle(List<Article> article);
    }

    interface Presenter {
        void queryArticle();
    }
}
