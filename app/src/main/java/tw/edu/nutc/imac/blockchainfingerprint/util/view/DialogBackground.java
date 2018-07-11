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
 * Created by 依杰 on 2018/3/13.
 */

public class DialogBackground extends LinearLayout {
    private int marginStartY;
    private int marginStartX;
    private int mShadowSize;
    private int mRadiusSize;
    private Paint mBackgroundPaint;
    private Paint mShadowPaint;
    private RectF mShadowRect;

    public DialogBackground(Context context) {
        this(context, null);
        init();
    }

    public DialogBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttr(attrs);
        init();
    }

    private void setAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DialogBackground);

        marginStartY = typedArray.getDimensionPixelSize(R.styleable.DialogBackground_marginStartY, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginStartY, getResources().getDisplayMetrics()));

        marginStartX = typedArray.getDimensionPixelSize(R.styleable.DialogBackground_marginStartX, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginStartX, getResources().getDisplayMetrics()));

        mShadowSize = typedArray.getDimensionPixelSize(R.styleable.DialogBackground_shadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mShadowSize, getResources().getDisplayMetrics()));

        mRadiusSize = typedArray.getDimensionPixelSize(R.styleable.DialogBackground_radiusSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mRadiusSize, getResources().getDisplayMetrics()));

        typedArray.recycle();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(0xffffffff);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setColor(0x38000000);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));

        mShadowRect = new RectF();

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mShadowRect, mRadiusSize, mRadiusSize, mShadowPaint);
        canvas.drawRoundRect(mShadowRect, mRadiusSize, mRadiusSize, mBackgroundPaint);
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
        setMeasuredDimension(mWidth, totalHeight);
        mShadowRect.set(mShadowSize + marginStartX, mShadowSize + marginStartY, mWidth - mShadowSize, totalHeight - mShadowSize);
    }
}
