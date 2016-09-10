package com.liang.pro.notefortravel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.database.TravelDB;
import com.liang.pro.notefortravel.model.Travel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class TravelAdapter extends MyAdapter {

    private class ViewHolder{

        @ViewInject(R.id.img_travel_list)
        private ImageView img_travel;

        @ViewInject(R.id.tv_travel_list)
        private TextView tv_title;

        @ViewInject(R.id.tv_place)
        private TextView tv_place;

        @ViewInject(R.id.tv_date)
        private TextView tv_date;

        public ViewHolder(View view) {
            x.view().inject(this,view);
        }
    }

    public TravelAdapter(Context context) {
        super(context, null);
        TravelDB travelDB = TravelDB.getInstance(context);
        List list = travelDB.loadTravel();
        setList(list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = getLayoutInflater().inflate(R.layout.travle_listview_item,null);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Travel travel = (Travel) getList().get(i);
        if (travel.getImg_path() == null){
            viewHolder.img_travel.setImageResource(R.drawable.img_default);
        }else {
            Bitmap bitmap = BitmapFactory.decodeFile(travel.getImg_path());
            viewHolder.img_travel.setImageBitmap(bitmap);
        }
        viewHolder.tv_title.setText(travel.getTitle());
        viewHolder.tv_place.setText(travel.getDestination());
        viewHolder.tv_date.setText(travel.getWrite_time());

        return view;
    }
}
