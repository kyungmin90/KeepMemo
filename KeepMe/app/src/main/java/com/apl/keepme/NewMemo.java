package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMemo extends AppCompatActivity {
    DbHelper dbHelper;
    EditText edtTitle, edtContent;
    SQLiteDatabase db;
    String tableName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);

        tableName = "myMemo";
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);

        // 내용 입력 에디트 텍스트에 포커스를 할당하고
        // 입력 키패드가 보이게 함
        edtContent.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        Button button = findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            //어댑터에 아이템 추가해서 제목 내용 가져옴
            @Override
            public void onClick(View view) {
                MemoAdapter adapter = new MemoAdapter();
                adapter.addItem(new MemoList(edtTitle.getText().toString(), edtContent.getText().toString(),null));
                String input_title = edtTitle.getText().toString();
                String input_content = edtContent.getText().toString();

                //입력된 내용이 없으면 저장 안됨
                try { ContentValues contentValues = new ContentValues();

                    if(TextUtils.isEmpty(input_title) && TextUtils.isEmpty(input_content)){
                        Toast.makeText(NewMemo.this, "저장 안됨.", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(NewMemo.this, MainActivity.class);
                        startActivity(intent);
                        return;
                    }
                    //시간 포맷 설정해서 DB 저장
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String formatDate = sdfNow.format(mDate);

                    contentValues.put("mTitle", input_title);
                    contentValues.put("mContent", input_content);
                    contentValues.put("mTime", formatDate);

                    Toast.makeText(NewMemo.this, "저장 완료!", Toast.LENGTH_SHORT).show();
                    //insert
                    db.insert(tableName, null, contentValues);
                }catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                finish();
                Intent intent = new Intent(NewMemo.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    //뒤로 가기 버튼 클릭시 새 메모 닫고, 메인액티비티로 가기
    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(NewMemo.this,MainActivity.class);
        startActivity(intent);
    }
}
