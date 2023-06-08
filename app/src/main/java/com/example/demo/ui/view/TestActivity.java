package com.example.demo.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.core.permission.Permission;
import com.example.core.permission.Permissions;
import com.example.core.test.component.AppComponent;
import com.example.demo.R;
import com.example.demo.ui.component.DaggerTestComponent;
import com.example.demo.ui.contract.TestContract;
import com.example.demo.ui.model.TestModel;
import com.example.demo.ui.presenter.TestPresenter;
import com.example.core.base.ui.BaseActivity;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View {

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
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(new TestModel());
    }

    @Override
    public void onClick(View v) {
        System.out.println(permission);
        mPresenter.test();
        super.onClick(v);
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
    public int initView(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.activity_test;
    }

    @Override
    public void initData(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        btn_test = findViewById(R.id.btn_test);
        setOnClickListener(btn_test);
    }
    @Override
    public Activity getActivity() {
        return this;
    }
}