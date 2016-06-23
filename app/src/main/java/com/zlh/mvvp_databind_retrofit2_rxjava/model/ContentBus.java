package com.zlh.mvvp_databind_retrofit2_rxjava.model;

/**
 * ContentFragment RxBus数据类型
 * Created by sdbean-zlh on 16/6/23.
 */
public class ContentBus {

    public ContentBus(String webUrl) {
        this.webUrl = webUrl;
    }

    private String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
