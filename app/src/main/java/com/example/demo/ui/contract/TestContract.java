package com.example.demo.ui.contract;

import android.app.Activity;

import com.example.core.base.mvp.IModel;
import com.example.core.base.mvp.IView;

public interface TestContract {
    interface Model extends IModel {
        String test();
    }
    interface View extends IView {
        void test();
        Activity getActivity();
    }
}
