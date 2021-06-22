package com.wparja.myfloatingbutton.Component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wparja.myfloatingbutton.Component.Animation.AnimationManager;
import com.wparja.myfloatingbutton.Component.Animation.BoomEnum;
import com.wparja.myfloatingbutton.Component.Animation.EaseEnum;
import com.wparja.myfloatingbutton.Component.Animation.OrderEnum;
import com.wparja.myfloatingbutton.Component.Animation.ShareLinesView;
import com.wparja.myfloatingbutton.Component.BoomButtons.BoomButton;
import com.wparja.myfloatingbutton.Component.BoomButtons.BoomButtonBuilder;
import com.wparja.myfloatingbutton.Component.BoomButtons.ButtonPlaceAlignmentEnum;
import com.wparja.myfloatingbutton.Component.BoomButtons.ButtonPlaceEnum;
import com.wparja.myfloatingbutton.Component.Piece.BoomPiece;
import com.wparja.myfloatingbutton.Component.Piece.PiecePlaceEnum;
import com.wparja.myfloatingbutton.Component.Piece.PiecePlaceManager;
import com.wparja.myfloatingbutton.R;

import java.util.ArrayList;

public class BoomMenuButton extends FrameLayout {

    // Basic
    private Context context;
    private boolean needToLayout = true;
    private boolean cacheOptimization;
    private boolean boomInWholeScreen;
    private boolean inList;
    private boolean inFragment;
    private boolean isBackPressListened = true;
    private Runnable layoutJobsRunnable;

    // Shadow
    private boolean shadowEffect;
    private int shadowOffsetX;
    private int shadowOffsetY;
    private int shadowRadius;
    private int shadowColor;
    private BMBShadow shadow;

    // Button
    private int buttonRadius;
    private ButtonEnum buttonEnum = ButtonEnum.Unknown;
    private boolean backgroundEffect;
    private boolean rippleEffect;
    private int normalColor;
    private int highlightedColor;
    private int unableColor;
    private boolean draggable;
    private float startPositionX;
    private float startPositionY;
    private boolean ableToStartDragging = false;
    private boolean isDragging = false;
    private float lastMotionX = -1;
    private float lastMotionY = -1;
    private Rect edgeInsetsInParentView;
    private FrameLayout button;

    // Piece
    private ArrayList<BoomPiece> pieces;
    private ArrayList<RectF> piecePositions;
    private float dotRadius;
    private float hamWidth;
    private float hamHeight;
    private float pieceCornerRadius = -1;
    private float pieceHorizontalMargin;
    private float pieceVerticalMargin;
    private float pieceInclinedMargin;
    private float shareLineLength;
    private int shareLine1Color;
    private int shareLine2Color;
    private float shareLineWidth;
    private ShareLinesView shareLinesView;
    private PiecePlaceEnum piecePlaceEnum = PiecePlaceEnum.Unknown;
    private ArrayList<PointF> customPiecePlacePositions = new ArrayList<>();

    // Animation
    private int animatingViewNumber = 0;
    private OnBoomListener onBoomListener;
    private int dimColor;
    private long showDuration;
    private long showDelay;
    private long hideDuration;
    private long hideDelay;
    private boolean cancelable;
    private boolean autoHide;
    private OrderEnum orderEnum;
    private int frames;
    private BoomEnum boomEnum;
    private EaseEnum showMoveEaseEnum;
    private EaseEnum showScaleEaseEnum;
    private EaseEnum showRotateEaseEnum;
    private EaseEnum hideMoveEaseEnum;
    private EaseEnum hideScaleEaseEnum;
    private EaseEnum hideRotateEaseEnum;
    private int rotateDegree;
    private boolean use3DTransformAnimation;
    private boolean autoBoom;
    private boolean autoBoomImmediately;
    private BoomStateEnum boomStateEnum = BoomStateEnum.DidReboom;

    // Background
    private BackgroundView background;

    // Boom Buttons
    private ArrayList<BoomButton> boomButtons = new ArrayList<>();
    private ArrayList<BoomButtonBuilder> boomButtonBuilders = new ArrayList<>();
    private float simpleCircleButtonRadius;
    private float textInsideCircleButtonRadius;
    private float textOutsideCircleButtonWidth;
    private float textOutsideCircleButtonHeight;
    private float hamButtonWidth;
    private float hamButtonHeight;
    private ButtonPlaceEnum buttonPlaceEnum = ButtonPlaceEnum.Unknown;
    private ArrayList<PointF> customButtonPlacePositions = new ArrayList<>();
    private ButtonPlaceAlignmentEnum buttonPlaceAlignmentEnum;
    private float buttonHorizontalMargin;
    private float buttonVerticalMargin;
    private float buttonInclinedMargin;
    private float buttonTopMargin;
    private float buttonBottomMargin;
    private float buttonLeftMargin;
    private float buttonRightMargin;
    private ArrayList<PointF> startPositions;
    private ArrayList<PointF> endPositions;
    private float bottomHamButtonTopMargin;
    private boolean needToCreateButtons = true;
    private boolean needToCalculateStartPositions = true;
    private int lastReboomIndex = -1;
    private boolean isOrientationAdaptable;
    private int lastOrientation = OrientationEventListener.ORIENTATION_UNKNOWN;
    private boolean isOrientationChanged = false;
    private OrientationEventListener orientationEventListener;

    public BoomMenuButton(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public BoomMenuButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BoomMenuButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public BoomMenuButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.bmb, this, true);
        initAttrs(context, attrs);
        initShadow();
        initButton();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BoomMenuButton, 0, 0);
        if (typedArray == null) return;
        try {
            // Basic
            cacheOptimization = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cacheOptimization, R.bool.default_bmb_cacheOptimization);
            boomInWholeScreen = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_boomInWholeScreen, R.bool.default_bmb_boomInWholeScreen);
            inList = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inList, R.bool.default_bmb_inList);
            inFragment = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inFragment, R.bool.default_bmb_inFragment);
            isBackPressListened = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_backPressListened, R.bool.default_bmb_backPressListened);
            isOrientationAdaptable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_orientationAdaptable, R.bool.default_bmb_orientationAdaptable);

            // Shadow
            shadowEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_shadowEffect, R.bool.default_bmb_shadow_effect);
            shadowRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shadowRadius, R.dimen.default_bmb_shadow_radius);
            shadowOffsetX = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetX, R.dimen.default_bmb_shadow_offset_x);
            shadowOffsetY = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetY, R.dimen.default_bmb_shadow_offset_y);
            shadowColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shadowColor, R.color.default_bmb_shadow_color);

            // Button
            buttonRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_buttonRadius, R.dimen.default_bmb_button_radius);
            buttonEnum = ButtonEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonEnum, R.integer.default_bmb_button_enum));
            backgroundEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_backgroundEffect, R.bool.default_bmb_background_effect);
            rippleEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_rippleEffect, R.bool.default_bmb_ripple_effect);
            normalColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_normalColor, R.color.default_bmb_normal_color);
            highlightedColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_highlightedColor, R.color.default_bmb_highlighted_color);
            if (highlightedColor == Color.TRANSPARENT) highlightedColor = Util.getDarkerColor(normalColor);
            unableColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_unableColor, R.color.default_bmb_unable_color);
            if (unableColor == Color.TRANSPARENT) unableColor = Util.getLighterColor(normalColor);
            draggable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_draggable, R.bool.default_bmb_draggable);
            edgeInsetsInParentView = new Rect(0, 0, 0, 0);
            edgeInsetsInParentView.left = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsLeft, R.dimen.default_bmb_edgeInsetsLeft);
            edgeInsetsInParentView.top = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsTop, R.dimen.default_bmb_edgeInsetsTop);
            edgeInsetsInParentView.right = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsRight, R.dimen.default_bmb_edgeInsetsRight);
            edgeInsetsInParentView.bottom = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsBottom, R.dimen.default_bmb_edgeInsetsBottom);

            // Piece
            dotRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_dotRadius, R.dimen.default_bmb_dotRadius);
            hamWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamWidth, R.dimen.default_bmb_hamWidth);
            hamHeight = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamHeight, R.dimen.default_bmb_hamHeight);
            pieceCornerRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_pieceCornerRadius, R.dimen.default_bmb_pieceCornerRadius);
            pieceHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceHorizontalMargin, R.dimen.default_bmb_pieceHorizontalMargin);
            pieceVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceVerticalMargin, R.dimen.default_bmb_pieceVerticalMargin);
            pieceInclinedMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceInclinedMargin, R.dimen.default_bmb_pieceInclinedMargin);
            shareLineLength = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_sharedLineLength, R.dimen.default_bmb_sharedLineLength);
            shareLine1Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine1Color, R.color.default_bmb_shareLine1Color);
            shareLine2Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine2Color, R.color.default_bmb_shareLine2Color);
            shareLineWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shareLineWidth, R.dimen.default_bmb_shareLineWidth);
            piecePlaceEnum = PiecePlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_piecePlaceEnum, R.integer.default_bmb_pieceEnum));

            // Animation
            dimColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_dimColor, R.color.default_bmb_dimColor);
            showDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDuration, R.integer.default_bmb_showDuration);
            showDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDelay, R.integer.default_bmb_showDelay);
            hideDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDuration, R.integer.default_bmb_hideDuration);
            hideDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDelay, R.integer.default_bmb_hideDelay);
            cancelable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cancelable, R.bool.default_bmb_cancelable);
            autoHide = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoHide, R.bool.default_bmb_autoHide);
            orderEnum = OrderEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_orderEnum, R.integer.default_bmb_orderEnum));
            frames = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_frames, R.integer.default_bmb_frames);
            boomEnum = BoomEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_boomEnum, R.integer.default_bmb_boomEnum));
            showMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showMoveEaseEnum, R.integer.default_bmb_showMoveEaseEnum));
            showScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showScaleEaseEnum, R.integer.default_bmb_showScaleEaseEnum));
            showRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showRotateEaseEnum, R.integer.default_bmb_showRotateEaseEnum));
            hideMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideMoveEaseEnum, R.integer.default_bmb_hideMoveEaseEnum));
            hideScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideScaleEaseEnum, R.integer.default_bmb_hideScaleEaseEnum));
            hideRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideRotateEaseEnum, R.integer.default_bmb_hideRotateEaseEnum));
            rotateDegree = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_rotateDegree, R.integer.default_bmb_rotateDegree);
            use3DTransformAnimation = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_use3DTransformAnimation, R.bool.default_bmb_use3DTransformAnimation);
            autoBoom = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoBoom, R.bool.default_bmb_autoBoom);
            autoBoomImmediately = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoBoomImmediately, R.bool.default_bmb_autoBoomImmediately);

            // Boom buttons
            buttonPlaceEnum = ButtonPlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceEnum, R.integer.default_bmb_buttonPlaceEnum));
            buttonPlaceAlignmentEnum = ButtonPlaceAlignmentEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceAlignmentEnum, R.integer.default_bmb_buttonPlaceAlignmentEnum));
            buttonHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonHorizontalMargin, R.dimen.default_bmb_buttonHorizontalMargin);
            buttonVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonVerticalMargin, R.dimen.default_bmb_buttonVerticalMargin);
            buttonInclinedMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonInclinedMargin, R.dimen.default_bmb_buttonInclinedMargin);
            buttonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonTopMargin, R.dimen.default_bmb_buttonTopMargin);
            buttonBottomMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonBottomMargin, R.dimen.default_bmb_buttonBottomMargin);
            buttonLeftMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonLeftMargin, R.dimen.default_bmb_buttonLeftMargin);
            buttonRightMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonRightMargin, R.dimen.default_bmb_buttonRightMargin);
            bottomHamButtonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_bottomHamButtonTopMargin, R.dimen.default_bmb_bottomHamButtonTopMargin);


        } finally {
            typedArray.recycle();
        }
    }

    private void initShadow() {
        if (shadow == null) shadow = (BMBShadow) findViewById(R.id.shadow);
        boolean hasShadow = shadowEffect && backgroundEffect && !inList;
        shadow.setShadowEffect(hasShadow);
        if (hasShadow) {
            shadow.setShadowOffSetX(shadowOffsetX);
            shadow.setShadowOffSetY(shadowOffsetY);
            shadow.setShadowColor(shadowColor);
            shadow.setShadowRadius(shadowRadius);
            shadow.setShadowCornerRadius(shadowRadius + buttonRadius);
        } else {
            shadow.clearShadow();
        }
        
    }

    private void initButton() {
        if (button == null) button = (FrameLayout) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boom();
            }
        });
        
        initDraggableTouchListener();
        setButtonSize();
        setButtonBackground();
    }

    private void setButtonSize() {
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = buttonRadius * 2;
        params.height = buttonRadius * 2;
        button.setLayoutParams(params);
    }

    private void setButtonBackground() {
        if (backgroundEffect && !inList) {
            if (rippleEffect && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(highlightedColor),
                        Util.getOvalDrawable(button, normalColor),
                        null);
                Util.setDrawable(button, rippleDrawable);
            } else {
                StateListDrawable stateListDrawable = Util.getOvalStateListBitmapDrawable(
                        button,
                        buttonRadius,
                        normalColor,
                        highlightedColor,
                        unableColor);
                Util.setDrawable(button, stateListDrawable);

            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackgroundBorderless));
            } else {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackground));
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && isBackPressListened && (boomStateEnum == BoomStateEnum.WillBoom || boomStateEnum == BoomStateEnum.DidBoom)) {
            reboom();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // region Place Pieces

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isOrientationChanged) {
            reLayoutAfterOrientation();
        }

        if (needToLayout) {
            if (inList) delayToDoLayoutJobs();
            else doLayoutJobs();
        }

        needToLayout = false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        checkAutoBoom();
    }

    private void doLayoutJobs() {
        if (uninitializedBoomButtons()) return;
        clearPieces();
        createPieces();
        placeShareLinesView();
        placePieces();
        placePiecesAtPositions();
        setShareLinesViewData();
    }
    private void clearPieces() {
        if (pieces != null) {
            for (BoomPiece piece : pieces) {
                button.removeView(piece);
            }
            pieces.clear();
        }
    }

    private void createPieces() {
        calculatePiecePositions();
        int pieceNumber = pieceNumber();
        for (int i = 0; i < pieceNumber; i++) {
            pieces.add(PiecePlaceManager.createPiece(this, boomButtonBuilders.get(i)));
        }
    }

    private void placePieces() {
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == PiecePlaceEnum.Share) {
            indexes = AnimationManager.getOrderIndex(OrderEnum.DEFAULT, pieces.size());
        } else {
            indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        }
        for (int i = indexes.size() - 1; i >= 0; i--) {
            button.addView(pieces.get(indexes.get(i)));
        }
    }
    private void placePiecesAtPositions() {
        int pieceNumber = pieceNumber();
        for (int i = 0; i < pieceNumber; i++) {
            pieces.get(i).place(piecePositions.get(i));
        }
    }

    private void calculatePiecePositions() {
        switch (buttonEnum) {

            case SimpleCircle:
            case TextInsideCircle:
            case TextOutsideCircle:
                if (piecePlaceEnum == PiecePlaceEnum.Share) {
                    piecePositions = PiecePlaceManager.getShareDotPositions(this, new Point(button.getWidth(), button.getHeight()), boomButtonBuilders.size());
                } else {
                    piecePositions = PiecePlaceManager.getDotPositions(this, new Point(button.getWidth(), button.getHeight()));
                }
                break;
            case Ham:
                piecePositions = PiecePlaceManager.getHamPositions(this, new Point(button.getWidth(), button.getHeight()));
                break;
            case Unknown:
                throw new RuntimeException("The button-enum is unknown!");
        }
    }

    // endregion

    public ButtonEnum getButtonEnum() {
        return buttonEnum;
    }

    public float getPieceCornerRadius() {
        return pieceCornerRadius;
    }

    private int pieceNumber() {
        return 0;
    }

    private void setShareLinesViewData() {
    }





    private void placeShareLinesView() {
    }





    private boolean uninitializedBoomButtons() {
        return false;
    }

    private void checkAutoBoom() {
    }

    private void delayToDoLayoutJobs() {
    }

    private void reLayoutAfterOrientation() {
    }

    private void initDraggableTouchListener() {
    }

    private void boom() {
    }


    public float getDotRadius() {
        return 0;
    }

    public long getShowDelay() {
        return 0;
    }

    public long getShowDuration() {
        return 0;
    }

    public long getHideDuration() {
        return 0;
    }

    public long getHideDelay() {
        return 0;
    }

    public int getDimColor() {
        return 0;
    }

    public ViewGroup getParentView() {
        return null;
    }

    public void onBackgroundClicked() {
        if (isAnimating()) return;
        if (onBoomListener != null) onBoomListener.onBackgroundClick();
        if (cancelable) reboom();
    }

    private void reboom() {
    }

    private boolean isAnimating() {
        return false;
    }
}
