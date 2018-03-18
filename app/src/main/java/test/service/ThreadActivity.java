package test.service;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.efamily.testapp.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHandler;
    private TextView tvHandler;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvHandler.setText("dasdadas");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initView();

//        new MyThread().start();
//
//        MyThread2 myThread2 = new MyThread2();
//        new Thread(myThread2).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //执行耗时操作
//            }
//        }).start();
    }

    private void initView() {

        tvHandler = (TextView) findViewById(R.id.tv_handler);
        btnHandler = (Button) findViewById(R.id.btn_handler);
        btnHandler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //mHandler.sendEmptyMessage(1);

                        Message msg = new Message();
                        //Message message = Message.obtain();
                        mHandler.sendMessage(msg);
                    }
                }).start();
                break;
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            //执行耗时操作
        }
    }

    class MyThread2 implements Runnable {
        @Override
        public void run() {
            //执行耗时操作
        }
    }
}
