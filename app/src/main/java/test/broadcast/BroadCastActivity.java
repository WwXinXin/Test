package test.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.efamily.testapp.R;

public class BroadCastActivity extends AppCompatActivity {

    private MyBroadCastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        findViewById(R.id.btn_broad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("test.broadcast.MyBroadCastReceiver_MY");
                sendBroadcast(intent);
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        mReceiver = new MyBroadCastReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("test.broadcast.MyBroadCastReceiver_MY");
//        registerReceiver(mReceiver, filter);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        unregisterReceiver(mReceiver);
//    }
}
