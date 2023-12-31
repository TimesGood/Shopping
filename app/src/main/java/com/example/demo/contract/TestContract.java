package com.example.demo.contract;

import android.app.Activity;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.IModel;
import com.example.core.base.mvp.IView;
import com.example.demo.mvp.model.entity.HomeContentResult;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.example.demo.mvp.model.entity.TokenVo;

import io.reactivex.rxjava3.core.Observable;

public interface TestContract {
    interface Model extends IModel {
        Observable<CommonResult<TokenVo>> test();
        Observable<CommonResult<HomeContentResult>> content();

        Observable<CommonResult<PmsPortalProductDetail>> productDetail(int id);
    }
    interface View extends IView {
        Activity getActivity();

        void onContentSuccess(CommonResult<HomeContentResult> result);

        void onProductDetailSuccess(CommonResult<PmsPortalProductDetail> result);
    }
}
