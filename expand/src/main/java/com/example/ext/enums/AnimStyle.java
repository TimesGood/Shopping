package com.example.ext.enums;

import com.example.expand.R;

public class AnimStyle {
    /** 默认动画效果 */
    public static int ANIM_DEFAULT = -1;

    /** 没有动画效果 */
    public static int ANIM_EMPTY = 0;

    /** 缩放动画 */
    public static int ANIM_SCALE = R.style.ScaleAnimStyle;

    /** IOS 动画 */
    public static int ANIM_IOS = R.style.IOSAnimStyle;

    /** 吐司动画 */
    public static int ANIM_TOAST = android.R.style.Animation_Toast;

    /** 顶部弹出动画 */
    public static int ANIM_TOP = R.style.TopAnimStyle;

    /** 底部弹出动画 */
    public static int ANIM_BOTTOM = R.style.BottomAnimStyle;

    /** 左边弹出动画 */
    public static int ANIM_LEFT = R.style.LeftAnimStyle;

    /** 右边弹出动画 */
    public static int ANIM_RIGHT = R.style.RightAnimStyle;
}
