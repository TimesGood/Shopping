package com.example.demo.ui.contract;

import com.example.demo.base.BaseModel;
import com.example.core.base.mvp.IBaseView;

public interface TestContract {
    interface Model extends BaseModel {
        String test();
    }
    interface View extends IBaseView {
        void test();
    }
}
