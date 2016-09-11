package com.liang.pro.notefortravel.activity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.AddAccountActivity;
import com.liang.pro.notefortravel.adapter.AccountAdapter;
import com.liang.pro.notefortravel.database.AccountDB;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class FragmentAccount extends Fragment  {

    @ViewInject(R.id.account_listview)
    private ListView accountListView;

    @ViewInject(R.id.btn_add_account)
    private Button addAccount;

    AccountDB accountDB;
    AccountAdapter accountAdapter;
    private List list;

    public FragmentAccount() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        x.view().inject(this,view);

    }

    @Event(value = R.id.btn_add_account)
    private void doClick(View view){
        Intent intent = new Intent(getActivity(),AddAccountActivity.class);
        startActivity(intent);
    }

    public void showList(){
        accountDB = AccountDB.getInstance(getActivity());
        list = accountDB.loadAccount();
        accountAdapter = new AccountAdapter(getActivity(),list);
        accountListView.setAdapter(accountAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showList();
    }
}
