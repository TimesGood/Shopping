package com.example.demo.ui.presenter;

import com.example.core.base.mvp.BasePresenter;
import com.example.core.di.scope.ActivityScope;
import com.example.demo.ui.contract.TestContract;

import javax.inject.Inject;

@ActivityScope
public class TestPresenter extends BasePresenter<TestContract.View, TestContract.Model> {

    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View view) {
        super(model, view);
    }

    public String test(){
        mView.showLoading();
        mView.test();
        return mModel.test();
    }
}
