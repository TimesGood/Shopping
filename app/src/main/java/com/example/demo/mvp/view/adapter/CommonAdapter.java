package com.example.demo.mvp.view.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.demo.R;
import com.example.ext.adapter.AppAdapter;
import com.example.ext.adapter.BaseAdapter;

public class CommonAdapter extends AppAdapter<String> {
    public CommonAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(R.layout.common_item);
    }
    private class ViewHolder extends AppAdapter<?>.ViewHolder {
        private final RelativeLayout list_item;
        private final ImageView left_img,right_img;
        private final TextView text_center;
        public ViewHolder(int id) {
            super(id);
            list_item = findViewById(R.id.list_item);
            left_img = findViewById(R.id.left_img);
            right_img = findViewById(R.id.right_img);
            text_center = findViewById(R.id.text_center);
        }
        @Override
        public void onBindView(int position) {
            list_item.setId(position);
            text_center.setText(getItemData(position));
        }
    }
}