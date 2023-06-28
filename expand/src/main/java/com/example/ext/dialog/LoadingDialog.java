package com.example.ext.dialog;

import android.content.Context;
import android.view.View;

import com.example.expand.R;

/**
 * 加载中弹窗
 * 转圈圈
 */
public class LoadingDialog {
    public static class Builder extends CommonDialog.Builder<Builder> {
        private OnListener mListener;
        public Builder(Context context) {
            super(context);
            setCustomView(R.layout.loading_dialog);
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_ui_confirm) {
                autoDismiss();
                if (mListener != null) {
                    mListener.onConfirm(getDialog());
                }
            } else if (id == R.id.tv_ui_cancel) {
                autoDismiss();
                if (mListener != null) {
                    mListener.onCancel(getDialog());
                }
            }
        }
    }
    public interface OnListener {
        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog);

        /**
         * 点击取消时回调
         */
        default void onCancel(BaseDialog dialog) {}
    }
}
