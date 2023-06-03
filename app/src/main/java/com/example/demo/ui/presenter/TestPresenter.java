package com.example.demo.ui.presenter;

import com.example.core.base.mvp.BasePresenter;
import com.example.demo.ui.contract.TestContract;
import com.example.demo.ui.model.TestModel;

public class TestPresenter extends BasePresenter<TestContract.View, TestContract.Model> {

    public TestPresenter(TestModel testModel) {
        super(testModel);
    }

    public String test(){
        mView.showLoading();
        mView.test();
        return mModel.test();
    }
}
