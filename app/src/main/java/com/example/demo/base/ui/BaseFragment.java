package com.example.demo.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import autodispose2.AutoDispose;
import autodispose2.AutoDisposeConverter;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import com.example.demo.base.IBaseView;
import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<P extends BasePresenter,V extends IBaseView> extends AppFragment<AppActivity> implements IBaseView{
    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this);
        return super.onCreateView(inflater,container,savedInstanceState);
    }
    /**
     * 创建对应的P层实体对象
     * @return new Presenter实体对象
     */
    protected abstract P createPresenter();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     */
    @Override
    public <P> AutoDisposeConverter<P> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
