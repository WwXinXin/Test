package bitmap.color;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.efamily.testapp.R;

public class ColorActivity extends AppCompatActivity {
    private LinearLayout ll;
    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        ll = (LinearLayout) findViewById(R.id.ll_anim);
        anim = (AnimationDrawable) ll.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
