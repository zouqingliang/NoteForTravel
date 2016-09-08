package com.liang.pro.notefortravel.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.adapter.MainAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.rg_main)
    private RadioGroup radioGroup;

    @ViewInject(R.id.layout_tv)
    private LinearLayout linearLayout;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;

    private int size = 3;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkID) {
                for (int i = 0; i < size; i++){
                    TextView textView = (TextView) linearLayout.getChildAt(i);
                    textView.setBackgroundColor(getResources().getColor(R.color.transparent));

                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()){
                        textView.setBackgroundColor(getResources().getColor(R.color.holo_blue_dark));
                        viewPager.setCurrentItem(i);
                    }
                }
            }
        });

        mainAdapter = new MainAdapter(getSupportFragmentManager(),size);
        viewPager.setAdapter(mainAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}