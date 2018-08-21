package com.example.administrator.destress.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.destress.R;
import com.example.administrator.destress.base.BaseActivity;
import com.example.administrator.destress.contract.MainActivityContract;
import com.example.administrator.destress.gson.Article;
import com.example.administrator.destress.presenter.MainActivityPresenter;
import com.example.administrator.destress.util.BannerViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainActivityContract.View {
    private MZBannerView mMZBanner;
    private MainActivityPresenter mainActivityPresenter;
    private RecyclerView ry_article;
    private SmartRefreshLayout main_swip;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        main_swip = (SmartRefreshLayout) findViewById(R.id.main_swip);
        mMZBanner = (MZBannerView) findViewById(R.id.main_banner);
        ry_article = (RecyclerView) findViewById(R.id.ry_article);
        ry_article.setNestedScrollingEnabled(false);
        ry_article.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534600024220&di=5b30bf66ca7fb684eb9a64cd159f629c&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01449657fddbe4a84a0e282b8b3b2d.jpg%402o.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534600024220&di=87aca7e3db792d9438e57c65771bfb23&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F19%2F99%2F40%2F99y58PIC9DW_1024.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534600024214&di=bf6d1a4986cfb6b4b540749fc89e946c&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F69%2F99%2F66%2F9fce5755f081660431464492a9aeb003.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534600024248&di=b3f1ec3009c42249eca002adae1059bd&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F25%2F97%2F64%2F40b58PICBQm_1024.jpg");
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();
    }

    @Override
    public void initData() {
        mainActivityPresenter = new MainActivityPresenter(this);
        main_swip.autoRefresh();
        main_swip.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mainActivityPresenter.queryArticle();
                main_swip.finishRefresh(3000);
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

    @Override
    public void setArticle(List<Article> article) {
        ry_article.setAdapter(new ArticleAdapter(article));
    }

    private class ArticleAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {

        public ArticleAdapter(List<Article> data) {
            super(R.layout.main_ry_article_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Article item) {
            ImageView view = helper.getView(R.id.img_src);
            Glide.with(MainActivity.this).load(item.getImg()).asBitmap().into(view);
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_content, item.getContent());
        }
    }
}
