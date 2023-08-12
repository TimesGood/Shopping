package com.example.core.base;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

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
public abstract class BaseFragment<P extends IPresenter>  extends AppFragment<AppActivity> implements IView {
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

    @Override
    public <P> AutoDisposeConverter<P> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
