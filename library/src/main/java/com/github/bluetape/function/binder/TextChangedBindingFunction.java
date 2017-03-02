package com.github.bluetape.function.binder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.github.bluetape.R;
import com.github.bluetape.function.BindingFunction;
import com.github.bluetape.function.listener.OnTextChangedListener;
import com.github.bluetape.function.listener.TextWatcherAdapter;

/**
 * Function which assigns listener which is called after each time text in {@link TextView}
 * is changed by user. {@code null} will remove the listener.
 */
public class TextChangedBindingFunction implements BindingFunction {

    @Nullable
    private final OnTextChangedListener listener;

    private TextChangedBindingFunction(@Nullable OnTextChangedListener listener) {
        this.listener = listener;
    }

    /**
     * @return function which binds {@link OnTextChangedListener} to a {@link TextView}.
     */
    public static BindingFunction create(@Nullable OnTextChangedListener listener) {
        return new TextChangedBindingFunction(listener);
    }

    private static boolean isAlreadyAssigned(TextView textView) {
        return textView.getTag(R.id.onTextChangedListener) instanceof TextWatcher;
    }

    private static void addTextWatcher(TextView textView, @NonNull OnTextChangedListener listener) {
        TextWatcher watcher = TextWatcherAdapter.whenTextChanged(listener);

        textView.addTextChangedListener(watcher);
        textView.setTag(R.id.onTextChangedListener, watcher);
    }

    private static void removeTextWatcher(TextView textView) {
        textView.removeTextChangedListener(
                (TextWatcher) textView.getTag(R.id.onTextChangedListener)
        );
        textView.setTag(R.id.onTextChangedListener, null);
    }

    @Override
    public void bind(View view) {
        TextView textView = (TextView) view;

        if (!isAlreadyAssigned(textView) && listener != null) {
            addTextWatcher(textView, listener);
        }

        if (isAlreadyAssigned(textView) && listener == null) {
            removeTextWatcher(textView);
        }
    }

}
