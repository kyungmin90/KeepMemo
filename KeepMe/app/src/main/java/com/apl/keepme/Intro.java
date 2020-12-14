package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Intro extends AppCompatActivity {
    byte timer;
    //실행시 0.5초 있다가 비밀번호 입력 액티비티로 이동
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        timer = 0;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timer < 5) {
                    timer++;
                    handler.postDelayed(this, 500);
                }
                else {
                    finish();
                    Intent intent = new Intent(Intro.this, Password.class);
                    startActivity(intent);
                }
            }
        }, 0);


    }
}
