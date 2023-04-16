package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.base.ui.BaseActivity;

public class TestActivity extends BaseActivity<TestPresenter> implements TestView{

    Button btn_test;

    @Override
    public void showLoading() {
        System.out.println("测试加载");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        btn_test = findViewById(R.id.btn_test);
        setOnClickListener(btn_test);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(new TestModel());
    }

    @Override
    public void onClick(View v) {
        mPresenter.test();
        super.onClick(v);
    }

    @Override
    public void test() {
        System.out.println("响应结果");
    }
}