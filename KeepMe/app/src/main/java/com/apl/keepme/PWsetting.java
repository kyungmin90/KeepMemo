package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PWsetting extends AppCompatActivity {

    EditText pwSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwsetting);

        pwSetting = findViewById(R.id.pwsetting);

        //pwSetting 포커스 주고 입력자판 올림
        pwSetting.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        //취소버튼
        Button btnC = findViewById(R.id.btnCancel);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "비밀번호 변경 취소", Toast.LENGTH_SHORT).show();
                //입력자판 내리기
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                finish();
            }
        });

        //확인버튼
        Button btnO = findViewById(R.id.btnOk);
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setPW = pwSetting.getText().toString();
                //입력 글자가 4개보다 작으면 토스트 후 리턴
                if (setPW.length() < 4) {
                    Toast.makeText(getApplicationContext(), "비밀번호 4자리를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //SharedPreferences 사용하여 비밀번호 Password의 pw를 바꿈
                SharedPreferences sp = getSharedPreferences("Password", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("pw", setPW);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"비밀번호 변경 성공!",Toast.LENGTH_SHORT).show();

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    finish();
            }
        });


    }

}
