package com.liang.pro.notefortravel.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.base.BaseActivity;
import com.liang.pro.notefortravel.database.AccountDB;
import com.liang.pro.notefortravel.model.Account;
import com.liang.pro.notefortravel.utils.DateTools;
import com.liang.pro.notefortravel.utils.KeyboardUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.add_account)
public class AddAccountActivity extends BaseActivity{

    @ViewInject(R.id.edit_category)
    EditText edit_category;

    @ViewInject(R.id.ck_input)
    CheckBox ck_input;

    @ViewInject(R.id.ck_output)
    CheckBox ck_output;

    @ViewInject(R.id.edit_money)
    EditText edit_money;

    @ViewInject(R.id.toolbar_add_account)
    Toolbar toolbar;

    private Account account;
    private AccountDB accountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);


        accountDB = AccountDB.getInstance(this);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                builder.setTitle("提示");
                builder.setMessage("未保存内容，是否离开");
                builder.setPositiveButton("取消", null);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        edit_money.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //这样是在触摸到控件时，软键盘才会显示出来
                int inputback = edit_money.getInputType();
                edit_money.setInputType(InputType.TYPE_NULL);
                new KeyboardUtil(AddAccountActivity.this, AddAccountActivity.this, edit_money).showKeyboard();
                edit_money.setInputType(inputback);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addtravel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                finish();
                break;
        }
        return true;
    }

    private void save() {
        account = new Account();
        account.setCategory(edit_category.getText().toString().trim());
        if (ck_input.isChecked()){
            account.setState(1); //1表示收入
        } else if (ck_output.isChecked()){
            account.setState(2); //2表示支出
        }
        account.setAddDate(DateTools.getWriteTime());
        account.setMoney(Float.parseFloat(edit_money.getText().toString().trim()));

        accountDB.saveAccount(account);
    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //屏蔽后退键
        if(KeyEvent.KEYCODE_BACK == event.getKeyCode())
        {
            return true;//阻止事件继续向下分发
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
