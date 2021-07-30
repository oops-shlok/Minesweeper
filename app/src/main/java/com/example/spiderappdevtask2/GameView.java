package com.example.spiderappdevtask2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GameView extends View {
    Paint paintline = new Paint();
    Paint paint_1 = new Paint();
    Paint paint_2 = new Paint();
    Context context;
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private boolean[][] cellChecked;
    Dialog dialog;
    final Vibrator vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    int points=0;

    public GameView(Context context){
        super(context);
        this.context = context;
        paintline.setColor(Color.WHITE);
        paintline.setTextSize(50);
        paintline.setStrokeWidth(6);
        paint_1.setColor(Color.GREEN);
        paint_2.setColor(Color.RED);
        dialog = new Dialog(context);
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
        canvas.drawText("POINTS: "+points,400,1500,paintline);
        if (numColumns == 0 || numRows == 0) {
            return;
        }


        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 1; j < numRows-1; j++) {
                if (cellChecked[i][j]) {

                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            paint_1);
                }else if(cellChecked[0][8]){
                    canvas.drawRect(0,8*cellHeight,cellWidth,9*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();

                }else if(cellChecked[3][1]){
                    canvas.drawRect(3*cellWidth,1*cellHeight,4*cellWidth,2*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[7][2]){
                    canvas.drawRect(7*cellWidth,2*cellHeight,8*cellWidth,3*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[4][3]){
                    canvas.drawRect(4*cellWidth,3*cellHeight,5*cellWidth,4*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[5][3]){
                    canvas.drawRect(5*cellWidth,3*cellHeight,6*cellWidth,4*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[2][5]){
                    canvas.drawRect(2*cellWidth,5*cellHeight,3*cellWidth,6*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[6][5]){
                    canvas.drawRect(6*cellWidth,5*cellHeight,7*cellWidth,6*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[0][6]){
                    canvas.drawRect(0,6*cellHeight,cellWidth,7*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
                else if(cellChecked[2][7]){
                    canvas.drawRect(2*cellWidth,7*cellHeight,3*cellWidth,8*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }else if(cellChecked[4][7]){
                    canvas.drawRect(4*cellWidth,7*cellHeight,5*cellWidth,8*cellHeight,paint_2);
                    vibe.vibrate(100);
                    openDialog();
                }
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 165, i * cellWidth, height-170, paintline);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, paintline);
        }
    }
    private void openDialog() {
        dialog.setContentView(R.layout.activity_main1);
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
