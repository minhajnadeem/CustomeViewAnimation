package com.example.minhaj.customeviewanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by minhaj on 21/08/2017.
 */

public class CustomView extends View implements View.OnClickListener{

    private int cx,cy,radius,maxRadius;
    private Paint paint;
    private Path path;
    private ValueAnimator animator;
    private Canvas myCanvas;

    private final String TAG = "tag";
    public CustomView(Context context) {
        super(context);
        Log.d(TAG,"CustomView");
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG,"onAttachToWindow");
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG,"onSizeChanged");
        radius = w/2;
        setUpAnimator(w/2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure - width :"+widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.d(TAG,"onDraw");
        myCanvas = canvas;

        cx = getWidth()/2;
        cy = getHeight()/2;
        Log.d(TAG,"cx :"+getWidth());
        //radius = getWidth()/2;

        paint.setColor(Color.BLUE);
        canvas.drawCircle(cx,cy,radius,paint);
        super.onDraw(canvas);
    }

    private void setUpAnimator(int maxRadius) {
        Log.d(TAG,"setUpAnimator - max Radius :"+maxRadius);
        animator = ValueAnimator.ofInt(0,maxRadius);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                radius = (int)valueAnimator.getAnimatedValue();
                Log.d(TAG,"animate circle - radius:"+radius);
                invalidate();
            }
        });
        //animator.start();
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),"clicked", Toast.LENGTH_SHORT).show();
        animator.start();
    }
}
