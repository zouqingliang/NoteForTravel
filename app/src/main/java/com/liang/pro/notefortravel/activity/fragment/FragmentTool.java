package com.liang.pro.notefortravel.activity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.WeatherActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


public class FragmentTool extends Fragment {

    @ViewInject(R.id.btn_weather)
    private Button btn_weather;

    public FragmentTool() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tool, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        x.view().inject(this, view);
    }

    @Event(R.id.btn_weather)
    private void doClick(View view) {
        Intent i = new Intent(getActivity(), WeatherActivity.class);
        startActivity(i);
    }

}
