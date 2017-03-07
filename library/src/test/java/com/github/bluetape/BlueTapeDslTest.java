package com.github.bluetape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.bluetape.exception.ViewNotFoundException;
import com.github.bluetape.function.BindingFunction;
import com.github.bluetape.function.binder.TextChangedBindingFunction;
import com.github.bluetape.function.listener.OnTextChangedListener;
import com.github.bluetape.function.listener.ShortenedOnClickListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BlueTapeDslTest {

    @Mock
    Context context;
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

    @Test(expected = ViewNotFoundException.class)
    public void id_ViewNotFound() throws Exception {
        // Given
        int subViewId = 123;

        //noinspection ResourceType
        given(view.findViewById(subViewId))
                .willReturn(null);

        // When
        BlueTapeDsl
                .id(subViewId,
                        functionA,
                        functionB
                )
                .bind(view);

        // Then
        // Expect exception
    }

    @Test
    public void text() throws Exception {
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

    @Test
    public void textResource() throws Exception {
        // Given
        TextView textView = mock(TextView.class);
        int expectedResource = android.R.string.cancel;

        // When
        BlueTapeDsl
                .textResource(expectedResource)
                .bind(textView);

        // Then
        verify(textView).setText(expectedResource);
    }

    @Test
    public void textColor() throws Exception {
        // Given
        TextView textView = mock(TextView.class);

        // When
        BlueTapeDsl
                .textColor(Color.RED)
                .bind(textView);

        // Then
        verify(textView).setTextColor(Color.RED);
    }

    @Test
    public void visibility() throws Exception {
        // When
        BlueTapeDsl
                .visibility(View.GONE)
                .bind(view);

        // Then
        verify(view).setVisibility(View.GONE);
    }

    @Test
    public void visible_True() throws Exception {
        // When
        BlueTapeDsl
                .visible(true)
                .bind(view);

        // Then
        verify(view).setVisibility(View.VISIBLE);
    }

    @Test
    public void visible_False() throws Exception {
        // When
        BlueTapeDsl
                .visible(false)
                .bind(view);

        // Then
        verify(view).setVisibility(View.GONE);
    }

    @Test
    public void enabled() throws Exception {
        // When
        BlueTapeDsl
                .enabled(true)
                .bind(view);

        // Then
        verify(view).setEnabled(true);
    }

    @Test
    public void checked() throws Exception {
        // Given
        CheckBox checkBox = mock(CheckBox.class);

        // When
        BlueTapeDsl
                .checked(true)
                .bind(checkBox);

        // Then
        verify(checkBox).setChecked(true);
    }

    @Test
    public void imageDrawable() throws Exception {
        // Given
        ImageView imageView = mock(ImageView.class);
        Drawable drawable = mock(Drawable.class);

        // When
        BlueTapeDsl
                .imageDrawable(drawable)
                .bind(imageView);

        // Then
        verify(imageView).setImageDrawable(drawable);
    }

    @Test
    public void imageResource() throws Exception {
        // Given
        ImageView imageView = mock(ImageView.class);
        int imageResource = android.R.drawable.ic_menu_report_image;

        // When
        BlueTapeDsl
                .imageResource(imageResource)
                .bind(imageView);

        // Then
        verify(imageView).setImageResource(imageResource);
    }

    @Test
    public void imageBitmap() throws Exception {
        // Given
        ImageView imageView = mock(ImageView.class);
        Bitmap bitmap = mock(Bitmap.class);

        // When
        BlueTapeDsl
                .imageBitmap(bitmap)
                .bind(imageView);

        // Then
        verify(imageView).setImageBitmap(bitmap);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void backgroundDrawable() throws Exception {
        // Given
        Drawable drawable = mock(Drawable.class);

        // When
        BlueTapeDsl
                .backgroundDrawable(drawable)
                .bind(view);

        // Then
        verify(view).setBackgroundDrawable(drawable);
    }

    @Test
    public void backgroundResource() throws Exception {
        // Given
        int imageResource = android.R.drawable.ic_menu_report_image;

        // When
        BlueTapeDsl
                .backgroundResource(imageResource)
                .bind(view);

        // Then
        verify(view).setBackgroundResource(imageResource);
    }

    @Test
    public void onClick() throws Exception {
        // Given
        View.OnClickListener listener = mock(View.OnClickListener.class);

        // When
        BlueTapeDsl
                .onClick(listener)
                .bind(view);

        // Then
        verify(view).setOnClickListener(listener);
    }

    @Test
    public void onClick_Shortened() throws Exception {
        // Given
        ShortenedOnClickListener listener = mock(ShortenedOnClickListener.class);

        ArgumentCaptor<View.OnClickListener> listenerCaptor = ArgumentCaptor.forClass(View.OnClickListener.class);

        // When
        BlueTapeDsl
                .onClick(listener)
                .bind(view);

        verify(view).setOnClickListener(listenerCaptor.capture());
        listenerCaptor.getValue().onClick(view);

        // Then
        verify(listener).onClick();
    }

    @Test
    public void onClick_Shortened_Clear() throws Exception {
        // When
        BlueTapeDsl
                .onClick((ShortenedOnClickListener) null)
                .bind(view);

        // Then
        verify(view).setOnClickListener(null);
    }

    @Test
    public void onLongClick() throws Exception {
        // Given
        View.OnLongClickListener listener = mock(View.OnLongClickListener.class);

        // When
        BlueTapeDsl
                .onLongClick(listener)
                .bind(view);

        // Then
        verify(view).setOnLongClickListener(listener);
    }

    @Test
    public void onTouch() throws Exception {
        // Given
        View.OnTouchListener listener = mock(View.OnTouchListener.class);

        // When
        BlueTapeDsl
                .onTouch(listener)
                .bind(view);

        // Then
        verify(view).setOnTouchListener(listener);
    }

    @Test
    public void onToggle() throws Exception {
        // Given
        CompoundButton compoundButton = mock(CompoundButton.class);
        CompoundButton.OnCheckedChangeListener listener = mock(CompoundButton.OnCheckedChangeListener.class);

        // When
        BlueTapeDsl
                .onToggle(listener)
                .bind(compoundButton);

        // Then
        verify(compoundButton).setOnCheckedChangeListener(listener);
    }

    @Test
    public void onTextChanged() throws Exception {
        // Given
        OnTextChangedListener listener = mock(OnTextChangedListener.class);

        // When
        BindingFunction bindingFunction = BlueTapeDsl
                .onTextChanged(listener);

        // Then
        assertTrue(bindingFunction instanceof TextChangedBindingFunction);
    }

}