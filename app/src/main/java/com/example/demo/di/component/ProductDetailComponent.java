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
package com.example.demo.di.component;


import com.example.core.di.component.AppComponent;
import com.example.core.di.scope.ActivityScope;
import com.example.demo.contract.ProductDetailContract;
import com.example.demo.contract.TestContract;
import com.example.demo.di.module.ProductDetailModule;
import com.example.demo.mvp.view.ProductDetailActivity;
import com.example.demo.mvp.view.fragment.HomeFragment;

import dagger.BindsInstance;
import dagger.Component;


/**
 */
@ActivityScope
@Component(modules = ProductDetailModule.class, dependencies = AppComponent.class)
public interface ProductDetailComponent {
    void inject(ProductDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder view(ProductDetailContract.View view);

        Builder appComponent(AppComponent appComponent);

        ProductDetailComponent build();
    }
}
