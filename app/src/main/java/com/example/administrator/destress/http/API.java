package com.example.administrator.destress.http;

import com.example.administrator.destress.base.BaseGson;
import com.example.administrator.destress.gson.Article;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/8/18/018.
 */

public interface API {

    @GET("/DeStreePhp/public/index.php/Index/Article/queryArticle")
    Observable<BaseGson<Article>> queryArticle();
}
