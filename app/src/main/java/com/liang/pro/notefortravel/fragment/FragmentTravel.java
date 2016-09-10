package com.liang.pro.notefortravel.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.adapter.TravelAdapter;
import com.liang.pro.notefortravel.customView.PullToRefreshView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTravel extends Fragment implements PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {

    @ViewInject(R.id.travel_listview)
    private ListView lv_list;

    @ViewInject(R.id.fresh)
    private PullToRefreshView fresh;

    //当前的页数
    private int page = 1;
    private Context context;

    private TravelAdapter travelAdapter;

    public FragmentTravel() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_travel, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        x.view().inject(this,view);

        travelAdapter = new TravelAdapter(getActivity());
        lv_list.setAdapter(travelAdapter);

    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

    }
}
