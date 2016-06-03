package com.zlh.mvvp_databind_retrofit2_rxjava.network.api;

import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sdbean-zlh on 16/5/20.
 */
public interface WechatSelectionApi {
    /**
     * 微信精选文章网址
     * http://v.juhe.cn/weixin/query?pno=&ps=&dtype=&key=您申请的KEY
     * @param pno   当前页数，默认1
     * @param ps    每页返回条数，最大100，默认20
     * @param dtype 返回数据的格式,xml或json，默认json
     * @param key
     * @return
     */
    @POST("query")
    Observable<WechatSelectionBean> getList(@Query("pno") int pno, @Query("ps") int ps, @Query("dtype") String dtype, @Query("key") String key);
}
