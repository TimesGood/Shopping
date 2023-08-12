package com.example.demo.mvp.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.ThreadPoolManager;
import com.example.core.base.AppActivity;
import com.example.core.di.component.AppComponent;
import com.example.demo.R;
import com.example.demo.dialog.AlbumDialog;
import com.example.demo.mvp.model.entity.AlbumInfo;
import com.example.ext.action.HandlerAction;
import com.example.ext.adapter.BaseAdapter;
import com.example.ext.adapter.common.ImageSelectAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 图片浏览+选择图片功能
 */
public final class ImageSelectActivity extends AppActivity
        implements Runnable, HandlerAction,
        View.OnClickListener,
        BaseAdapter.OnItemClickListener,
        BaseAdapter.OnItemLongClickListener,
        BaseAdapter.OnChildClickListener {
    private FloatingActionButton mFloatingView;
    private RecyclerView mRecyclerView;
    private ImageSelectAdapter mAdapter;
    /** 专辑选择对话框 */
    private AlbumDialog.Builder mAlbumDialog;
    /** 选中列表 */
    private final ArrayList<String> mSelectImage = new ArrayList<>();
    /** 全部图片 */
    private final ArrayList<String> mAllImage = new ArrayList<>();
    /** 图片专辑 */
    private final HashMap<String, List<String>> mAllAlbum = new HashMap<>();
    private Toolbar toolbar;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_select;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.rv_image_select_list);
        mFloatingView = findViewById(R.id.camera_btn);
        toolbar = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new ImageSelectAdapter(this, mSelectImage);
        mAdapter.setOnChildClickListener(com.example.expand.R.id.fl_image_select_check, this);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        //开启线程池加载图片列表
        ThreadPoolManager.getInstance().execute(this);
        mFloatingView.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_img_group,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.select_album) {
            if (mAllImage.isEmpty()) return super.onOptionsItemSelected(item);
            ArrayList<AlbumInfo> data = new ArrayList<>(mAllAlbum.size() + 1);
            int count = 0;
            Set<String> keys = mAllAlbum.keySet();
            for (String key : keys) {
                List<String> list = mAllAlbum.get(key);
                if (list != null && !list.isEmpty()) {
                    count += list.size();
                    data.add(new AlbumInfo(list.get(0), key, String.format(getString(R.string.menu_img_count), list.size()), mAdapter.getData() == list));
                }
            }
            data.add(0, new AlbumInfo(mAllImage.get(0), getString(R.string.menu_img_group), String.format(getString(R.string.menu_img_count), count), mAdapter.getData() == mAllImage));
            if (mAlbumDialog == null) {
                mAlbumDialog = new AlbumDialog.Builder(this)
                        .setListener((dialog, position, bean) -> {
//                            setMenuTitle(0,bean.getName());
                            // 滚动回第一个位置
                            mRecyclerView.scrollToPosition(0);
                            if (position == 0) {
                                mAdapter.setData(mAllImage);
                            } else {
                                mAdapter.setData(mAllAlbum.get(bean.getName()));
                            }
                            // 执行列表动画
//                            mRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.from_bottom_layout));
//                            mRecyclerView.scheduleLayoutAnimation();
                        });
            }
            mAlbumDialog.setData(data)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View v, int position) {

    }

    @Override
    public boolean onItemLongClick(RecyclerView recyclerView, View v, int position) {
        return false;
    }

    @Override
    public void onChildClick(RecyclerView recyclerView, View v, int position) {

    }

    @Override
    public void run() {
        mAllAlbum.clear();
        mAllImage.clear();
        //*******************需要查询多媒体文件************************
        //MediaStore的数据库文件位于/data/data/com.android.providers/databases
        //指定要查询的SQLite表名称
        final Uri contentUri = MediaStore.Files.getContentUri("external");
        //指定查询结果的排序方式
        final String sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC";
        //指定查询条件
        final String selection = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)" + " AND " + MediaStore.MediaColumns.SIZE + ">0";
        //***************************指定查询数据库中哪几列****************************
        String[] projections = new String[]{
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.SIZE
        };
        //要查询什么类型的数据
        String[] selectionArgs = new String[]{
                String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)//图片
        };
        //获取ContentProvider的查询接口
        ContentResolver contentResolver = getContentResolver();
        //执行查询
        Cursor cursor = contentResolver.query(contentUri, projections, selection, selectionArgs, sortOrder);

        if (cursor != null && cursor.moveToFirst()) {
            //获取某字段在第几列
            int pathIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int mimeTypeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE);
            int sizeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE);

            do {
                long size = cursor.getLong(sizeIndex);
                if (size < 1024) continue;// 图片大小不得小于 1 KB
                String type = cursor.getString(mimeTypeIndex);//获取图片类型
                String path = cursor.getString(pathIndex);//获取图片的绝对路径
                if (TextUtils.isEmpty(path) || TextUtils.isEmpty(type)) continue;//图片路径、图片类型不能为空
                File file = new File(path);
                if (!file.exists() || !file.isFile()) continue;//图片路径不能是文件夹
                File parentFile = file.getParentFile();//获得父目录
                if (parentFile != null) {
                    String albumName = parentFile.getName();//获取目录名
                    //目录名作为专辑名称
                    List<String> data = mAllAlbum.get(albumName);
                    if (data == null) {
                        data = new ArrayList<>();
                        mAllAlbum.put(albumName, data);
                    }
                    data.add(path);
                    mAllImage.add(path);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        postDelayed(() -> {
            // 滚动回第一个位置
            mRecyclerView.scrollToPosition(0);
            // 设置新的列表数据
            mAdapter.setData(mAllImage);
        }, 500);
    }

    @Override
    public void onClick(View view) {
    }
}