package com.liang.pro.notefortravel.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.TravelDetailsActivity;
import com.liang.pro.notefortravel.adapter.TravelAdapter;
import com.liang.pro.notefortravel.database.TravelDB;
import com.liang.pro.notefortravel.model.Travel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTravel extends Fragment  {

    @ViewInject(R.id.travel_listview)
    private ListView lv_list;

   /* @ViewInject(R.id.fresh)
    private PullToRefreshView fresh;*/

    private TravelAdapter travelAdapter;
    private TravelDB travelDB;
    private List list;

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

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Travel travel = (Travel) list.get(position);
                Intent intent = new Intent(getActivity(),TravelDetailsActivity.class);
                intent.putExtra("id",travel.getId());
                intent.putExtra("title",travel.getTitle());
                intent.putExtra("content",travel.getContent());
                intent.putExtra("travelDate",travel.getTravel_time());
                intent.putExtra("place",travel.getDestination());
                intent.putExtra("imgPath",travel.getImg_path());
                startActivity(intent);
            }
        });
    }
    public void showList(){
        travelDB = TravelDB.getInstance(getActivity());
        list = travelDB.loadTravel();
        travelAdapter = new TravelAdapter(getActivity(),list);
        lv_list.setAdapter(travelAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showList();
    }
}
