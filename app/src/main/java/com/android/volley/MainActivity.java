package com.android.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.android.volley.R.id.textView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(textView);
        Button btnRequest = (Button) findViewById(R.id.btnRequest);

        mQueue = Volley.newRequestQueue(this);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

    }

    private void request() {
        StringRequest stringRequest = new StringRequest("https://www.baidu.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                mTextView.setText(System.currentTimeMillis() + " " + response);
            }
        });

        mQueue.add(stringRequest);
    }


}
