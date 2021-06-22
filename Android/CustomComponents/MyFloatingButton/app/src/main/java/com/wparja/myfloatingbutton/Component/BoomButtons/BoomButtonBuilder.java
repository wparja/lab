package com.wparja.myfloatingbutton.Component.BoomButtons;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;

import com.wparja.myfloatingbutton.Component.Piece.BoomPiece;
import com.wparja.myfloatingbutton.Component.Util;

import java.lang.ref.WeakReference;

public abstract class BoomButtonBuilder<T> {

    private BoomPiece piece = null;
    private WeakReference<BoomButton> boomButtonWeakReference;

    // Basic
    int index = -1;
    InnerOnBoomButtonClickListener listener;
    OnBMClickListener onBMClickListener;
    boolean rotateImage = true;
    boolean rotateText = true;
    boolean containsSubText = true;

    // piece
    Integer pieceColor = null;
    int pieceColorRes = 0;

    // Shadow
    boolean shadowEffect = true;
    int shadowOffsetX = Util.dp2px(0);
    int shadowOffsetY = Util.dp2px(3);
    int shadowRadius = Util.dp2px(8);
    int shadowColor = Color.parseColor("#88757575");
    int shadowCornerRadius = Util.dp2px(5);

    // Images
    int normalImageRes = 0;
    int highlightedImageRes = 0;
    int unableImageRes = 0;
    Drawable normalImageDrawable = null;
    Drawable highlightedImageDrawable = null;
    Drawable unableImageDrawable = null;
    Rect imageRect = new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70));
    Rect imagePadding = new Rect(0, 0, 0, 0);

    // Text
    int normalTextRes = 0;
    int highlightedTextRes = 0;
    int unableTextRes = 0;
    String normalText;
    String highlightedText;
    String unableText;
    int normalTextColor = Color.WHITE;
    int normalTextColorRes = 0;
    int highlightedTextColor = Color.WHITE;
    int highlightedTextColorRes = 0;
    int unableTextColor = Color.WHITE;
    int unableTextColorRes = 0;
    Rect textRect = new Rect(Util.dp2px(15), Util.dp2px(52), Util.dp2px(65), Util.dp2px(72));
    Rect textPadding = new Rect(0, 0, 0, 0);
    Typeface typeface = null;
    int maxLines = 1;
    int textGravity = Gravity.CENTER;
    TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.MARQUEE;
    int textSize = 10;

    // Sub text
    int subNormalTextRes = 0;
    int subHighlightedTextRes = 0;
    int subUnableTextRes = 0;
    String subNormalText;
    String subHighlightedText;
    String subUnableText;
    int subNormalTextColor = Color.WHITE;
    int subNormalTextColorRes = 0;
    int subHighlightedTextColor = Color.WHITE;
    int subHighlightedTextColorRes = 0;
    int subUnableTextColor = Color.WHITE;
    int subUnableTextColorRes = 0;
    Rect subTextRect = new Rect(Util.dp2px(70), Util.dp2px(35), Util.dp2px(280), Util.dp2px(55));
    Rect subTextPadding = new Rect(0, 0, 0, 0);
    Typeface subTypeface = null;
    int subMaxLines = 1;
    int subTextGravity = Gravity.START|Gravity.CENTER_VERTICAL;
    TextUtils.TruncateAt subEllipsize = TextUtils.TruncateAt.MARQUEE;
    int subTextSize = 10;

    // Text for text-outside-circle-button
    int textTopMargin = Util.dp2px(5);
    int textWidth = Util.dp2px(80);
    int textHeight = Util.dp2px(20);

    // Button
    boolean rippleEffect = true;
    int normalColor = Util.getColor();
    int normalColorRes = 0;
    int highlightedColor = Util.getColor();
    int highlightedColorRes = 0;
    int unableColor = Util.getColor();
    int unableColorRes = 0;
    boolean unable = false;
    int buttonRadius = Util.dp2px(40);
    int buttonWidth = Util.dp2px(300);
    int buttonHeight = Util.dp2px(60);
    int buttonCornerRadius = Util.dp2px(5);
    boolean isRound = true;  // only for simple circle/text inside/text outside circle button

    public abstract BoomButton build(Context context);

    public int pieceColor(Context context) {
        if (pieceColor == null && pieceColorRes == 0)
            if (unable) return Util.getColor(context, unableColorRes, unableColor);
            else return Util.getColor(context, normalColorRes, normalColor);
        else if (pieceColor == null) return Util.getColor(context, pieceColorRes);
        else return Util.getColor(context, pieceColorRes, pieceColor);
    }

    public void setUnable(boolean unable) {
        this.unable = unable;
    }

    public BoomButton button() {
        if (boomButtonWeakReference != null) return boomButtonWeakReference.get();
        return null;
    }

    BoomButton weakReferenceButton(BoomButton button) {
        boomButtonWeakReference = new WeakReference<>(button);
        return button;
    }


    /**
     * Set the piece of the builder, only used in BMB package.
     *
     * @param piece the piece
     */
    public void piece(BoomPiece piece) {
        this.piece = piece;
    }
}
