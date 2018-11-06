package com.sensology.baseproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sensology.baseproject.R;

/**
 * Created by ${chenM} on 2018/11/5.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private Bitmap srcBitmap,dstBitmap;
    private Path path;


    private void init() {
        //初始化画笔
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        // 源图像
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.goods_ch2o, null);
        //目标图像
        dstBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //路径（贝塞尔曲线）
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //使用离屏绘制
        int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);

        //先将路径绘制到 bitmap上
        Canvas dstCanvas = new Canvas(dstBitmap);

        dstCanvas.drawLine(0,0,600,400,paint);

        //绘制 目标图像
        canvas.drawBitmap(dstBitmap, 100,100, paint);
        //设置 模式 为 SRC_OUT
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制源图像
        canvas.drawBitmap(srcBitmap, 100,100, paint);
        paint.setXfermode(null);

        canvas.restoreToCount(layerID);
    }

    private float eventX,eventY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventX = event.getX();
                eventY = event.getY();
                path.moveTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() - eventX) / 2 + eventX;
                float endY = (event.getY() - eventY) / 2 + eventY;
                path.quadTo(eventX, eventY, endX, endY);
                eventX = event.getX();
                eventY = event.getY();
                break;
        }
        invalidate();
        return true;
    }
}
