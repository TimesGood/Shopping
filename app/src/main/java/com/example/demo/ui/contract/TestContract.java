package com.example.demo.ui.contract;

import com.example.demo.base.mvp.IBaseModel;
import com.example.demo.base.mvp.IBaseView;

public interface TestContract {
    interface Model extends IBaseModel {
        String test();
    }
    interface View extends IBaseView {
        void test();
    }
}
