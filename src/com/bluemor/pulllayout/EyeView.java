package com.bluemor.pulllayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class EyeView extends FrameLayout {

    private Paint paint;
    private Bitmap bitmap;

    public EyeView(Context context) {
        super(context);
        init();
    }

    public EyeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        setDrawingCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (bitmap != null) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(bitmap, 0, 0, paint);
            paint.setXfermode(null);
        }
    }

    public void setRadius(int radius) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, paint);
        invalidate();
    }

}
