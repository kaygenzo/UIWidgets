package com.telen.library.widgets.topbar;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Snackbars provide lightweight feedback about an operation. They show a brief message at the
 * bottom of the screen on mobile and lower left on larger devices. Snackbars appear above all other
 * elements on screen and only one can be displayed at a time.
 * <p>
 * They automatically disappear after a timeout or after user interaction elsewhere on the screen,
 * particularly after interactions that summon a new surface or activity. Snackbars can be swiped
 * off screen.
 * <p>
 * Snackbars can contain an action which is set via
 * {@link #setAction(CharSequence, View.OnClickListener)}.
 * <p>
 * To be notified when a snackbar has been shown or dismissed, you can provide a {@link Callback}
 * via {@link BaseTransientBottomBar#addCallback(BaseCallback)}.</p>
 */


/**
 * Created by karim on 17/01/2018.
 */
public final class TopSnackbar extends BaseTransientBottomBar<TopSnackbar> {

    /**
     * Show the Snackbar indefinitely. This means that the Snackbar will be displayed from the time
     * that is {@link #show() shown} until either it is dismissed, or another Snackbar is shown.
     *
     * @see #setDuration
     */
    public static final int LENGTH_INDEFINITE = BaseTransientBottomBar.LENGTH_INDEFINITE;

    /**
     * Show the Snackbar for a short period of time.
     *
     * @see #setDuration
     */
    public static final int LENGTH_SHORT = BaseTransientBottomBar.LENGTH_SHORT;

    /**
     * Show the Snackbar for a long period of time.
     *
     * @see #setDuration
     */
    public static final int LENGTH_LONG = BaseTransientBottomBar.LENGTH_LONG;

    private TopSnackbar(ViewGroup parent, View content, ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    /**
     * Make a Topbar to display a content view
     *
     * <p>Snackbar will try and find a parent view to hold Snackbar's view from the value given
     * to {@code view}. Snackbar will walk up the view tree trying to find a suitable parent,
     * which is defined as a {@link android.support.design.widget.CoordinatorLayout} or the window decor's content view,
     * whichever comes first.
     *
     * <p>Having a {@link android.support.design.widget.CoordinatorLayout} in your view hierarchy allows Snackbar to enable
     * certain features, such as swipe-to-dismiss and automatically moving of widgets like
     * {@link android.support.design.widget.FloatingActionButton}.
     *
     * @param container     The view to find a parent from.
     * @param content     The content to display into topbar
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or {@link
     *                 #LENGTH_LONG}
     */

    @NonNull
    public static TopSnackbar make(@NonNull View container, View content, ContentViewCallback contentViewCallback, @Duration int duration, int swipeDirection) {
        final ViewGroup parent = findSuitableParent(container);
        if (parent == null) {
            throw new IllegalArgumentException("No suitable parent found from the given view. "
                    + "Please provide a valid view.");
        }

        final TopSnackbar snackbar = new TopSnackbar(parent, content, contentViewCallback);
        snackbar.setDuration(duration);
        snackbar.setSwipeDirection(swipeDirection);
        return snackbar;
    }

    @NonNull
    public static TopSnackbar make(@NonNull View container, View content, @Duration int duration, int swipeDirection) {
        return make(container, content, new ContentViewCallback() {
            @Override public void animateContentIn(int delay, int duration) {}
            @Override public void animateContentOut(int delay, int duration) {}
        }, duration, swipeDirection);
    }

    @NonNull
    public static TopSnackbar make(@NonNull View container, View content, @Duration int duration) {
        return make(container, content, new ContentViewCallback() {
            @Override public void animateContentIn(int delay, int duration) {}
            @Override public void animateContentOut(int delay, int duration) {}
        }, duration, SwipeDismissBehavior.DEFAULT_SWIPE_DIRECTION);
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }
}

