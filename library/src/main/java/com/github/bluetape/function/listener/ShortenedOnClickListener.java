package com.github.bluetape.function.listener;

import android.view.View;

/**
 * Serves the same purpose as {@link android.view.View.OnClickListener} but does not require
 * {@link View} as a parameter. That is a convenience which allows to write lambdas in a shorter
 * way.
 */
public interface ShortenedOnClickListener {

    /**
     * Same as {@link android.view.View.OnClickListener#onClick(View)} but without {@link View} as a
     * parameter.
     */
    void onClick();

}
