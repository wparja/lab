package com.wparja.myfloatingbutton.Component.Piece;

import android.content.Context;
import android.graphics.Point;
import android.graphics.RectF;

import com.wparja.myfloatingbutton.Component.BoomButtons.BoomButtonBuilder;
import com.wparja.myfloatingbutton.Component.BoomMenuButton;

import java.util.ArrayList;

public class PiecePlaceManager {
    public static BoomPiece createPiece(BoomMenuButton bmb, BoomButtonBuilder builder) {
        switch (bmb.getButtonEnum()) {

            case SimpleCircle:
            case TextInsideCircle:
            case TextOutsideCircle:
                return createDot(bmb.getContext(), builder, bmb.getPieceCornerRadius());
                break;
            case Ham:
                return createHam(bmb.getContext(), builder, bmb.getPieceCornerRadius());
                break;
            case Unknown:
                throw new RuntimeException("Unknown button-enum!");
        }
    }

    private static Ham createHam(Context context, BoomButtonBuilder builder, float pieceCornerRadius) {
        Ham ham = new Ham(context);
        builder.piece(ham);
        ham.init(builder.pieceColor(context), pieceCornerRadius);
        return ham;
    }

    private static Dot createDot(Context context, BoomButtonBuilder builder, float pieceCornerRadius) {
        Dot dot = new Dot(context);
        builder.piece(dot);
        dot.init(builder.pieceColor(context), pieceCornerRadius);
        return dot;
    }


    public static ArrayList<RectF> getShareDotPositions(BoomMenuButton boomMenuButton, Point point, int size) {
    }

    public static ArrayList<RectF> getDotPositions(BoomMenuButton boomMenuButton, Point point) {
    }

    public static ArrayList<RectF> getHamPositions(BoomMenuButton boomMenuButton, Point point) {

    }
}
