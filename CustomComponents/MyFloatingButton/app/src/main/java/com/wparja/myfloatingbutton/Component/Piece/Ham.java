package com.wparja.myfloatingbutton.Component.Piece;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.wparja.myfloatingbutton.Component.Util;
import com.wparja.myfloatingbutton.R;

public class Ham extends BoomPiece {

    public Ham(Context context) {
        super(context);
    }

    @Override
    public void init(int color, float cornerRadius) {
        Drawable backgroundDrawable = Util.getDrawable(this, R.drawable.piece, null);
        ((GradientDrawable)backgroundDrawable).setColor(color);
        ((GradientDrawable)backgroundDrawable).setCornerRadius(cornerRadius);
        Util.setDrawable(this, backgroundDrawable);
    }

    @Override
    public void setColor(int color) {
        ((GradientDrawable)getBackground()).setColor(color);
    }

    @Override
    public void setColorRes(int colorRes) {
        setColor(Util.getColor(getContext(), colorRes));
    }
}
