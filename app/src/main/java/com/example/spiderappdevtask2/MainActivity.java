package com.example.spiderappdevtask2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void level1(View view){
        GameView gameview = new GameView(this);
        setContentView(gameview);
        gameview.setNumColumns(8);
        gameview.setNumRows(8);
    }

    public void level2(View view){
        GameView2 gameview2 = new GameView2(this);
        setContentView(gameview2);
        gameview2.setNumColumns(8);
        gameview2.setNumRows(8);
    }
}
