package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class ReadMemo extends AppCompatActivity {
    TextView readTitle,readContent, readTime;
    String title,content, time;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_memo);

        readTitle = findViewById(R.id.readTitle);
        readContent = findViewById(R.id.readContent);
        readTime = findViewById(R.id.readTime);

        Intent intent = getIntent();
       //인텐트 정보를 받음.
        title = intent.getExtras().getString("title");
        content = intent.getExtras().getString("content");
        time = intent.getExtras().getString("time");
        position = intent.getExtras().getInt("position");

        readTitle.setText(title);
        readContent.setText(content);
        readTime.setText(time);


    }

    //뒤로 가기 버튼 클릭시 메모보는 액티비티 닫고, 메인 액티비티로 가기
    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ReadMemo.this,MainActivity.class);
        startActivity(intent);
    }


    // 화면 터치시 터치 이벤트 실행
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        //ACTION_DOWN(=화면을 누름) 일 경우, 수정 액티비티로 이동
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(getApplicationContext(), UpdateMemo.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("time", time);
            intent.putExtra("position", position);
            startActivity(intent);
            finish();
        }
        return super.dispatchTouchEvent(motionEvent);
    }


}
