package com.example.demo.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.core.api.CommonResult;
import com.example.core.base.AppActivity;
import com.example.core.base.BaseActivity;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.contract.ProductDetailContract;
import com.example.demo.di.component.DaggerProductDetailComponent;
import com.example.demo.mvp.model.entity.PmsBrand;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.example.demo.mvp.model.entity.PmsProduct;
import com.example.demo.mvp.presenter.ProductDetailPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View {
    private Banner banner;
    private TextView sub_title;
    private TextView mPrice;
    private TextView mOriginalPrice;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerProductDetailComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        banner = findViewById(R.id.banner);
        sub_title = findViewById(R.id.sub_title);
        mPrice = findViewById(R.id.price);
        mOriginalPrice = findViewById(R.id.original_price);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        int id = getIntent().getIntExtra("id", -1);
        mPresenter.productDetail(id);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onProductDetailSuccess(CommonResult<PmsPortalProductDetail> result) {
        PmsPortalProductDetail data = result.getData();
        PmsProduct product = data.getProduct();
        String albumPics = product.getAlbumPics();
        if(albumPics == null) return;
        String[] split = albumPics.split(",");
        System.out.println(albumPics);
        banner.setImages(Arrays.stream(split).collect(Collectors.toList()));
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();
        sub_title.setText(product.getSubTitle());
        String original_price = String.valueOf(product.getOriginalPrice());
        SpannableString sp = new SpannableString(original_price);
        sp.setSpan(new StrikethroughSpan(), 0, original_price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mOriginalPrice.setText(sp);
        mPrice.setText(String.valueOf(product.getPrice()));
    }
}
