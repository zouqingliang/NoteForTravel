package com.liang.pro.notefortravel.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.base.BaseActivity;
import com.liang.pro.notefortravel.database.TravelDB;
import com.liang.pro.notefortravel.shareweibo.AccessTokenKeeper;
import com.liang.pro.notefortravel.shareweibo.Constants;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;


/**
 * Created by Administrator on 2016/4/1.
 */
public class TravelDetailsActivity extends BaseActivity implements IWeiboHandler.Response{


    private String mk="a1ea204d483322759d27e93679a33c42";

    private Toolbar toolbar;
    private TextView title_text, travel_time, content_text, place_text;
    private ImageView image;
    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    /** 微博分享的接口实例 */
    private IWeiboShareAPI mWeiboShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title_text = (TextView) findViewById(R.id.title_text);
        travel_time = (TextView) findViewById(R.id.time_text);
        content_text = (TextView) findViewById(R.id.content_text);
        place_text = (TextView) findViewById(R.id.place_text);
        image = (ImageView) findViewById(R.id.img);

        title_text.setText(getIntent().getStringExtra("title"));
        travel_time.setText("旅行时间 " + getIntent().getStringExtra("travelDate"));
        content_text.setText(getIntent().getStringExtra("content"));
        place_text.setText("地点：" + getIntent().getStringExtra("place"));
        if (getIntent().getStringExtra("imgPath").equals("null")) {
           image.setImageResource(R.drawable.img_default);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("imgPath"));
            image.setImageBitmap(bitmap);
        }

        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
        mWeiboShareAPI.registerApp(); // 将应用注册到微博客户端

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_travel_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Delete();
                finish();
                break;
            case R.id.action_share:
                weiboShare();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Delete() {
        TravelDB.getInstance(this).deleteTravel(getIntent().getIntExtra("id",0));
        showMsg("已删除");
    }

    /*
        * 本地分享
        * */
    public void nativeShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra("title", getIntent().getStringExtra("title"));
        startActivity(Intent.createChooser(intent, "分享到"));
    }

    /*
    * 新浪微博分享
    * */
    public void weiboShare() {
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);
        /*mAccessToken = AccessTokenKeeper.readAccessToken(TravelDetailsActivity.this);
        if (mAccessToken.isSessionValid()){
            sendMultiMessage();
        } else {
            mSsoHandler = new SsoHandler(TravelDetailsActivity.this, mAuthInfo);

            mSsoHandler.authorize(new AuthListener());
        }*/
        mSsoHandler = new SsoHandler(TravelDetailsActivity.this, mAuthInfo);

        mSsoHandler.authorize(new AuthListener());
    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }

    private String getSharedText() {
        return getIntent().getStringExtra("title");
    }

    private void sendMultiMessage() {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();//初始化微博的分享消息

            weiboMessage. textObject = getTextObj();

        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        mWeiboShareAPI.sendRequest(TravelDetailsActivity.this,request); //发送请求消息到微博，唤起微博分享界面
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWeiboShareAPI.handleWeiboResponse(intent, this); //当前应用唤起微博分享后，返回当前应用
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {

        if(baseResponse!= null){
            switch (baseResponse.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    Toast.makeText(this,
                            getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: " + baseResponse.errMsg,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle bundle) {

            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);

                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(TravelDetailsActivity.this, mAccessToken);
                Toast.makeText(TravelDetailsActivity.this,
                        "授权成功", Toast.LENGTH_SHORT).show();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = bundle.getString("code");
                String message = "授权失败";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(TravelDetailsActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(TravelDetailsActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(TravelDetailsActivity.this, "取消授权", Toast.LENGTH_LONG).show();
        }
    }

}