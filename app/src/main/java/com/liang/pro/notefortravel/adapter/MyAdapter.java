package com.liang.pro.notefortravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyAdapter extends BaseAdapter {

    private List mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context Context, List List) {
        this.mList = List;
        this.mContext = Context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public Context getContext() {
        return mContext;
    }

    public List getList() {
        return mList;
    }

    public void setList(List List) {
        this.mList = List;
    }

    public LayoutInflater getLayoutInflater(){
        return mLayoutInflater;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


}
