package com.github.bluetape;

import android.app.Activity;
import android.view.View;

import com.github.bluetape.function.BindingFunction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BlueTapeTest {

    @Mock
    View view;
    @Mock
    BlueTape.Factory factory;
    @Mock
    BindingFunction bindingFunction;

    BlueTape testee;

    @Before
    public void setUp() throws Exception {
        testee = BlueTape
                .with(factory)
                .into(view);
    }

    @Test
    public void update() throws Exception {
        // Given
        given(factory.create())
                .willReturn(bindingFunction);

        // When
        testee.update();

        // Then
        verify(bindingFunction).bind(view);
    }

    @Test
    public void create_WithView() throws Exception {
        // When
        BlueTape blueTape = BlueTape
                .with(factory)
                .into(view);

        // Then
        assertSame(factory, blueTape.factory);
        assertSame(view, blueTape.view);
    }

    @Test
    public void create_WithActivity() throws Exception {
        // Given
        Activity activity = mock(Activity.class);

        given(activity.findViewById(android.R.id.content))
                .willReturn(view);

        // When
        BlueTape blueTape = BlueTape
                .with(factory)
                .into(activity);

        // Then
        assertSame(factory, blueTape.factory);
        assertSame(view, blueTape.view);
    }

}