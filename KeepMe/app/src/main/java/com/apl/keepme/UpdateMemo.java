package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UpdateMemo extends AppCompatActivity {

DbHelper dbHelper;
SQLiteDatabase db;
String tableName, dbName;

EditText updateTitle, updateContent;
TextView updateTime;

String title, content, time;
int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_memo);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        dbName = "notepad.db";
        tableName = "noteData";

        updateTitle = findViewById(R.id.updateTitle);
        updateContent = findViewById(R.id.updateContent);
        updateTime = findViewById(R.id.updateTime);

        Intent intent = getIntent();

        //인텐트 정보 받기.
        title = intent.getExtras().getString("title");
        content = intent.getExtras().getString("content");
        time = intent.getExtras().getString("time");
        position = intent.getExtras().getInt("position");

        updateTitle.setText(title);
        updateContent.setText(content);
        updateTime.setText("마지막 수정 시간 : " + time);

        Button btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up_title = updateTitle.getText().toString();
                String up_content = updateContent.getText().toString();
                ContentValues contentValues = new ContentValues();

                //현재 시간 포맷 설정
                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String formatDate = sdfNow.format(mDate);

                contentValues.put("mTitle", up_title);
                contentValues.put("mContent", up_content);
                contentValues.put("mTime", formatDate);
                Toast.makeText(getApplicationContext(), "수정 완료!", Toast.LENGTH_SHORT).show();
                //업데이트
                db.update("myMemo", contentValues, "mNo=?", new String[] {String.valueOf(position)});
                finish();
                Intent intent1 = new Intent(UpdateMemo.this, MainActivity.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateMemo.this, ReadMemo.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("time", time);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();







    }
}

