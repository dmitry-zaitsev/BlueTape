package com.github.bluetape;

import android.view.View;

public class BlueTape {


    private final Factory factory;

    private BlueTape(Factory factory) {
        this.factory = factory;
    }

    public static BlueTape create(Factory factory) {
        return new BlueTape(factory);
    }

    public void update(View view) {
        factory.create().bind(view);
    }

    public interface Factory {

        BindingFunction create();

    }

}
