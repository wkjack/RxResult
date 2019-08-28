package com.wkjack.rxresult.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AndroidXActivity extends AppCompatActivity {
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_x);

        findViewById(R.id.androidX_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("showContent", type == 1 ? "AndroidX-观察者返回内容": "AndroidX-回调返回内容");
                setResult(Activity.RESULT_OK, intent);
                AndroidXActivity.this.finish();
            }
        });

        type = getIntent().getIntExtra("type", 0);
    }
}
