package com.github.bluetape;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.bluetape.annotation.Visibility;
import com.github.bluetape.exception.ViewNotFoundException;
import com.github.bluetape.function.BindingFunction;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Most commonly used binding functions.
 */
public class BlueTapeDsl {

    /**
     * @param functions functions which will be executed for current view in order of iteration.
     * @return function which binds every given function to the same view. Useful when you
     * want to use several binding functions for the same view at once.
     */
    public static BindingFunction composite(@NonNull BindingFunction... functions) {
        return view -> {
            for (BindingFunction function : functions) {
                function.bind(view);
            }
        };
    }

    /**
     * @param id        Android identifier of the sub-view in current view.
     * @param functions functions which will be executed on sub-view in order of iteration.
     * @return function which binds every given function to the sub-view of current view. Useful
     * when you want to apply binding function to a particular view.
     * @throws ViewNotFoundException if view with given Android identifier was not found.
     */
    public static BindingFunction id(int id, @NonNull BindingFunction... functions) {
        return view -> {
            View subView = view.findViewById(id);
            if (subView == null) {
                throw new ViewNotFoundException("View with id " + id + " cannot be found");
            }

            composite(functions).bind(subView);
        };
    }

    /**
     * @return function which assigns text to current {@link TextView}.
     * @throws ClassCastException if current view is not a {@link TextView}.
     */
    public static BindingFunction text(String text) {
        return view -> ((TextView) view).setText(text);
    }

    /**
     * @return function which assigns text resource to current {@link TextView}.
     * @throws ClassCastException if current view is not a {@link TextView}.
     */
    public static BindingFunction textResource(@StringRes int stringId) {
        return view -> ((TextView) view).setText(stringId);
    }

    /**
     * @return function which assigns text color (not resource!) to current {@link TextView}.
     * @throws ClassCastException if current view is not a {@link TextView}.
     */
    public static BindingFunction textColor(@ColorInt int color) {
        return view -> ((TextView) view).setTextColor(color);
    }

    /**
     * Shortcut for {@link #visibility(int)} which either sets visibility to {@link View#VISIBLE}
     * or {@link View#GONE}.
     */
    public static BindingFunction visible(boolean visible) {
        return visibility(
                visible
                        ? VISIBLE
                        : GONE
        );
    }

    /**
     * @param visibility parameter as in {@link View#setVisibility(int)}.
     * @return function which assigns visibility to current {@link View}.
     */
    public static BindingFunction visibility(@Visibility int visibility) {
        return view -> view.setVisibility(visibility);
    }

    /**
     * @return function which checks or un-checks {@link Checkable} view.
     * @throws ClassCastException if current view is not a {@link Checkable}.
     */
    public static BindingFunction checked(boolean checked) {
        return view -> ((Checkable) view).setChecked(checked);
    }

    /**
     * @return function which assigns {@link Drawable} to an {@link ImageView}.
     * @throws ClassCastException if current view is not an {@link ImageView}.
     */
    public static BindingFunction imageDrawable(@Nullable Drawable drawable) {
        return view -> ((ImageView) view).setImageDrawable(drawable);
    }

    /**
     * @return function which assigns {@link Drawable} resource to an {@link ImageView}.
     * @throws ClassCastException if current view is not an {@link ImageView}.
     */
    public static BindingFunction imageResource(@DrawableRes int drawableId) {
        return view -> ((ImageView) view).setImageResource(drawableId);
    }

    /**
     * @return function which assigns {@link Bitmap} to an {@link ImageView}.
     * @throws ClassCastException if current view is not an {@link ImageView}.
     */
    public static BindingFunction imageBitmap(@Nullable Bitmap bitmap) {
        return view -> ((ImageView) view).setImageBitmap(bitmap);
    }

    /**
     * @return function which assigns {@link Drawable} as {@link View} background.
     */
    @SuppressWarnings("deprecation")
    public static BindingFunction backgroundDrawable(@Nullable Drawable drawable) {
        return view -> view.setBackgroundDrawable(drawable);
    }

    /**
     * @return function which assigns {@link Drawable} resource to {@link View} background.
     */
    public static BindingFunction backgroundResource(@DrawableRes int drawableId) {
        return view -> view.setBackgroundResource(drawableId);
    }

    /**
     * @return function which assigns click listener to {@link View}. {@code null} removes the
     * listener.
     */
    public static BindingFunction onClick(@Nullable View.OnClickListener listener) {
        return view -> view.setOnClickListener(listener);
    }

    /**
     * @return function which assigns long-click listener to {@link View}. {@code null} removes the
     * listener.
     */
    public static BindingFunction onLongClick(@Nullable View.OnLongClickListener listener) {
        return view -> view.setOnLongClickListener(listener);
    }

    /**
     * @return function which assigns {@link android.view.View.OnTouchListener} to {@link View}.
     * {@code null} removes the listener.
     */
    public static BindingFunction onTouch(@Nullable View.OnTouchListener listener) {
        return view -> view.setOnTouchListener(listener);
    }

}
