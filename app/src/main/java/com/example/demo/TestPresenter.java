package com.example.demo;

import com.example.demo.base.mvp.BasePresenter;
import com.example.demo.base.mvp.IBaseModel;
import com.example.demo.base.mvp.IBaseView;

public class TestPresenter extends BasePresenter<TestView,TestModel> {

    public TestPresenter(TestModel testModel) {
        super(testModel);
    }

    public String test(){
        mView.showLoading();
        mView.test();
        return mModel.test();
    }
}
