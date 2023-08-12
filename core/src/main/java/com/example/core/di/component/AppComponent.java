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

import com.example.core.di.module.AppModule;
import com.example.core.di.module.ClientModule;
import com.example.core.di.module.GlobalConfigModule;
import com.example.core.base.delegate.AppDelegate;
import com.example.core.cache.Cache;
import com.example.core.net.IRepositoryManager;

import dagger.BindsInstance;
import dagger.Component;

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
