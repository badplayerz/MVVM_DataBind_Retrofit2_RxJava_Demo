package com.zlh.mvvp_databind_retrofit2_rxjava.interf;

import android.content.Context;

/**
 * Created by sdbean-zlh on 16/5/27.
 */
public interface BaseInterf {
    interface MainView{
        Context getContext();
    }
    interface ViewModel{
        void destory();
    }
}
