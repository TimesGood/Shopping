package com.example.demo.mvp.view.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.mvp.model.entity.PmsProduct;
import com.example.ext.adapter.AppAdapter;
import com.example.ext.viewgroup.RatioFrameLayout;

import java.util.List;

/**
 * 图片展示适配器
 */
public final class ImageAdapter extends AppAdapter<PmsProduct> {

    public ImageAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(R.layout.adapter_img);
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private final RatioFrameLayout ratioFrameLayout;
        private final ImageView mImageView;

        private ViewHolder(int id) {
            super(id);
            ratioFrameLayout = findViewById(R.id.radio_layout);
            mImageView = findViewById(R.id.iv_image);
        }

        @Override
        public void onBindView(int position) {
            PmsProduct imagePath = getItemData(position);
            ratioFrameLayout.setId(Math.toIntExact(imagePath.getId()));
            Glide.with(getContext()).load(imagePath.getPic()).into(mImageView);


        }
    }
    //如果没有设置布局管理器，设置默认的布局管理
    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}