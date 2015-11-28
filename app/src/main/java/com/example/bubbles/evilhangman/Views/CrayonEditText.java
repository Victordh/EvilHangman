package com.example.bubbles.evilhangman.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CrayonEditText extends EditText {
    private final static int ROBOTO = 0;
    private final static int ROBOTO_CONDENSED = 1;

    public CrayonEditText(Context context) {
        super(context);
        init();
    }

    public CrayonEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CrayonEditText(Context context, AttributeSet attrs, int defStyle) {
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