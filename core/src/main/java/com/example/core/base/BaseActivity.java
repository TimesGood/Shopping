package com.example.core.base;

import androidx.annotation.Nullable;

import com.example.core.base.mvp.BasePresenter;
import com.example.core.base.mvp.IPresenter;

import javax.inject.Inject;

public abstract class BaseActivity<P extends IPresenter>  extends AppActivity{
    @Inject
    @Nullable
    protected P mPresenter;//如果页面简单，可为null

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();//释放资源
        }
        this.mPresenter = null;
    }
}
