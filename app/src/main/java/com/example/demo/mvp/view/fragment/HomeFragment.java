package com.example.demo.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.core.base.AppFragment;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.mvp.view.ImageSelectActivity;
import com.example.ext.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面
 */
public class HomeFragment extends AppFragment{
    private Button test_button;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        test_button = (Button) findViewById(R.id.test_button);
        System.out.println("测试000000000000000000000000000000000000000000000000000000000000000000");
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("88888888888888888888888888888");
                startActivity(new Intent(getContext(), ImageSelectActivity.class));
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
