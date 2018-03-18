package test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2018/1/4.
 */

public class AlarmService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("111", "开启定时任务");
            }
        }).start();

        /**
         * SystemClock.elapsedRealtime()
         * 可以获取系统开机至今所经历时间的毫秒数
         * System.currentTimeMillis()
         * 可以获取到1970年1月1日0点到至今经历时间的毫秒数
         *
         * ELAPSED_REALTIME_WAKEUP 配合SystemClock.elapsedRealtime()
         * 让定时任务的触发时间从系统开机开始算起，会唤醒CPU
         * RTC_WAKEUP 配合System.currentTimeMillis()
         * 让定时任务的触发时间从1970年1月1日0点开始算起，会唤醒CPU
         *
         */
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //1s时间
        int time = 1000 * 5;
        //5秒后
        long triggerAtTime = SystemClock.elapsedRealtime() + time;
        Intent itent = new Intent(this, AlarmService.class);
        PendingIntent pItent = PendingIntent.getService(this, 0, itent, 0);
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pItent);

        return super.onStartCommand(intent, flags, startId);
    }
}
