package com.example.core.util;

import android.content.Context;
import com.example.core.base.App;
import com.example.core.di.component.AppComponent;

public class AppComponentUtils {
    public static AppComponent obtainAppComponentFromContext(Context context) {
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        Preconditions.checkState(context.getApplicationContext() instanceof App, "%s must be implements %s", context.getApplicationContext().getClass().getName(), App.class.getName());
        return ((App) context.getApplicationContext()).getAppComponent();
    }
}
