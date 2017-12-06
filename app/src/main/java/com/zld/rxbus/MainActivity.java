package com.zld.rxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    Button btn;
    TextView tv;

    Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        observable = RxBus.getInstance().register(TAG);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tv.setText(s);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getInstance().post(TAG,"哈哈哈");
            }
        });
    }

    @Override
    protected void onDestroy() {
        RxBus.getInstance().unregister(TAG,observable);
        super.onDestroy();
    }
}
