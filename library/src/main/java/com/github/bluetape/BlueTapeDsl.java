package com.github.bluetape;

import android.view.View;
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
    public static BindingFunction composite(BindingFunction... functions) {
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
    public static BindingFunction id(int id, BindingFunction... functions) {
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

}
