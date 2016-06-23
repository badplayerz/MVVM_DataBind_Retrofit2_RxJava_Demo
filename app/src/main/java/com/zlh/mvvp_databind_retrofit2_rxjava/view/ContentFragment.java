package com.zlh.mvvp_databind_retrofit2_rxjava.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zlh.mvvp_databind_retrofit2_rxjava.R;
import com.zlh.mvvp_databind_retrofit2_rxjava.adapter.ContentViewPagerAdapter;
import com.zlh.mvvp_databind_retrofit2_rxjava.databinding.FragmentContentBinding;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.ContentFragmentInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel.ContentFragmentViewModel;


public class ContentFragment extends Fragment implements ContentFragmentInterf.MainView{

    private static final String WEBURL = "weburl";

    private FragmentContentBinding fragmentContentBinding;

    private ContentFragmentViewModel contentFragmentViewModel;

    public static ContentFragment newInstance(String weburl) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(WEBURL, weburl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            webUrl = getArguments().getString(WEBURL);
//        }
    }

    @Override
    public void initWebViewUrl(String webUrl) {
        fragmentContentBinding.contentWebview.loadUrl(webUrl);
        fragmentContentBinding.contentWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentContentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_content, container, false);
        contentFragmentViewModel = new ContentFragmentViewModel(this,getContext());
        fragmentContentBinding.setContentFragmentViewModel(contentFragmentViewModel);



        return fragmentContentBinding.getRoot();
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        contentFragmentViewModel.destory();
        super.onDestroy();
    }
}
