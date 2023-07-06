package com.example.demo.mvp.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.core.base.BaseActivity;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.mvp.view.fragment.HomeFragment;
import com.example.demo.mvp.view.fragment.Page1Fragment;
import com.example.demo.mvp.view.fragment.Page2Fragment;
import com.example.ext.adapter.common.FragmentPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends BaseActivity implements NavigationBarView.OnItemSelectedListener{
    private FragmentPagerAdapter<Fragment> fragmentPagerAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView home_navigation;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mViewPager = findViewById(R.id.main_body);
        fragmentPagerAdapter = new FragmentPagerAdapter<>(this);
        fragmentPagerAdapter.addFragment(new HomeFragment());
        fragmentPagerAdapter.addFragment(new Page1Fragment());
        fragmentPagerAdapter.addFragment(new Page2Fragment());
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        home_navigation = findViewById(R.id.home_navigation);
        home_navigation.setOnItemSelectedListener(this);
        home_navigation.setItemIconTintList(null);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.home)
            mViewPager.setCurrentItem(0);
        else if(itemId == R.id.page1)
            mViewPager.setCurrentItem(1);
        else if(itemId == R.id.page2)
            mViewPager.setCurrentItem(2);
        return true;
    }
}
