package com.lskycity.support.utils;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/**
 * Created by zhaofliu on 1/5/17.
 */

public class ViewUtils {

    private static long mLastClickTime = 0L;

    public static void setVisible(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @NonNull
    public static <T extends View> T findViewById(View view, @IdRes int id) {
        //noinspection unchecked cast
        T t = (T)view.findViewById(id);
        if(t == null) {
            throw new NullPointerException("can not find id " + id);
        }
        return t;
    }

    @Nullable
    public static <T extends View> T findNullableViewById(View view, @IdRes int id) {
        //noinspection unchecked cast
        return (T)view.findViewById(id);
    }

    public static View inflate(@LayoutRes int resource, @NonNull ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(root.getContext()).inflate(resource, root, attachToRoot);
    }

    public static int getActionBarSize(View view) {
        return getActionBarSize(AppUtils.getActivity(view));
    }

    public static int getActionBarSize(Activity activity) {
        final TypedArray actionBarSize = activity.obtainStyledAttributes(new int[]
                { android.R.attr.actionBarSize });
        int actionBarHeight = actionBarSize.getDimensionPixelSize(0, 0);
        actionBarSize.recycle();
        return actionBarHeight;
    }

    public static Animation.AnimationListener createDismissAnimationListener(View view) {
        return new DismissAnimationListener(view);
    }

    public static Animation.AnimationListener createShowAnimationListener(View view) {
        return new ShowAnimationListener(view);
    }

    public static boolean isLayoutRtl(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection()== View.LAYOUT_DIRECTION_RTL;
        }
        return false;
    }



    public static boolean isClickTooFast() {
        return isClickTooFast(300);
    }

    public static boolean isClickTooFast(int interval) {
        long last = mLastClickTime;
        long now = SystemClock.elapsedRealtime();
        mLastClickTime = now;
        return (now - last)<interval;
    }

    public static Bitmap convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        // assert cacheBmp !=null;
        Bitmap viewBmp = null;
        if (cacheBmp != null) {
            viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        }
        view.destroyDrawingCache();
        return viewBmp;
    }

    private static class DismissAnimationListener implements Animation.AnimationListener {

        private final View mView;
        private DismissAnimationListener(View view) {
            mView = view;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            mView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mView.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private static class ShowAnimationListener implements Animation.AnimationListener {

        private final View mView;
        private ShowAnimationListener(View view) {
            mView = view;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            mView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
