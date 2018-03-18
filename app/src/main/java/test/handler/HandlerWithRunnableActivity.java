package test.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.efamily.testapp.R;

public class HandlerWithRunnableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_with_runnable);

        findViewById(R.id.btn_runnable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
