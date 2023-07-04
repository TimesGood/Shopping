package com.example.core.base;

import androidx.annotation.Nullable;

import com.example.core.base.mvp.IPresenter;
import com.example.core.base.mvp.IView;

import javax.inject.Inject;

public abstract class BaseFragment<P extends IPresenter>  extends AppFragment<AppActivity> implements IFragment ,IView {
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
