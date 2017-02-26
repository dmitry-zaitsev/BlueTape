package com.github.bluetape;

import android.app.Activity;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BlueTapeTest {

    @Mock
    View view;
    @Mock
    BlueTape.Factory factory;

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