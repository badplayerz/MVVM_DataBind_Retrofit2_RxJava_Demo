package com.zlh.mvvp_databind_retrofit2_rxjava.interf;

import android.content.Context;

import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;

import java.util.List;

/**
 * Created by sdbean-zlh on 16/5/21.
 */
public interface WechatSelectionInterf {

    interface MainView extends BaseInterf.MainView{
        void loadDataRecycleView(List<WechatSelectionBean.ResultBean.ListBean> list);
    }

    interface ViewModel extends BaseInterf.ViewModel{

    }
}
