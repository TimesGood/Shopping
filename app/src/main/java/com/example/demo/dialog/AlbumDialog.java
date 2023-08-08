package com.example.demo.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.mvp.model.entity.AlbumInfo;
import com.example.ext.adapter.AppAdapter;
import com.example.ext.adapter.BaseAdapter;
import com.example.ext.dialog.BaseDialog;
import com.example.ext.dialog.BottomSheetDialog;

import java.util.List;

/**
 * 相册专辑
 */
public final class AlbumDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder>
            implements BaseAdapter.OnItemClickListener {

        private OnListener mListener;

        private final RecyclerView mRecyclerView;
        private final AlbumAdapter mAdapter;

        public Builder(Context context) {
            super(context);

            setContentView(com.example.expand.R.layout.dialog_album);

            mRecyclerView = findViewById(com.example.expand.R.id.rv_album_list);
            mAdapter = new AlbumAdapter(context);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }

        public Builder setData(List<AlbumInfo> data) {
            mAdapter.setData(data);
            // 滚动到选中的位置
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).isSelect()) {
                    mRecyclerView.scrollToPosition(i);
                    break;
                }
            }
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
            List<AlbumInfo> data = mAdapter.getData();
            if (data == null) {
                return;
            }

            for (AlbumInfo info : data) {
                if (info.isSelect()) {
                    info.setSelect(false);
                    break;
                }
            }
            mAdapter.getItemData(position).setSelect(true);
            mAdapter.notifyDataSetChanged();

            // 延迟消失
            postDelayed(() -> {

                if (mListener != null) {
                    mListener.onSelected(getDialog(), position, mAdapter.getItemData(position));
                }
                dismiss();

            }, 300);
        }

        @NonNull
        @Override
        protected BaseDialog createDialog(Context context, int themeId) {
            BottomSheetDialog dialog = new BottomSheetDialog(context, themeId);
            dialog.getBottomSheetBehavior().setPeekHeight(getResources().getDisplayMetrics().heightPixels / 2);
            return dialog;
        }
    }

    private static final class AlbumAdapter extends AppAdapter<AlbumInfo> {

        private AlbumAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder();
        }

        private final class ViewHolder extends AppAdapter<?>.ViewHolder {

            private final ImageView mIconView;
            private final TextView mNameView;
            private final TextView mRemarkView;
            private final CheckBox mCheckBox;

            private ViewHolder() {
                super(com.example.expand.R.layout.adapter_album);
                mIconView = findViewById(com.example.expand.R.id.iv_album_icon);
                mNameView = findViewById(com.example.expand.R.id.tv_album_name);
                mRemarkView = findViewById(com.example.expand.R.id.tv_album_remark);
                mCheckBox = findViewById(com.example.expand.R.id.rb_album_check);
            }

            @Override
            public void onBindView(int position) {
                AlbumInfo info = getItemData(position);
                Glide.with(getContext())
                        .asBitmap()
                        .load(info.getIcon())
                        .into(mIconView);

                mNameView.setText(info.getName());
                mRemarkView.setText(info.getRemark());
                mCheckBox.setChecked(info.isSelect());
                mCheckBox.setVisibility(info.isSelect() ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }

    public interface OnListener {

        /**
         * 选择条目时回调
         */
        void onSelected(BaseDialog dialog, int position, AlbumInfo bean);
    }
}