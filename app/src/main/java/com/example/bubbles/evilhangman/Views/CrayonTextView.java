package com.example.bubbles.evilhangman.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CrayonTextView extends TextView {

    public CrayonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CrayonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CrayonTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                    "dk_crayon_crumble.ttf");
            setTypeface(tf);
        }
    }
}