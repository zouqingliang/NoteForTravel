package com.liang.pro.notefortravel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.model.Account;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class AccountAdapter extends MyAdapter {

    private class ViewHolder{


        @ViewInject(R.id.tv_category)
        private TextView tv_category;

        @ViewInject(R.id.tv_money)
        private TextView tv_money;

        @ViewInject(R.id.tv_create_date)
        private TextView tv_create_date;

        public ViewHolder(View view) {
            x.view().inject(this,view);
        }
    }

    private List list;

    public AccountAdapter(Context Context, List mList) {
        super(Context, mList);
        this.list = mList;
        setList(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = getLayoutInflater().inflate(R.layout.account_listview_item,null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Account account = (Account) getList().get(position);
        viewHolder.tv_category.setText(account.getCategory());
        if (account.getState() == 1){
            viewHolder.tv_money.setText("+ " + account.getMoney());
            viewHolder.tv_money.setTextColor(Color.GREEN);
        }else {
            viewHolder.tv_money.setText("- " + account.getMoney());
            viewHolder.tv_money.setTextColor(Color.RED);
        }
        viewHolder.tv_create_date.setText(account.getAddDate());

        return convertView;
    }
}
