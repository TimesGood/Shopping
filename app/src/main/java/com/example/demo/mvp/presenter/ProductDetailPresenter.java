package com.example.demo.mvp.presenter;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.BasePresenter;
import com.example.core.di.scope.ActivityScope;
import com.example.core.net.RxScheduler;
import com.example.demo.contract.ProductDetailContract;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@ActivityScope
public class ProductDetailPresenter extends BasePresenter<ProductDetailContract.View, ProductDetailContract.Model> {

    @Inject
    public ProductDetailPresenter(ProductDetailContract.Model model, ProductDetailContract.View view) {
        super(model, view);
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
