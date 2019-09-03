package com.wkjack.rxresult.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
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
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
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
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
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

        findViewById(R.id.support_callback_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 0);

//                Intent intent = new Intent(MainActivity.this, RouterActivity.class);
//                intent.putExtra("type", 0);


                RxResult.in(MainActivity.this)
                        .start(postcard, new RxResultCallback() {
                            @Override
                            public void onResult(RxResultInfo resultInfo) {
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
                            }
                        });
            }
        });

        findViewById(R.id.support_obser_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 1);

                RxResult.in(MainActivity.this)
                        .start(postcard)
                        .subscribe(new Observer<RxResultInfo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RxResultInfo resultInfo) {
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
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
    }
}
