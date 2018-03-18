package test.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.efamily.testapp.R;

public class ServiceActivity extends AppCompatActivity {

    private MyBindService mBindService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBindService = ((MyBindService.MyBinder) service).getService();
            ((MyBindService.MyBinder) service).getLog();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ServiceActivity.this, MyStartService.class);
//                startService(intent);
                Intent intent = new Intent(ServiceActivity.this, MyBindService.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);


            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ServiceActivity.this, MyStartService.class);
//                stopService(intent);
                if (mBindService != null) {
                    unbindService(mConnection);
                    mBindService = null;
                }
            }
        });

    }
}
