//package com.chocozhao.chocobilibili.mvp.ui.adapter.BannerHolderView;
//
//import android.view.View;
//import android.widget.ImageView;
//
//import com.bigkoo.convenientbanner.holder.Holder;
//import com.chocozhao.chocobilibili.R;
//
//
//public class LocalHolderView extends Holder<String> {
//    private ImageView imageView;
//
//    public LocalHolderView(View view) {
//        super(view);
//    }
//
//    protected void initView(View view) {
//        this.imageView = (ImageView) view.findViewById(R.id.item_iv);
//    }
//
//    public void updateUI(String url) {
//        ILFactory.getLoader().loadImageSizekipMemoryCache( url,imageView);
//
//    }
//}