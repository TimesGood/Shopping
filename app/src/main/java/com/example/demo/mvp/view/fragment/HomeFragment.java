package com.example.demo.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.core.base.AppFragment;
import com.example.core.base.BaseFragment;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.contract.TestContract;
import com.example.demo.di.component.DaggerTestComponent;
import com.example.demo.mvp.presenter.TestPresenter;
import com.example.demo.mvp.view.DemoActivity;
import com.example.demo.mvp.view.ImageSelectActivity;
import com.example.demo.mvp.view.adapter.CommonAdapter;
import com.example.ext.adapter.BaseAdapter;
import com.example.ext.viewgroup.RecyclerViewDecorator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 首页界面
 */
public class HomeFragment extends BaseFragment<TestPresenter> implements TestContract.View,BaseAdapter.OnItemClickListener{
    private RecyclerViewDecorator recyclerview;
    private CommonAdapter adapter;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTestComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
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
        adapter = new CommonAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        recyclerview = (RecyclerViewDecorator) findViewById(R.id.recyclerview);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<String> listData = new ArrayList<>();
        listData.add("查看手机图片");
        listData.add("测试2");
        listData.add("测试3");
        listData.add("测试4");
        listData.add("测试5");
        listData.add("测试6");
        listData.add("测试7");
        listData.add("测试8");
        listData.add("测试9");
        adapter.setData(listData);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void onItemClick(RecyclerView recyclerView, View v, int position) {
        switch (v.getId()){
            case 0:
                startActivity(new Intent(getContext(), ImageSelectActivity.class));
                break;
            case 1:
                mPresenter.test();
                System.out.println("测试-----------------："+Environment.getExternalStorageDirectory().getPath()+getContext().getPackageName());
                break;
            case 2:
                startActivity(new Intent(getContext(), DemoActivity.class));
                break;

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
