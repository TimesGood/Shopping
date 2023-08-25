package com.example.demo.test;

import com.example.demo.mvp.view.DemoActivity;

import dagger.Component;

@Component(modules = DemoModule.class)
public interface DemoComponent {
    void inject(DemoActivity activity);
}