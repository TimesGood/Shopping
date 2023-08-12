package com.example.demo.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.core.base.AppFragment;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;

/**
 * 首页界面
 */
public class Page2Fragment extends AppFragment{

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_page2;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
