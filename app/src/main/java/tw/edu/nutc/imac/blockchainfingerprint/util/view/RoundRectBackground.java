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
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tw.edu.nutc.imac.blockchainfingerprint.R;

/**
 * Created by 依杰 on 2018/1/29.
 */

public class RoundRectBackground extends LinearLayout {
    private int mRadiusSize;
    private int mShadowSize;
    private RectF mBackgroundRect;
    private Paint mBackgroundPaint;
    private Paint mFirstShadowPaint;
    private Paint mSecondShadowPaint;

    public RoundRectBackground(Context context) {
        this(context, null);
        init();
    }

    public RoundRectBackground(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public RoundRectBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(attrs, defStyleAttr);
        init();
    }

    private void setAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.RoundRectBackground, defStyleAttr, 0);

        mRadiusSize = typedArray.getDimensionPixelSize(R.styleable.RoundRectBackground_radiusSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mRadiusSize, getResources().getDisplayMetrics()));

        mShadowSize = typedArray.getDimensionPixelSize(R.styleable.RoundRectBackground_shadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mShadowSize, getResources().getDisplayMetrics()));
        typedArray.recycle();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(0xffffffff);

        mFirstShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstShadowPaint.setStyle(Paint.Style.FILL);
        mFirstShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
        mFirstShadowPaint.setColor(0x80e2e2e2);

        mSecondShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondShadowPaint.setStyle(Paint.Style.FILL);
        mSecondShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
        mSecondShadowPaint.setColor(0x80e3e3e3);

        mBackgroundRect = new RectF();

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mBackgroundRect, mRadiusSize, mRadiusSize, mSecondShadowPaint);
        canvas.drawRoundRect(mBackgroundRect, mRadiusSize, mRadiusSize, mFirstShadowPaint);
        canvas.drawRoundRect(mBackgroundRect, mRadiusSize, mRadiusSize, mBackgroundPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int childCount = this.getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            totalHeight += child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
        setMeasuredDimension(mWidth + mShadowSize * 2, totalHeight + mShadowSize * 2);
        mBackgroundRect.set(mShadowSize, mShadowSize, mWidth-mShadowSize, totalHeight);
    }

}
