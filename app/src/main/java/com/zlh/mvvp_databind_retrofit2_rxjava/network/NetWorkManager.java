package com.zlh.mvvp_databind_retrofit2_rxjava.network;

import com.zlh.mvvp_databind_retrofit2_rxjava.network.api.WechatSelectionApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sdbean-zlh on 16/5/20.
 */
public class NetWorkManager {

    public static final String WECHATSELECTION_URL = "http://v.juhe.cn/weixin/";    //微信精选文章url
    public static final String WECHATSELECTION_KEY ="77450a4e8d6a0d3c463fe7a66b43488c"; //聚合数据申请微信精选appkey

    private static NetWorkManager netWorkManager;
    private OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory ;
    private static CallAdapter.Factory rxJavaCallAdapterFactory;

    public static NetWorkManager getInstent(){
        if(netWorkManager == null){
            netWorkManager = new NetWorkManager();
        }
        return netWorkManager;
    }

    public NetWorkManager() {
        this.okHttpClient = new OkHttpClient();
        gsonConverterFactory = GsonConverterFactory.create();
        rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    }

    public WechatSelectionApi getWechatSelectionApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WECHATSELECTION_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        return retrofit.create(WechatSelectionApi.class);
    }


}
