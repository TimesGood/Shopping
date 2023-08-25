package com.example.demo.contract;

import android.app.Activity;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.IModel;
import com.example.core.base.mvp.IView;
import com.example.demo.mvp.model.entity.TokenVo;

import io.reactivex.rxjava3.core.Observable;

public interface TestContract {
    interface Model extends IModel {
        Observable<CommonResult<TokenVo>> test();
    }
    interface View extends IView {
        Activity getActivity();
    }
}
