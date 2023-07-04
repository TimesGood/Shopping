package com.example.demo.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.core.base.BaseActivity;
import com.example.core.di.component.AppComponent;
import com.example.demo.permission.Permission;
import com.example.demo.R;
import com.example.demo.di.component.DaggerTestComponent;
import com.example.demo.contract.TestContract;
import com.example.demo.mvp.presenter.TestPresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View , View.OnClickListener {

    Button btn_test;
    @Inject
    Permission permission;

    @Override
    public void showLoading() {
        System.out.println("测试加载");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void test() {
        System.out.println("响应结果");
    }

    @Override
    public void setupActivityComponent(@NonNull @NotNull AppComponent appComponent) {
        DaggerTestComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initData(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View view) {
        mPresenter.test();
    }
}