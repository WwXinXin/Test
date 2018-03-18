package test.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.efamily.testapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConActivity extends AppCompatActivity {

    private TextView mTvNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_con);

        mTvNet = (TextView) findViewById(R.id.tv_net);

        findViewById(R.id.btn_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequsetWithHttpConnect();
            }
        });
    }

    private void sendRequsetWithHttpConnect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);

                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    showResponse(builder.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvNet.setText(str);
            }
        });
    }
}
