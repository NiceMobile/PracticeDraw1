package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.ViewMode;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private Paint mPaint;
    private List<ViewMode> modeList;
    private String[] str = {"Lollipop", "Marshmallow", "Froyo", "Gine", "Kitkat", "Horen"};
    private float[] number = {120, 40, 20, 30, 65, 85};
    private String[] color = {"#00FFFF", "#32CD32", "#556B2F", "#FFD700", "#D2691E", "#FF0000"};
    private float sumNumber;
    private float mMax;

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
            mode.color = color[i];
            sumNumber += number[i];
            modeList.add(mode);
        }
        for (float numbers : number) {
            if (mMax < numbers) {
                mMax = numbers;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        mPaint.setColor(Color.parseColor("#7FFF00"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        float startAngle = -180;
        float lineAngle = 0;//当前扇形的半角度
        for (int i = 0; i < modeList.size(); i++) {
            float percent = modeList.get(i).number / sumNumber;
            int angle = (int) (percent * 360);
            RectF rectF = new RectF(-200, -200, 200, 200);
            RectF rectFMax = new RectF(-210, -210, 190, 190);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor(modeList.get(i).color));
            if (modeList.get(i).number == mMax) {
                canvas.drawArc(rectFMax, startAngle, angle, true, mPaint);
            } else {
                canvas.drawArc(rectF, startAngle, angle, true, mPaint);
            }
            lineAngle = startAngle + angle / 2;
            //Math.cos(X):X代表是弧度，公式：2*PI/360*角度 = 弧度
            float startX = (float) (Math.cos(lineAngle * Math.PI / 180) * 200);
            float startY = (float) (Math.sin(lineAngle * Math.PI / 180) * 200);
            float endX = (float) (Math.cos(lineAngle * Math.PI / 180) * (200 + 50));
            float endY = (float) (Math.sin(lineAngle * Math.PI / 180) * (200 + 50));
            Log.d("wangbin", "lineAngle " + lineAngle);
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.STROKE);
            if (modeList.get(i).number == mMax) {
                startX -= 10;
                startY -= 10;
                endX -= 10;
                endY -= 10;
            }
            canvas.drawLine(startX, startY, endX, endY, mPaint);
            canvas.drawLine(endX, endY, endX < 0 ? endX - 50 : endX + 50, endY, mPaint);
            canvas.drawText(modeList.get(i).name, endX < 0 ? endX - 60 : endX + 60, endY, mPaint);
            startAngle = startAngle + angle;
        }
    }
}
