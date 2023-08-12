package com.example.core.base;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import com.example.core.base.mvp.BasePresenter;
import com.example.core.base.mvp.IPresenter;
import com.example.core.base.mvp.IView;

import javax.inject.Inject;

import autodispose2.AutoDispose;
import autodispose2.AutoDisposeConverter;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

/**
 * MVP设计，提供P层实现
 * @param <P>
 */
public abstract class BaseActivity<P extends IPresenter>  extends AppActivity implements IView {
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

    @Override
    public <P> AutoDisposeConverter<P> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
