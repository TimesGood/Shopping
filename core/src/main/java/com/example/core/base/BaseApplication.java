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
package com.example.core.base;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.example.core.di.component.AppComponent;
import com.example.core.base.delegate.AppDelegate;
import com.example.core.util.AppComponentUtils;
import com.example.core.util.Preconditions;

/**
 * 框架拓展，如果想研究本框架的设计流程，请从该类开始着手
 */
public class BaseApplication extends Application implements App {
    private AppDelegate mAppDelegate;

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null) {
            this.mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);
    }

    /**
     * 程序创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null) {
            this.mAppDelegate.onCreate(this);
        }
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null) {
            this.mAppDelegate.onTerminate(this);
        }
    }
    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法所返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see AppComponentUtils#obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        Preconditions.checkState(mAppDelegate instanceof App, "%s must be implements %s", mAppDelegate.getClass().getName(), App.class.getName());
        return ((App) mAppDelegate).getAppComponent();
    }

}
