package com.example.spiderappdevtask2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.util.Random;

public class GameView2 extends View {
    Paint paintline = new Paint();
    Paint paint_1 = new Paint();
    Paint paint_2 = new Paint();
    Context context;
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private boolean[][] cellChecked;
    Dialog dialog;
    int k,l,p,q,r,s,t,u,v,w,y,z;
    final Vibrator vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    int points=0;
    float TEXT_SIZE = 60;
    Random random;

    public GameView2(Context context){
        super(context);
        this.context = context;
        paintline.setColor(Color.WHITE);
        paint_1.setColor(Color.GREEN);
        paint_2.setColor(Color.RED);
        dialog = new Dialog(context);
        k = randBetween(0,7);
        l = randBetween(0,7);
        p = randBetween(0,7);
        q = randBetween(0,7);
        r = randBetween(0,7);
        s = randBetween(0,7);
        t = randBetween(0,7);
        u = randBetween(0,7);
        v = randBetween(0,7);
        w = randBetween(0,7);
        y = randBetween(0,7);
        z = randBetween(0,7);

    }

    public static int randBetween(int start,int end){
        return start + (int) Math.round(Math.random()*(end-start));
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;
        cellChecked = new boolean[numColumns][numRows];
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawText("POINTS: "+points,950,TEXT_SIZE,paintline);
        if (numColumns == 0 || numRows == 0) {
            return;
        }


        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, paint_1);
                }
            }
        }
        if(cellChecked[k][l]){
            canvas.drawRect(k * cellWidth, l * cellHeight, (k + 1) * cellWidth, (l + 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }
        if(cellChecked[p][q]){
            canvas.drawRect(p * cellWidth, q * cellHeight, (p + 1) * cellWidth, (q + 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }
        if(cellChecked[r][s]){
            canvas.drawRect(r * cellWidth, s * cellHeight, (r + 1) * cellWidth, (s + 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }
        if(cellChecked[t][u]){
            canvas.drawRect(t * cellWidth, u * cellHeight, (t + 1) * cellWidth, (u + 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }
        if(cellChecked[v][w]){
            canvas.drawRect(v * cellWidth, w * cellHeight, (v + 1) * cellWidth, (w+ 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }
        if(cellChecked[y][z]){
            canvas.drawRect(y * cellWidth, z * cellHeight, (y + 1) * cellWidth, (z + 1) * cellHeight,paint_2);
            vibe.vibrate(100);
            openDialog();
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, paintline);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, paintline);
        }
    }
    private void openDialog() {
        dialog.setContentView(R.layout.activity_gameover_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button button=dialog.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
        dialog.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);
            points++;
            cellChecked[column][row] = !cellChecked[column][row];
            invalidate();
        }

        return true;
    }

}