package tw.edu.nutc.imac.blockchainfingerprint.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import tw.edu.nutc.imac.blockchainfingerprint.R;

public class CustomDialogButton extends android.support.v7.widget.AppCompatButton {
    private int mHeight;
    private int mWidth;
    private int mRadiusSize;
    private int mShadowSize;
    private RectF mShadowRect;
    private RectF mBackgroundRect;
    private RectF mBackgroundRect1;
    private Paint mBackgroundPaint;
    private Paint mFirstShadowPaint;
    private Paint mSecondShadowPaint;
    private Handler handler;


    private Paint mPaint;
    private float duration = 250;
    private float speed = 1;
    private float radius = 0;
    private float endRadius = 0;
    private float rippleX = 0;
    private float rippleY = 0;
    private OnClickListener mOnClickListener = null;
    private int touchAction;

    public CustomDialogButton(Context context) {
        this(context, null);
        init();
    }

    public CustomDialogButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CustomDialogButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(attrs, defStyleAttr);
        init();
    }

    private void setAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomDialogButton, defStyleAttr, 0);

        mRadiusSize = typedArray.getDimensionPixelSize(R.styleable.CustomDialogButton_radiusSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mRadiusSize, getResources().getDisplayMetrics()));

        mShadowSize = typedArray.getDimensionPixelSize(R.styleable.CustomDialogButton_shadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mShadowSize, getResources().getDisplayMetrics()));

        typedArray.recycle();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(0xff2493ee);

        mFirstShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstShadowPaint.setStyle(Paint.Style.FILL);
        mFirstShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
        mFirstShadowPaint.setColor(0xe195ccf7);

        mSecondShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondShadowPaint.setStyle(Paint.Style.FILL);
        mSecondShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
        mSecondShadowPaint.setColor(0x8243d3d0);

        mShadowRect = new RectF();
        mBackgroundRect = new RectF();
        mBackgroundRect1 = new RectF();

        handler = new Handler();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mShadowRect, mRadiusSize, mRadiusSize, mFirstShadowPaint);
        canvas.drawRoundRect(mBackgroundRect, mRadiusSize, mRadiusSize, mSecondShadowPaint);
        canvas.drawRoundRect(mBackgroundRect1, mRadiusSize, mRadiusSize, mBackgroundPaint);
        super.onDraw(canvas);
        if (radius > 0 && radius < endRadius) {
            canvas.drawCircle(rippleX, rippleY, radius, mPaint);
            if (touchAction == MotionEvent.ACTION_UP)
                invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        rippleX = event.getX();
        rippleY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: {
                getParent().requestDisallowInterceptTouchEvent(false);
                touchAction = MotionEvent.ACTION_UP;

                radius = 1;
                endRadius = Math.max(Math.max(Math.max(mWidth - rippleX, rippleX), rippleY), mHeight - rippleY);
                speed = endRadius / duration * 10;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (radius < endRadius) {
                            radius += speed;
                            mPaint.setAlpha(90 - (int) (radius / endRadius * 90));
                            handler.postDelayed(this, 1);
                        } else {
                            mOnClickListener.onClick(CustomDialogButton.this);
                        }
                    }
                }, 10);
                invalidate();
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                getParent().requestDisallowInterceptTouchEvent(false);
                touchAction = MotionEvent.ACTION_CANCEL;
                radius = 0;
                invalidate();
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                touchAction = MotionEvent.ACTION_UP;
                endRadius = Math.max(Math.max(Math.max(mWidth - rippleX, rippleX), rippleY), mHeight - rippleY);
                mPaint.setAlpha(90);
                radius = endRadius / 4;
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                if (rippleX < 0 || rippleX > mWidth || rippleY < 0 || rippleY > mHeight) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    touchAction = MotionEvent.ACTION_CANCEL;
                    radius = 0;
                    invalidate();
                    break;
                } else {
                    touchAction = MotionEvent.ACTION_MOVE;
                    invalidate();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void setOnClickListener(OnClickListener clickListener) {
        mOnClickListener = clickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        mShadowRect.set(mShadowSize, mShadowSize + mShadowSize, mWidth - mShadowSize, mHeight - mShadowSize);
        mBackgroundRect.set(mShadowSize, mShadowSize, mWidth - mShadowSize, mHeight - mShadowSize);
        mBackgroundRect1.set(mShadowSize, mShadowSize, mWidth - mShadowSize, mHeight - mShadowSize);
    }

}
