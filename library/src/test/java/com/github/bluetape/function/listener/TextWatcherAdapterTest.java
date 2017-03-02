package com.github.bluetape.function.listener;

import android.text.Editable;
import android.text.TextWatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TextWatcherAdapterTest {

    @Mock
    OnTextChangedListener listener;
    @Mock
    Editable editable;

    @Test
    public void notifyListener() throws Exception {
        // Given
        TextWatcher watcher = TextWatcherAdapter.whenTextChanged(listener);

        given(editable.toString())
                .willReturn("value");

        // When
        watcher.afterTextChanged(editable);

        // Then
        verify(listener).onTextChanged("value");
    }
}