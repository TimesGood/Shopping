package com.example.demo.mvp.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.common.util.IntentUtils;
import com.example.core.api.CommonResult;
import com.example.core.base.BaseFragment;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.contract.TestContract;
import com.example.demo.di.component.DaggerTestComponent;
import com.example.demo.mvp.model.entity.HomeContentResult;
import com.example.demo.mvp.model.entity.PmsBrand;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.example.demo.mvp.model.entity.PmsProduct;
import com.example.demo.mvp.presenter.TestPresenter;
import com.example.demo.mvp.view.DemoActivity;
import com.example.demo.mvp.view.ImageSelectActivity;
import com.example.demo.mvp.view.ProductDetailActivity;
import com.example.demo.mvp.view.adapter.CommonAdapter;
import com.example.demo.mvp.view.adapter.ImageAdapter;
import com.example.demo.permission.Permission;
import com.example.core.permission.DefaultPermissionObserver;
import com.example.core.permission.PermissionUtil;
import com.example.demo.permission.PermissionObserver;
import com.example.ext.adapter.BaseAdapter;
import com.example.ext.dialog.MessageDialog;
import com.example.ext.viewgroup.RecyclerViewDecorator;
import com.example.ext.viewgroup.StatusLayout;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

/**
 * 首页界面
 */
public class HomeFragment extends BaseFragment<TestPresenter> implements TestContract.View,BaseAdapter.OnItemClickListener{
    private RecyclerViewDecorator recyclerview;
    private ImageAdapter adapter;
    private StatusLayout statusLayout;

    private Banner banner;

    @Inject
    Permission permission;

    public HomeFragment() {
    }

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
        recyclerview = (RecyclerViewDecorator) findViewById(R.id.recyclerview);
        statusLayout = findViewById(R.id.status_layout);
//        banner = findViewById(R.id.banner);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.content();
        statusLayout.setAnimResource(com.example.expand.R.raw.loading);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
        statusLayout.show();
    }

    @Override
    public void hideLoading() {
        statusLayout.hide();
    }

    /**
     * 首页数据条目点击事件
     * @param recyclerView
     * @param v
     * @param position
     */
    @Override
    public void onItemClick(RecyclerView recyclerView, View v, int position) {
//        mPresenter.productDetail(v.getId());
        Intent intent = new Intent(getContext(),ProductDetailActivity.class);
        intent.putExtra("id",v.getId());
        startActivity(intent);
    }

    /**
     * 首页数据请求成功处理
     * @param result
     */
    @Override
    public void onContentSuccess(CommonResult<HomeContentResult> result) {
        List<PmsBrand> brandList = result.getData().getBrandList();
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_head_banner,null,false);
        banner = layout.findViewById(R.id.banner);
        banner.setImages(brandList);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(((PmsBrand)path).getBigPic()).into(imageView);
            }
        });
        List<PmsProduct> hotProductList = result.getData().getHotProductList();
        adapter = new ImageAdapter(getContext());
        adapter.setData(hotProductList);
        adapter.setOnItemClickListener(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerview.addHeaderView(layout);
        recyclerview.adjustSpanSize();
        banner.start();
    }

    @Override
    public void onProductDetailSuccess(CommonResult<PmsPortalProductDetail> result) {

    }
}
