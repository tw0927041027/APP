package tw.edu.nutc.imac.blockchainfingerprint.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import tw.edu.nutc.imac.blockchainfingerprint.R;

/**
 * Created by 依杰 on 2018/1/26.
 */

public class CustomEditContainer extends RelativeLayout {
    private int mHeight;
    private int mWidth;
    private int mRadiusSize;
    private int mFirstShadowSize;
    private int mSecondShadowSize;
    private int mBackgroundPaintSize;
    private RectF mShadowRect;
    private RectF mSecondShadowRect;
    private RectF mRimRect;
    private RectF mContentRect;
    private Paint mBackgroundPaint;
    private Paint mFirstShadowPaint;
    private Paint mSecondShadowPaint;
    private Paint mContentPaint;
    private Paint mRimPaint;
    private boolean mIsFocus = false;
    private boolean mIsError = false;
    private int mBackgroundPaintColor;
    private int mRimPaintColor;

    public CustomEditContainer(Context context) {
        this(context, null);
        init();
    }

    public CustomEditContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CustomEditContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(attrs, defStyleAttr);
        init();
    }

    private void setAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomEditContainer, defStyleAttr, 0);

        mBackgroundPaintSize = typedArray.getDimensionPixelSize(R.styleable.CustomEditContainer_backgroundPaintSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mBackgroundPaintSize, getResources().getDisplayMetrics()));

        mRadiusSize = typedArray.getDimensionPixelSize(R.styleable.CustomEditContainer_radiusSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mRadiusSize, getResources().getDisplayMetrics()));

        mFirstShadowSize = typedArray.getDimensionPixelSize(R.styleable.CustomDialogButton_shadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mFirstShadowSize, getResources().getDisplayMetrics()));

        mSecondShadowSize = typedArray.getDimensionPixelSize(R.styleable.CustomEditContainer_secondShadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mSecondShadowSize, getResources().getDisplayMetrics()));

        mBackgroundPaintColor = typedArray.getColor(R.styleable.CustomEditContainer_backgroundPaintColor, mBackgroundPaintColor);

        mRimPaintColor = typedArray.getColor(R.styleable.CustomEditContainer_rimPaintColor, mRimPaintColor);

        typedArray.recycle();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundPaintColor);

        mFirstShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstShadowPaint.setStyle(Paint.Style.FILL);
        mFirstShadowPaint.setMaskFilter(new BlurMaskFilter(mFirstShadowSize, BlurMaskFilter.Blur.OUTER));
        mFirstShadowPaint.setColor(0xe295ccf7);

        mSecondShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondShadowPaint.setStyle(Paint.Style.FILL);
        mSecondShadowPaint.setMaskFilter(new BlurMaskFilter(mSecondShadowSize, BlurMaskFilter.Blur.OUTER));
        mSecondShadowPaint.setColor(0x8043d3d0);

        mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContentPaint.setStyle(Paint.Style.STROKE);
        mContentPaint.setColor(mBackgroundPaintColor);
        mContentPaint.setStrokeWidth(mFirstShadowSize);

        mRimPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRimPaint.setStyle(Paint.Style.STROKE);
        mRimPaint.setColor(mRimPaintColor);
        mRimPaint.setStrokeWidth(mBackgroundPaintSize);

        mShadowRect = new RectF();
        mSecondShadowRect = new RectF();
        mRimRect = new RectF();
        mContentRect = new RectF();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public void setFocus(boolean isFocus) {
        if (!mIsError) {
            this.mIsFocus = isFocus;
            mRimPaint.setColor(mIsFocus ? 0xff2493ee : mRimPaintColor);
            mContentPaint.setColor(mIsFocus ? 0x195aafee : mBackgroundPaintColor);
            invalidate();
        }
    }

    public void setError(boolean isError) {
        if (mIsFocus) {
            this.mIsError = isError;
            mRimPaint.setColor(isError ? 0xffff5a7f : 0xff2493ee);
            mContentPaint.setColor(isError ? 0x19d73055 : 0x195aafee);
            mFirstShadowPaint.setColor(isError ? 0xe2ff92aa : 0xe295ccf7);
            mSecondShadowPaint.setColor(isError ? 0x80bb4380 : 0x8043d3d0);
            invalidate();
        }
    }

    public boolean getIsError() {
        return mIsError;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsFocus) {
            canvas.drawRoundRect(mShadowRect, mRadiusSize, mRadiusSize, mFirstShadowPaint);
            canvas.drawRoundRect(mSecondShadowRect, mRadiusSize, mRadiusSize, mSecondShadowPaint);
        }
        canvas.drawRoundRect(mSecondShadowRect, mRadiusSize, mRadiusSize, mBackgroundPaint);
        canvas.drawRoundRect(mContentRect, mRadiusSize, mRadiusSize, mContentPaint);
        canvas.drawRoundRect(mRimRect, mRadiusSize, mRadiusSize, mRimPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        mShadowRect.set(mSecondShadowSize, mSecondShadowSize + mRadiusSize, mWidth - mSecondShadowSize, mHeight - mSecondShadowSize);
        mSecondShadowRect.set(mSecondShadowSize, mSecondShadowSize, mWidth - mSecondShadowSize, mHeight - mSecondShadowSize);
        mRimRect.set(mSecondShadowSize + mBackgroundPaintSize / 2, mSecondShadowSize + mBackgroundPaintSize / 2, mWidth - mSecondShadowSize - mBackgroundPaintSize / 2, mHeight - mSecondShadowSize - mBackgroundPaintSize / 2);
        mContentRect.set(mSecondShadowSize + mBackgroundPaintSize / 2 + mFirstShadowSize / 2, mSecondShadowSize + mBackgroundPaintSize / 2 + mFirstShadowSize / 2, mWidth - mSecondShadowSize - mBackgroundPaintSize / 2 - mFirstShadowSize / 2, mHeight - mSecondShadowSize - mBackgroundPaintSize / 2 - mFirstShadowSize / 2);
    }

}
