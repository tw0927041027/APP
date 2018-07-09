package tw.edu.nutc.imac.blockchainfingerprint.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import tw.edu.nutc.imac.blockchainfingerprint.R;

public class TitleBarView extends View {
    private Paint mWhitePaint;
    private Paint mUndertintPaint;
    private PointF mWhiteStartPoint, mWhiteEndPoint, mWhiteControlPoint;
    private Path mWhitePath = new Path();
    private int mShadowSize;
    private int mWhiteHeight;

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(attrs, defStyleAttr);
        init();
    }

    private void setAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBarView, defStyleAttr, 0);

        mShadowSize = typedArray.getDimensionPixelSize(R.styleable.TitleBarView_shadowSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mShadowSize, getResources().getDisplayMetrics()));
        typedArray.recycle();
    }

    private void init() {
        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setStyle(Paint.Style.FILL);
        mWhitePaint.setColor(0xff2493ee);

        mWhiteStartPoint = new PointF(0, 0);
        mWhiteEndPoint = new PointF(0, 0);
        mWhiteControlPoint = new PointF(0, 0);

        mUndertintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUndertintPaint.setStyle(Paint.Style.FILL);
        mUndertintPaint.setMaskFilter(new BlurMaskFilter(mShadowSize, BlurMaskFilter.Blur.OUTER));
        mUndertintPaint.setColor(0xff5b5b5b);

        mWhiteHeight = (int) getContext().getResources().getDimension(R.dimen.bar_margin);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int mWidth, int mHeight, int oldw, int oldh) {
        super.onSizeChanged(mWidth, mHeight, oldw, oldh);

        mWhitePath.reset();

        mWhitePath.moveTo(0, 0);

        mWhitePath.addRect(0, 0, mWidth, mHeight - mWhiteHeight, Path.Direction.CCW);

        mWhiteStartPoint.x = 0;
        mWhiteStartPoint.y = mHeight - mWhiteHeight;

        mWhiteEndPoint.x = mWidth;
        mWhiteEndPoint.y = mHeight - mWhiteHeight;

        mWhiteControlPoint.x = mWidth / 2;
        mWhiteControlPoint.y = mHeight+30 ;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWhitePath.moveTo(mWhiteStartPoint.x, mWhiteStartPoint.y);
        mWhitePath.quadTo(mWhiteControlPoint.x, mWhiteControlPoint.y, mWhiteEndPoint.x, mWhiteEndPoint.y);
        canvas.drawPath(mWhitePath, mUndertintPaint);

        mWhitePath.moveTo(mWhiteStartPoint.x, mWhiteStartPoint.y);
        mWhitePath.quadTo(mWhiteControlPoint.x, mWhiteControlPoint.y, mWhiteEndPoint.x, mWhiteEndPoint.y);
        canvas.drawPath(mWhitePath, mWhitePaint);
    }
}