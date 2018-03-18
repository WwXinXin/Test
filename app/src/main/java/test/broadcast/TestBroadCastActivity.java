package test.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.efamily.testapp.R;

public class TestBroadCastActivity extends AppCompatActivity {

    private MyBroadCastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broad_casr);

        findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送普通广播
                Intent intent = new Intent();
                intent.setAction("test.broadcast.MyBroadCastReceiver_NORMAL");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver = new MyBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("test.broadcast.MyBroadCastReceiver_NORMAL");
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
