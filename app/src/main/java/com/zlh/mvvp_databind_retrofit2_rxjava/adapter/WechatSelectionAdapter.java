package com.zlh.mvvp_databind_retrofit2_rxjava.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlh.mvvp_databind_retrofit2_rxjava.R;
import com.zlh.mvvp_databind_retrofit2_rxjava.databinding.ItemWechatselectionBinding;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;
import com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel.ItemWechatSelectionViewModel;

import java.util.List;

/**
 * Created by sdbean-zlh on 16/5/23.
 */
public class WechatSelectionAdapter extends RecyclerView.Adapter<WechatSelectionAdapter.ViewHolder>{

    private List<WechatSelectionBean.ResultBean.ListBean> list;

    public WechatSelectionAdapter() {

    }

    public WechatSelectionAdapter(List<WechatSelectionBean.ResultBean.ListBean> lists) {
        this.list = lists;
    }

    public void setList(List<WechatSelectionBean.ResultBean.ListBean> lists){
        this.list = lists;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemWechatselectionBinding mItemWechatselectionBinding;
        public ViewHolder(ItemWechatselectionBinding itemWechatselectionBinding) {
            super(itemWechatselectionBinding.itemRelative);
            mItemWechatselectionBinding = itemWechatselectionBinding;

        }

        void bindItem(WechatSelectionBean.ResultBean.ListBean listBean){
            if(mItemWechatselectionBinding.getItemWechatSelection() == null){
                mItemWechatselectionBinding.setItemWechatSelection(new ItemWechatSelectionViewModel(listBean,itemView.getContext()));
            }else{
                mItemWechatselectionBinding.getItemWechatSelection().setItemContent(listBean);
            }
        }
    }

    @Override
    public WechatSelectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemWechatselectionBinding itemWechatselectionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_wechatselection,parent,false);

        return new ViewHolder(itemWechatselectionBinding);
    }

    @Override
    public void onBindViewHolder(WechatSelectionAdapter.ViewHolder holder, int position) {
        holder.bindItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
