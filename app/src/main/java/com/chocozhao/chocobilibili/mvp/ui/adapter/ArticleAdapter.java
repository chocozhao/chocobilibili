package com.chocozhao.chocobilibili.mvp.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

public class ArticleAdapter extends BaseQuickAdapter<GetArticleData.DatasBean, BaseViewHolder> {

    /**
     * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
     * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
     */
    public ArticleAdapter(@Nullable List<GetArticleData.DatasBean> data) {
        super(R.layout.article_list, data);
    }

    /**
     * 在此方法中设置item数据
     */
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GetArticleData.@Nullable DatasBean datasBean) {
        baseViewHolder.setText(R.id.article_tv, datasBean.getTitle());
    }

}