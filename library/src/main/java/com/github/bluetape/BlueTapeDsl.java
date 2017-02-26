package com.github.bluetape;

import android.widget.TextView;

public class BlueTapeDsl {

    public static BindingFunction composite(BindingFunction... functions) {
        return view -> {
            for (BindingFunction function : functions) {
                function.bind(view);
            }
        };
    }

    public static BindingFunction id(int id, BindingFunction... functions) {
        return view -> composite(functions).bind(view.findViewById(id));
    }

    public static BindingFunction text(String text) {
        return view -> ((TextView) view).setText(text);
    }

}
