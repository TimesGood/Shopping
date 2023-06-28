package com.example.core.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.example.core.base.mvp.IView;
import com.example.core.cache.Cache;
import com.example.core.cache.CacheType;
import com.example.core.util.AppComponentUtils;

import org.jetbrains.annotations.NotNull;

import autodispose2.AutoDispose;
import autodispose2.AutoDisposeConverter;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

public abstract class AppFragment<A extends AppActivity>  extends Fragment implements IFragment ,IView {

    private A mActivity;
    private View mView;
    private Cache<String, Object> mCache;
    /**
     * 获得全局Activity
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mActivity = (A) requireActivity();
    }

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(mActivity.getLayoutId(), container, false);
        return mView;
    }
    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            //noinspection unchecked
            mCache = AppComponentUtils.obtainAppComponentFromContext(mActivity).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }
    @Override
    public <P> AutoDisposeConverter<P> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }

}
