package com.liang.pro.notefortravel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.customView.PullToRefreshView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTool extends Fragment implements PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {


    //当前的页数
    private int page = 1;

    //是否有缓存
    private boolean hasCache;

    public FragmentTool() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tool, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

    }
}
