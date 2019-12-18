package com.chocozhao.chocobilibili.mvp.ui.adapter;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.chocozhao.chocobilibili.mvp.ui.holder.ArticleItemHolder;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * ClaseName：ArticleAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/12/17 15:50
 * Modified By：
 * Fixtime：2019/12/17 15:50
 * FixDescription：
 **/

public class ArticleAdapter extends DefaultAdapter<GetArticleData> {



    public ArticleAdapter(List<GetArticleData> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<GetArticleData> getHolder(@NonNull View v, int viewType) {
        return new ArticleItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.article_list;
    }
}