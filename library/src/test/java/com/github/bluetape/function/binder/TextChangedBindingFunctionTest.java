package com.github.bluetape.function.binder;

import android.text.TextWatcher;
import android.widget.TextView;

import com.github.bluetape.R;
import com.github.bluetape.function.BindingFunction;
import com.github.bluetape.function.listener.OnTextChangedListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TextChangedBindingFunctionTest {

    @Mock
    TextView view;
    @Mock
    OnTextChangedListener listener;

    @Test
    public void addTextWatcher() throws Exception {
        // Given
        BindingFunction function = TextChangedBindingFunction.create(listener);

        // When
        function.bind(view);

        // Then
        verify(view).addTextChangedListener(isA(TextWatcher.class));
        verify(view).setTag(eq(R.id.onTextChangedListener), isA(TextWatcher.class));
    }

    @Test
    public void doNotAddTextWatcher_IfAlreadyAdded() throws Exception {
        // Given
        given(view.getTag(anyInt()))
                .willReturn(mock(TextWatcher.class));

        BindingFunction function = TextChangedBindingFunction.create(listener);

        // When
        function.bind(view);

        // Then
        verify(view, never()).addTextChangedListener(any());
    }

    @Test
    public void removeTextWatcher() throws Exception {
        // Given
        TextWatcher watcher = mock(TextWatcher.class);

        given(view.getTag(anyInt()))
                .willReturn(watcher);

        BindingFunction function = TextChangedBindingFunction.create(null);

        // When
        function.bind(view);

        // Then
        verify(view).removeTextChangedListener(watcher);
        verify(view).setTag(R.id.onTextChangedListener, null);
    }

    @Test
    public void removeTextWatcher_NoWatcherSet() throws Exception {
        // Given
        BindingFunction function = TextChangedBindingFunction.create(null);

        // When
        function.bind(view);

        // Then
        verify(view, never()).addTextChangedListener(any());
        verify(view, never()).removeTextChangedListener(any());
        verify(view, never()).setTag(anyInt(), isNotNull());
    }

}