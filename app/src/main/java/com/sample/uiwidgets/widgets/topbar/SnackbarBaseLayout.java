package com.sample.uiwidgets.widgets.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sample.uiwidgets.R;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by karim on 17/01/2018.
 */

@RestrictTo(LIBRARY_GROUP)
public class SnackbarBaseLayout extends FrameLayout {

    private BaseTransientBottomBar.OnLayoutChangeListener mOnLayoutChangeListener;
    private BaseTransientBottomBar.OnAttachStateChangeListener mOnAttachStateChangeListener;

    SnackbarBaseLayout(Context context) {
        this(context, null);
    }

    SnackbarBaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SnackbarLayout);
        if (a.hasValue(R.styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation(this, a.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
        }
        a.recycle();

        setClickable(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mOnLayoutChangeListener != null) {
            mOnLayoutChangeListener.onLayoutChange(this, l, t, r, b);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mOnAttachStateChangeListener != null) {
            mOnAttachStateChangeListener.onViewAttachedToWindow(this);
        }

        ViewCompat.requestApplyInsets(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mOnAttachStateChangeListener != null) {
            mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
        }
    }

    void setOnLayoutChangeListener(
            BaseTransientBottomBar.OnLayoutChangeListener onLayoutChangeListener) {
        mOnLayoutChangeListener = onLayoutChangeListener;
    }

    void setOnAttachStateChangeListener(
            BaseTransientBottomBar.OnAttachStateChangeListener listener) {
        mOnAttachStateChangeListener = listener;
    }
}
