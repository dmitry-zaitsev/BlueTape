package com.github.bluetape;

import android.app.Activity;
import android.view.View;

public class BlueTape {

    final View view;
    final Factory factory;

    private BlueTape(View view, Factory factory) {
        this.view = view;
        this.factory = factory;
    }

    /**
     * Sets up a new instance of {@link BlueTape}.
     *
     * @param factory {@link Factory} which provides a {@link BindingFunction} each time
     *                {@link #update()} is called. Use it to set up bindings to your views.
     * @return {@link ViewSelector} step of {@link BlueTape} set up.
     */
    public static ViewSelector with(Factory factory) {
        return new ViewSelector(factory);
    }

    public void update() {

    }

    /**
     * Provides a {@link BindingFunction} each time {@link #update()} is called. Use it to set up
     * bindings to your views.
     * <p>
     * There are many implementations of {@link BindingFunction} ready to be used in {@link BlueTapeDsl}.
     */
    public interface Factory {

        /**
         * @return function which will bind view values when called.
         */
        BindingFunction create();

    }

    /**
     * Intermediate step in {@link BlueTape} creation which allows to select how {@link BlueTape}
     * will be attached to the view.
     */
    public static class ViewSelector {

        private final Factory factory;

        private ViewSelector(Factory factory) {
            this.factory = factory;
        }

        /**
         * Binds {@link BlueTape} to root view of given {@link Activity}.
         *
         * @return {@link BlueTape} bound to given {@link Activity}.
         */
        public BlueTape into(Activity activity) {
            return into(
                    activity.findViewById(android.R.id.content)
            );
        }

        /**
         * Binds {@link BlueTape} to given {@link View}.
         *
         * @return {@link BlueTape} bound to given {@link View}.
         */
        public BlueTape into(View view) {
            return new BlueTape(view, factory);
        }

    }

}
