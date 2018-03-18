package test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

public class MyStartService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 启动该服务会调用1次
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 每次启动服务都会调用
     * 执行耗时逻辑
     * */
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 回收不使用的资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
