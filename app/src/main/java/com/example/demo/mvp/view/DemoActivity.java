package com.example.demo.mvp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.core.base.AppActivity;
import com.example.core.di.component.AppComponent;
import com.example.demo.test.DaggerDemoComponent;
import com.example.demo.R;
import com.example.demo.test.DemoDaggerBase;

import javax.inject.Inject;

public class DemoActivity extends AppCompatActivity {

    private Button btn_demo;
    @Inject
    DemoDaggerBase mDemoDaggerBaseA;
    @Inject
    DemoDaggerBase mDemoDaggerBaseB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_demo);
        DaggerDemoComponent
                .create()
                .inject(this);
        btn_demo = findViewById(R.id.btn_demo);
        btn_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("mDemoDaggerBaseA对象地址:"+mDemoDaggerBaseA);
                System.out.println("mDemoDaggerBaseB对象地址:"+mDemoDaggerBaseB);
                System.out.println("测试:"+mDemoDaggerBaseB);
            }
        });
        super.onCreate(savedInstanceState);
    }
}