/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.core.base.delegate;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.core.di.module.GlobalConfigModule;

import java.util.List;

/**
 * 给开发者自定义框架内组件的配置
 */
public interface ConfigModule {
    /**
     * 使用 {@link GlobalConfigModule.Builder} 给框架配置一些配置参数
     *
     * @param context {@link Context}
     * @param builder {@link GlobalConfigModule.Builder}
     */
    void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder);
    void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles);

    /**
     * 使用 {@link Application.ActivityLifecycleCallbacks} 在 {@link Activity} 的生命周期中注入一些操作
     *
     * @param context    {@link Context}
     * @param lifecycles {@link Activity} 的生命周期容器, 可向框架中添加多个 {@link Activity} 的生命周期类
     */
    void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles);

    /**
     * 使用 {@link FragmentManager.FragmentLifecycleCallbacks} 在 {@link Fragment} 的生命周期中注入一些操作
     *
     * @param context    {@link Context}
     * @param lifecycles {@link Fragment} 的生命周期容器, 可向框架中添加多个 {@link Fragment} 的生命周期类
     */
    void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles);
}
