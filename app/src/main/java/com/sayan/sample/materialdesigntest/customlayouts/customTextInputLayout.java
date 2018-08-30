package com.sayan.sample.materialdesigntest.customlayouts;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

import com.sayan.sample.materialdesigntest.R;

public class customTextInputLayout extends TextInputLayout {
    public customTextInputLayout(Context context) {
        super(context);
        doAdditionalWorks(context);
    }


    public customTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        doAdditionalWorks(context);
    }

    public customTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doAdditionalWorks(context);
    }

    private void doAdditionalWorks(Context context) {
        super.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        super.setBoxStrokeColor(Color.GRAY);
        super.setBoxCornerRadii(0.1f, 30.0f, 30.0f, 0.1f);
        super.setAlpha(0.8f);
        super.setHelperTextEnabled(true);
    }
}