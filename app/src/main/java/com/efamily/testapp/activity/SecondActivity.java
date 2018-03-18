package com.efamily.testapp.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.efamily.testapp.MyApplication;
import com.efamily.testapp.R;
import com.efamily.testapp.single.AppManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private TedPermission mTedPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });

        // 传入getApplicationContext()也不会内存溢出
        //AppManager instance = AppManager.getInstance(getApplicationContext());

        mTedPermission = new TedPermission(MyApplication.getInstance().getApplicationContext()).setPermissionListener(new PermissionListener() {
            @Override
            public void onPermissionDenied(ArrayList<String> arrayList) {
            }

            @Override
            public void onPermissionGranted() {
            }
        }).setDeniedMessage(R.string.permission_hint).setDeniedCloseButtonText("拒绝").setGotoSettingButtonText("去设置")
                .setPermissions(Manifest.permission.READ_PHONE_STATE);
        mTedPermission.check();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTedPermission != null) {
            mTedPermission.setPermissionListener(null);
            mTedPermission = null;
        }
    }
}
