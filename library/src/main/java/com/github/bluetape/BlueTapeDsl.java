package com.github.bluetape;

import android.widget.TextView;

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
     */
    public static BindingFunction id(int id, BindingFunction... functions) {
        return view -> composite(functions).bind(view.findViewById(id));
    }

    /**
     * @return function which assigns text to current {@link TextView}.
     * @throws ClassCastException if current view is not a {@link TextView}.
     */
    public static BindingFunction text(String text) {
        return view -> ((TextView) view).setText(text);
    }

}
