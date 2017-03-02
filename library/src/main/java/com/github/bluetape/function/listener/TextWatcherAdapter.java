package com.github.bluetape.function.listener;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Adapts {@link OnTextChangedListener} to {@link TextWatcher} interface.
 */
public class TextWatcherAdapter implements TextWatcher {

    @NonNull
    private final OnTextChangedListener onTextChangedListener;

    private TextWatcherAdapter(@NonNull OnTextChangedListener onTextChangedListener) {
        this.onTextChangedListener = onTextChangedListener;
    }

    /**
     * @return {@link TextWatcher} which notifies {@link OnTextChangedListener} after text is changed.
     */
    public static TextWatcher whenTextChanged(@NonNull OnTextChangedListener listener) {
        return new TextWatcherAdapter(listener);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextChangedListener.onTextChanged(s.toString());
    }
}
