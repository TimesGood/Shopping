package com.example.demo.mvp.presenter;

import android.widget.Toast;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.BasePresenter;
import com.example.core.di.scope.ActivityScope;
import com.example.core.net.RxScheduler;
import com.example.demo.contract.TestContract;
import com.example.demo.mvp.model.entity.HomeContentResult;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.example.demo.mvp.model.entity.TokenVo;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@ActivityScope
public class TestPresenter extends BasePresenter<TestContract.View, TestContract.Model> {

    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View view) {
        super(model, view);
    }

    public void test(){
        mView.showLoading();
        mModel.test()
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<CommonResult<TokenVo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("测试1");
                    }

                    @Override
                    public void onNext(@NonNull CommonResult<TokenVo> tokenVoCommonResult) {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("测试2");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void content(){
        mModel.content().compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<CommonResult<HomeContentResult>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull CommonResult<HomeContentResult> result) {
                        mView.onContentSuccess(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    public void productDetail(int id) {
        mModel.productDetail(id).compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<CommonResult<PmsPortalProductDetail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull CommonResult<PmsPortalProductDetail> result) {
                        mView.onProductDetailSuccess(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
