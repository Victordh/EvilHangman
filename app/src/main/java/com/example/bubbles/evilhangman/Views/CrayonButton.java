package com.example.bubbles.evilhangman.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CrayonButton extends Button {
    private final static int ROBOTO = 0;
    private final static int ROBOTO_CONDENSED = 1;

    public CrayonButton(Context context) {
        super(context);
        init();
    }

    public CrayonButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CrayonButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "dk_crayon_crumble.ttf");
            setTypeface(tf);
        }
    }
}