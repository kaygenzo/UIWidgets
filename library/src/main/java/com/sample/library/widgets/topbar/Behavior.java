package com.sample.library.widgets.topbar;

import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by karim on 17/01/2018.
 */

final class Behavior extends SwipeDismissBehavior<SnackbarBaseLayout> {

    private SnackbarManager.Callback mCallback;

    public Behavior(SnackbarManager.Callback callback) {
        this.mCallback=callback;
    }

    @Override
    public boolean canSwipeDismissView(View child) {
        return child instanceof SnackbarBaseLayout;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, SnackbarBaseLayout child, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // We want to make sure that we disable any Snackbar timeouts if the user is
                // currently touching the Snackbar. We restore the timeout when complete
                if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                    if(mCallback!=null)
                        SnackbarManager.getInstance().pauseTimeout(mCallback);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(mCallback!=null)
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(mCallback);
                break;
        }
        return super.onInterceptTouchEvent(parent, child, event);
    }
}
