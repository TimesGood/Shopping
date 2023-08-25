package com.example.demo.test;

import dagger.Module;
import dagger.Provides;

@Module
public class DemoModule {
    @Provides
    public DemoDaggerBase provideDemoDaggerBase(){
        return new DemoDaggerBase("测试");
    }
}
