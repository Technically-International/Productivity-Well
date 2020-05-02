package com.example.productivitywell.Fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.productivitywell.R;

public class Circle extends View {

    private static final int START_ANGLE_POINT = -90;

    private final Paint paint;
    private final RectF rect;

    private float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 20;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.CENTER);


        //size 200x200 example
        rect = new RectF(strokeWidth, strokeWidth, 300 + strokeWidth, 300 + strokeWidth);

        //Initial Angle (optional, it can be zero)
        angle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
        canvas.drawARGB(0, 225, 225, 255);

        int x = getWidth()/2;
        int y = getHeight()/2;

        rect.set(x-350, y-350, 350+x, 350+y);

    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
