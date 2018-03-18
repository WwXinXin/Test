package bitmap.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.efamily.testapp.R;

public class ScrollActiivty extends AppCompatActivity implements View.OnClickListener {

    private Button btnOne;
    private Button btnTwo;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_actiivty);

        ll = (LinearLayout) findViewById(R.id.ll);
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_second);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                ll.scrollTo(100, 100);
                break;
            case R.id.btn_second:
                ll.scrollBy((int) getResources().getDimension(R.dimen.textSize), 0);
                break;

        }
    }
}
