package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.ViewMode;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    private Paint mPaint;
    private List<ViewMode> modeList;
    private String[] str = {"Froyo", "GB", "ICS", "JB", "Kitkat", "L", "M"};
    private float[] number = {10, 40, 50, 200, 320, 360, 300};
    private float sumNumber;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        modeList = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            ViewMode mode = new ViewMode();
            mode.name = str[i];
            mode.number = number[i];
            sumNumber += number[i];
            modeList.add(mode);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setColor(Color.parseColor("#7FFF00"));
        mPaint.setAntiAlias(true);
        float[] points = {50, 50, 50, 400, 50, 400, 700, 400};
        canvas.drawLines(points, mPaint);
        float width = (650 - 20 * (modeList.size() + 1)) / modeList.size();
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < modeList.size(); i++) {
            float height = modeList.get(i).number * 350 / sumNumber;
            float startx = 50 + 20;
            RectF rectF = new RectF(startx + (width + 20) * i, 400 - height, startx + width * (i + 1) + 20 * i, 400);
            canvas.drawRect(rectF, mPaint);
            canvas.drawText(modeList.get(i).name, startx + (width + 20) * i + width / 3, 410, mPaint);
        }
    }
}
