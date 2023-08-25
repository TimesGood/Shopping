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
package com.example.core.di.component;

import android.app.Application;

import com.example.core.base.delegate.ConfigModule;
import com.example.core.di.module.AppModule;
import com.example.core.di.module.ClientModule;
import com.example.core.di.module.GlobalConfigModule;
import com.example.core.base.delegate.AppDelegate;
import com.example.core.cache.Cache;
import com.example.core.net.IRepositoryManager;
import com.google.gson.Gson;

import java.io.File;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.OkHttpClient;

import javax.inject.Singleton;

/**
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
public interface AppComponent {

    /**
     * 缓存的工厂
     * @return
     */
    Cache.Factory cacheFactory();
    /**
     * 用于管理网络请求层, 以及数据缓存层
     */
    IRepositoryManager repositoryManager();
    /**
     * 网络请求框架
     */
    OkHttpClient okHttpClient();
    /**
     * Json 序列化库
     */
    Gson gson();
    /**
     * 缓存文件根目录 (RxCache 和 Glide 的缓存都已经作为子文件夹放在这个根目录下), 应该将所有缓存都统一放到这个根目录下
     * 便于管理和清理, 可在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 种配置
     */
    File cacheFile();
    /**
     * 用来存取一些整个 App 公用的数据, 切勿大量存放大容量数据, 这里的存放的数据和 {@link Application} 的生命周期一致
     * @return {@link Cache}
     */
    Cache<String, Object> extras();

    void inject(AppDelegate delegate);
    @Component.Builder
    interface Builder {
        /**
         * 初始化时提供Application
         * @param application
         * @return
         */
        @BindsInstance
        Builder application(Application application);

        Builder globalConfigModule(GlobalConfigModule globalConfigModule);

        AppComponent build();
    }
}
