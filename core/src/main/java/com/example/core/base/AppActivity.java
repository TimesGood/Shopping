package com.example.core.base;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.example.core.base.mvp.IView;
import com.example.core.cache.Cache;
import com.example.core.cache.CacheType;
import com.example.core.util.AppComponentUtils;

import autodispose2.AutoDispose;
import autodispose2.AutoDisposeConverter;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

/**
 * 实现IActivity，规范代码结构
 */
public abstract class AppActivity extends AppCompatActivity implements IActivity {
    private Cache<String, Object> mCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        int layoutId = getLayoutId();
        if(layoutId != 0){setContentView(layoutId);}
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            //noinspection unchecked
            mCache = AppComponentUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }


    @Override
    public boolean useFragment() {
        return true;
    }
}
