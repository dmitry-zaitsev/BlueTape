package com.github.bluetape.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * {@link IntDef} which defines possible arguments for {@link android.view.View#setVisibility(int)}.
 */
@IntDef({VISIBLE, INVISIBLE, GONE})
@Retention(RetentionPolicy.SOURCE)
public @interface Visibility {
}
