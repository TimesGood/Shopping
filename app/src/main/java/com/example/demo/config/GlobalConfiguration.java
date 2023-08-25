package com.example.demo.config;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.core.base.delegate.AppLifecycles;
import com.example.core.base.delegate.ConfigModule;
import com.example.core.di.module.ClientModule;
import com.example.core.di.module.GlobalConfigModule;

import java.util.List;

import okhttp3.OkHttpClient;

public final class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {
        builder.baseurl("http://192.168.31.69:8081/");
    }

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {

    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}