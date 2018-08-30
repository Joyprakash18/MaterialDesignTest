package com.sayan.sample.materialdesigntest.customViews;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

import com.sayan.sample.materialdesigntest.R;

public class CustomTextInputEditText extends TextInputEditText {
    public CustomTextInputEditText(Context context) {
        super(context);
        doAdditionalWorks(context);
    }

    public CustomTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        doAdditionalWorks(context);
    }

    public CustomTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doAdditionalWorks(context);
    }

    private void doAdditionalWorks(Context context) {
        super.setHintTextColor(Color.GRAY);
        super.setHighlightColor(Color.BLUE);
//        super.setBackground(context.getResources().getDrawable(R.drawable.input_text_field_background));
    }
}
