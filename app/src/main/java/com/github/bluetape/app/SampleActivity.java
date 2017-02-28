package com.github.bluetape.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.bluetape.function.BindingFunction;
import com.github.bluetape.BlueTape;

import static com.github.bluetape.BlueTapeDsl.composite;
import static com.github.bluetape.BlueTapeDsl.id;
import static com.github.bluetape.BlueTapeDsl.text;

public class SampleActivity extends AppCompatActivity {

    private String text;

    public static BindingFunction textColor(int color) {
        return view -> ((TextView) view).setTextColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        BlueTape blueTape = BlueTape
                .with(() -> composite(
                        id(R.id.text,
                                text(text)
                        ),
                        id(R.id.container,
                                id(R.id.text,
                                        text("Inner"),
                                        textColor(Color.RED)
                                )
                        )
                ))
                .into(this);

        text = "First";
        blueTape.update();

        text = "Second";
        blueTape.update();
    }

}
