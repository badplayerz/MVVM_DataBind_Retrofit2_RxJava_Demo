package com.zlh.mvvp_databind_retrofit2_rxjava.interf;

import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;

import java.util.List;

/**
 * Created by sdbean-zlh on 16/5/21.
 */
public interface ContentFragmentInterf {

    interface MainView extends BaseInterf.MainView{
        void initWebViewUrl(String webUrl);
    }

    interface ViewModel extends BaseInterf.ViewModel{

    }
}
