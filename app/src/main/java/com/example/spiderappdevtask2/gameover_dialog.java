package com.example.spiderappdevtask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class gameover_dialog extends AppCompatActivity {
    TextView textView3;
    TextView textView4;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
    }
}