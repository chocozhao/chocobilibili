package com.chocozhao.chocobilibili.mvp.ui.adapter.BannerHolderView;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;


public class BannerNetWorkImageHolderView extends Holder<GetBannerData> {
    private ImageView imageView;
    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public BannerNetWorkImageHolderView(View view) {
        super(view);
    }

    @Override
    protected void initView(View view) {
        this.imageView = (ImageView) view.findViewById(R.id.item_iv);
    }

    @Override
    public void updateUI(GetBannerData dataBean) {
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(imageView.getContext());
        mImageLoader = mAppComponent.imageLoader();
        try {
            if (dataBean.getImagePath() != null && dataBean.getImagePath().length() > 20) {
                mImageLoader.loadImage(imageView.getContext(),
                        ImageConfigImpl
                                .builder()
                                .url(dataBean.getImagePath())
                                .imageView(imageView)
                                .build());
            } else {
                mImageLoader.loadImage(imageView.getContext(),
                        ImageConfigImpl
                                .builder()
                                .url(ArmsUtils.getResources(imageView.getContext())+"")
                                .imageView(imageView)
                                .build());            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
