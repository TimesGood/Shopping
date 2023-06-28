package com.example.core.base;

import androidx.annotation.Nullable;

import com.example.core.back.IBasePresenter;
import com.example.core.base.mvp.IView;

import javax.inject.Inject;

public abstract class BaseFragment<P extends IBasePresenter>  extends AppFragment<AppActivity> implements IFragment ,IView {
    @Inject
    @Nullable
    protected P mPresenter;//如果页面简单，可为null

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null) {
            mPresenter.onDetach();
        }
    }
}
