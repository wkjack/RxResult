package com.wkjack.rxresult.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wkjack.rxresult.RxResult;
import com.wkjack.rxresult.RxResultCallback;
import com.wkjack.rxresult.RxResultInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTv = findViewById(R.id.showTv);

        findViewById(R.id.support_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                intent.putExtra("type", 0);

                RxResult.in(MainActivity.this)
                        .start(intent, new RxResultCallback() {
                            @Override
                            public void onResult(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                String showContent = data.getStringExtra("showContent");
                                showTv.setText(showContent);
                            }
                        });
            }
        });

        findViewById(R.id.support_obser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                intent.putExtra("type", 1);

                RxResult.in(MainActivity.this)
                        .start(intent)
                        .subscribe(new Observer<RxResultInfo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                String showContent = data.getStringExtra("showContent");
                                showTv.setText(showContent);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        findViewById(R.id.androidX_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.androidX_obser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
