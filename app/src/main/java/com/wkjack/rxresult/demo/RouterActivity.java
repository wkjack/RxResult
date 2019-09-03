package com.wkjack.rxresult.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/aaa/bbb")
public class RouterActivity extends AppCompatActivity {

    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("showContent", type == 1 ? "Arouter观察者返回数据" : "Arouter回调返回数据");
                setResult(RESULT_OK, intent);
                RouterActivity.this.finish();
            }
        });

        type = getIntent().getIntExtra("type", 0);
    }
}
