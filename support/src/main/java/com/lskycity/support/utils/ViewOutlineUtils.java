package com.lskycity.support.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 *@author liuzhaofeng
 *@since Mar 9, 2015
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ViewOutlineUtils {

    public static class RoundRectOutlineProvider extends ViewOutlineProvider {

        private float mRadius;

        public RoundRectOutlineProvider(float radius) {
            super();
            mRadius = radius;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), mRadius);
        }

    }

    public static class RoundRectOutlineProviderByRate extends ViewOutlineProvider {

        private float mRadiusRate;

        public RoundRectOutlineProviderByRate(float radiusRate) {
            super();
            mRadiusRate = radiusRate;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), view.getWidth() * mRadiusRate);
        }

    }

    public static class OvalOutlineProvider extends ViewOutlineProvider {

        public OvalOutlineProvider() {
            super();
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, view.getWidth(), view.getHeight());
        }

    }


}
