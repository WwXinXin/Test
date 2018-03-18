package bitmap.scroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.efamily.testapp.R;

public class ScrollTwoActiivty extends AppCompatActivity {

    private SmoothScrollView mSmoothScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_two_actiivty);

        mSmoothScrollView = (SmoothScrollView) findViewById(R.id.sv);

        mSmoothScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothScrollView.smoothScroll(-100,-100,1000);
                startActivity(new Intent(ScrollTwoActiivty.this, ScrollActiivty.class));

            }
        });
    }
}
