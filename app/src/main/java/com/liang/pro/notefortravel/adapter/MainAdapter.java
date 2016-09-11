package com.liang.pro.notefortravel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liang.pro.notefortravel.activity.fragment.FragmentAccount;
import com.liang.pro.notefortravel.activity.fragment.FragmentTool;
import com.liang.pro.notefortravel.activity.fragment.FragmentTravel;

public class MainAdapter extends FragmentPagerAdapter {

    private int size;
    public MainAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        if (position > size){
            return null;
        }
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentTravel();
                break;
            case 1:
                fragment = new FragmentAccount();
                break;
            case 2:
                fragment = new FragmentTool();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return size;
    }
}
