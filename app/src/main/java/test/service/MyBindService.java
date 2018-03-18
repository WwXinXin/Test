package test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBindService extends Service {

    public class MyBinder extends Binder {

        public MyBindService getService() {
            return MyBindService.this;
        }

        public void getLog() {
            Log.e("111", "MyBinder");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("111", "onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("111", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("111", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("111", "onDestroy");
    }
}
