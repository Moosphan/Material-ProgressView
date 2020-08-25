package com.moos.library;

import android.content.Context;

/**
 * Created by moos on 2018/3/16.
 * Utils to help customize views.
 */
class Utils {
    /**
     * Convert dp dimension to px.
     * @param context context
     * @param dpValue dp dimension
     * @return px value
     */
    static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Convert sp dimension to px.
     * @param context context
     * @param spValue sp dimension
     * @return px value
     */
    static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
