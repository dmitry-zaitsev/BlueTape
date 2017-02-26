package com.github.bluetape;

import android.view.View;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BlueTapeDslTest {

    @Mock
    View view;
    @Mock
    BindingFunction functionA;
    @Mock
    BindingFunction functionB;

    @Test
    public void composite() throws Exception {
        // When
        BlueTapeDsl
                .composite(
                        functionA,
                        functionB
                )
                .bind(view);

        // Then
        InOrder inOrder = inOrder(functionA, functionB);

        inOrder.verify(functionA).bind(view);
        inOrder.verify(functionB).bind(view);
    }

    @Test
    public void id() throws Exception {
        // Given
        int subViewId = 123;
        View subView = mock(View.class);

        //noinspection ResourceType
        given(view.findViewById(subViewId))
                .willReturn(subView);

        // When
        BlueTapeDsl
                .id(subViewId,
                        functionA,
                        functionB
                )
                .bind(view);

        // Then
        InOrder inOrder = inOrder(functionA, functionB);

        inOrder.verify(functionA).bind(subView);
        inOrder.verify(functionB).bind(subView);
    }

    @Test
    public void text_String() throws Exception {
        // Given
        TextView textView = mock(TextView.class);
        String expectedText = "expected";


        // When
        BlueTapeDsl
                .text(expectedText)
                .bind(textView);

        // Then
        verify(textView).setText(expectedText);
    }

}