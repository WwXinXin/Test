package test.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.efamily.testapp.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxjavaExActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_ex);

        tv = (TextView) findViewById(R.id.tv_text);
        et = (EditText) findViewById(R.id.et);

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et.getText().toString().trim();

                StringBuffer sb = new StringBuffer();
                String name = "吴鑫";
                String content = "大神大神大神大苏打V型从V型送达大神大神大神大苏打V型从V型送达大神大神大神大苏打V型从V型送" +
                        "达大神大神大神大苏打V型从V型送达大神大神大神大苏打V型从V型送达";

                sb.append("「")
                        .append(name)
                        .append("：")
                        .append(content)
                        .append("」")
                        .append("\n")
                        .append("- - - - - - - - - - - - - - -")
                        .append("\n")
                        .append(input);
                tv.setText(sb.toString());

            }
        });

        initRxjava();
    }

    private void initRxjava() {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onNext("333");
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("111", "开始采用Subscribe进行连接");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("111", "onNext事件响应：" + s);

                //切断被观察者与观察者的连接，onComplete都不会执行
                //if (s.equals("222")) {
                //mDisposable.dispose();
                //}
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("111", "onError事件响应");
            }

            @Override
            public void onComplete() {
                Log.e("111", "onComplete事件响应");
            }
        });
    }
}
