package com.liang.pro.notefortravel.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.base.BaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/1.
 */
public class AddTravelContentActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText edit_title, edit_content, edit_place;
    private Button btn_img, btn_data;
    private ImageView add_img;
    private TextView text;
    private String travelData;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_travel_content);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_content = (EditText) findViewById(R.id.edit_content);
        edit_place = (EditText) findViewById(R.id.edit_place);
        add_img = (ImageView) findViewById(R.id.add_img);
        btn_img = (Button) findViewById(R.id.btn_add_img);
        text = (TextView) findViewById(R.id.textView);
        btn_data = (Button) findViewById(R.id.btn_choose_data);

        btn_img.setOnClickListener(this);
        btn_data.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddContentActivity.this, MainActivity.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTravelContentActivity.this);
                builder.setTitle("提示");
                builder.setMessage("未保存内容，是否离开");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setPositiveButton("取消", null);
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
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
            case R.id.action_delete:
                finish();
                break;
            case R.id.action_save:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_img:
                //拍摄相片
                File path = new File(Environment.getExternalStorageDirectory() + "/image/");
                if (!path.exists()) {
                    path.mkdir();
                }
                Intent intent_img = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoFile = new File(path, getWriteTime() + ".jpg");
                intent_img.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(intent_img, 1);
                break;

            case R.id.btn_choose_data:
                //通过日期选择器获取日期
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(AddTravelContentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        travelData = String.format(" %d-%d-%d ", year, (monthOfYear + 1), dayOfMonth);
                        btn_data.setText(travelData);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    public String getWriteTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String writeDate = format.format(date);
        return writeDate;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            btn_img.setVisibility(View.GONE);
            add_img.setVisibility(View.VISIBLE);
            add_img.setImageBitmap(bitmap);
            text.setText("就是这张了");
        }
    }
}
