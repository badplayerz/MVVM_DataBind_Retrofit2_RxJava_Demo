package com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zlh.mvvp_databind_retrofit2_rxjava.R;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;
import com.zlh.mvvp_databind_retrofit2_rxjava.view.WechatContentActivity;
import com.zlh.mvvp_databind_retrofit2_rxjava.view.WechatSelectionActivity;

/**
 * Created by sdbean-zlh on 16/5/23.
 */
public class ItemWechatSelectionViewModel extends BaseObservable{

    private WechatSelectionBean.ResultBean.ListBean itemBean;
    private Context mContext;

    public String title;
    public String source;
    public String firstImg;

    public String getTitle() {
        return itemBean.getTitle();
    }

    public String getSource() {
        return itemBean.getSource();
    }

    public String getFirstImg() {
        return itemBean.getFirstImg();
    }

    /**
     * item点击事件
     * @param view
     */
    public void itemonclick(View view){

        Intent intent = new Intent();
        intent.setClass(view.getContext(),WechatContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("wechatcontent",itemBean);
        intent.putExtras(bundle);
        if(Build.VERSION.SDK_INT>=21){

            Transition ts = new ChangeTransform();
            ts.setDuration(3000);
            ((Activity) mContext).getWindow().setExitTransition(ts);

            View pic =  view.findViewById(R.id.item_circleimage);
            Bundle bundleTrans = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,
                    Pair.create(pic, getFirstImg()+"pic")).toBundle();

            mContext.startActivity(intent, bundleTrans);
        }else{
            mContext.startActivity(intent);
        }



    }

    @BindingAdapter({ "imageUrl","imageNull" })
    public static void loadImage(ImageView view, String imageurl, Drawable image){
        Glide.with(view.getContext())
                .load(imageurl)
                .error(image)
                .fitCenter()
                .into(view);

    }



    public ItemWechatSelectionViewModel(WechatSelectionBean.ResultBean.ListBean bean, Context context) {
        this.itemBean = bean;
        this.mContext = context;

    }

    public void setItemContent(WechatSelectionBean.ResultBean.ListBean listBean){
        itemBean = listBean;
        notifyChange();
    }
}
