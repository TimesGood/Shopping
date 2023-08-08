package com.example.core;

import android.app.ActivityManager;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ThreadPoolManager extends ThreadPoolExecutor {

    private static volatile ThreadPoolManager sInstance;

    public ThreadPoolManager() {
        super(0, Integer.MAX_VALUE,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }

    public static ThreadPoolManager getInstance() {
        if(sInstance == null) {
            synchronized (ActivityManager.class) {
                if(sInstance == null) {
                    sInstance = new ThreadPoolManager();
                }
            }
        }
        return sInstance;
    }
}