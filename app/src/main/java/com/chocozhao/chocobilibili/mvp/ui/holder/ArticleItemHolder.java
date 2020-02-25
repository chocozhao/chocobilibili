package com.chocozhao.chocobilibili.mvp.ui.holder;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

/**
 * ClaseName：ArticleItemHolder
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/12/17 16:34
 * Modified By：
 * Fixtime：2019/12/17 16:34
 * FixDescription：
 **/

public class ArticleItemHolder extends BaseHolder<GetArticleData.DatasBean> {
    @BindView(R.id.article_tv)
    TextView mArticleTv;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;
    public ArticleItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(@NonNull GetArticleData.DatasBean data, int position) {
        mArticleTv.setText(data.getTitle());
//itemView 的 Context 就是 Activity, Glide 会自动处理并和该 Activity 的生命周期绑定
//        mImageLoader.loadImage(itemView.getContext(),
//                ImageConfigImpl
//                        .builder()
//                        .url(data.getAvatarUrl())
//                        .imageView(mAvatar)
//                        .build());
    }

    /**
     * {@link BaseHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    @Override
    protected void onRelease() {
        //只要传入的 Context 为 Activity, Glide 就会自己做好生命周期的管理, 其实在上面的代码中传入的 Context 就是 Activity
        //所以在 onRelease 方法中不做 clear 也是可以的, 但是在这里想展示一下 clear 的用法
//        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
//                .imageViews(mAvatar)
//                .build());
//        this.mAvatar = null;
        this.mArticleTv = null;
        this.mAppComponent = null;
        this.mImageLoader = null;
    }
}
