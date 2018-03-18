package com.efamily.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.efamily.testapp.R;

public class TestOneActivity extends BaseActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();
    private TextView tvOne;
    private static A a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);

        tvOne = (TextView) findViewById(R.id.tv_one);
        tvOne.setOnClickListener(this);

        a = new A();
        //内存泄漏 处理方法：将dialog对象置null
        //DialogUtil.showNormalDialog(TestOneActivity.this);

//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                DialogUtil.showNormalDialog(TestOneActivity.this);
//            }
//        });
    }

    public class A {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                TestOneActivity.this.finish();
                startActivity(new Intent(TestOneActivity.this, TestSecondActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("111", "执行了");
        //DialogUtil.cancle();
    }
}
