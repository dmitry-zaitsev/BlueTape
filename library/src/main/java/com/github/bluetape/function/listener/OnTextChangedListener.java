package com.github.bluetape.function.listener;

/**
 * Called after each time text in {@link android.widget.TextView} (or subsequently
 * {@link android.widget.EditText}) is changed.
 */
public interface OnTextChangedListener {

    /**
     * @param newText new text in {@link android.widget.TextView}.
     */
    void onTextChanged(CharSequence newText);

}
