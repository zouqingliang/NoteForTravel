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
import com.liang.pro.notefortravel.database.TravelDB;
import com.liang.pro.notefortravel.model.Travel;
import com.liang.pro.notefortravel.utils.DateTools;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* Created by Administrator on 2016/4/1.
 */
@ContentView(value = R.layout.add_travel_content)
public class AddTravelContentActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.toolbar_add)
    private Toolbar toolbar;

    @ViewInject(value = R.id.edit_title)
    private EditText edit_title;

    @ViewInject(R.id.edit_content)
    private EditText edit_content;

    @ViewInject(R.id.edit_place)
    private EditText edit_place;

    @ViewInject(R.id.btn_add_img)
    private Button btn_img;

    @ViewInject(R.id.btn_choose_data)
    private Button btn_data;

    @ViewInject(R.id.add_img)
    private ImageView add_img;

    @ViewInject(R.id.textView)
    private TextView text;

    private String travelData;
    private File photoFile;
    private TravelDB travelDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);


        travelDB = TravelDB.getInstance(this);
        btn_img.setOnClickListener(this);
        btn_data.setOnClickListener(this);


        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddContentActivity.this, MainActivity.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTravelContentActivity.this);
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

    public void save(){
        Travel travel = new Travel();
        travel.setTitle(edit_title.getText().toString());
        travel.setContent(edit_content.getText().toString());
        travel.setWrite_time(DateTools.getWriteDate());
        travel.setTravel_time(btn_data.getText().toString());
        travel.setImg_path(photoFile + "");
        travel.setDestination(edit_place.getText().toString());
        travelDB.saveTravel(travel);
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
