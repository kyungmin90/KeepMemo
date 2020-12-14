package com.apl.keepme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends AppCompatActivity {
    byte len = 0;
    String input_password = "";
    Button pwinfo;
    EditText password_1;
    EditText password_2;
    EditText password_3;
    EditText password_4;

    String pw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        // i 버튼 클릭시 초기 비밀번호 알려줌
        pwinfo = findViewById(R.id.pwinfo);
        pwinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"초기 비밀번호는 0000 입니다!",Toast.LENGTH_SHORT).show();
            }
        });

        password_1 = findViewById(R.id.password_1);
        password_2 = findViewById(R.id.password_2);
        password_3 = findViewById(R.id.password_3);
        password_4 = findViewById(R.id.password_4);
        password_1.setTextColor(Color.parseColor("#94B2BD"));
        password_2.setTextColor(Color.parseColor("#94B2BD"));
        password_3.setTextColor(Color.parseColor("#94B2BD"));
       password_4.setTextColor(Color.parseColor("#94B2BD"));

        // SharedPreferences 를 사용해 비밀번호를 저장
        // 저장된 값이 없을경우 0000으로 가져옴.
        SharedPreferences sp = getSharedPreferences("Password", MODE_PRIVATE);
        pw = sp.getString("pw", "0000");

    }

    public void onClickBtnPassword(View view) {
        switch (view.getId()) {
            // 각 숫자 버튼에 대한 조건
            case R.id.btn_0:
                if(len == 0) {
                    len++;
                    input_password = "0";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "0";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "0";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "0";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_1:
                if(len == 0) {
                    len++;
                    input_password = "1";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "1";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "1";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "1";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_2:
                if(len == 0) {
                    len++;
                    input_password = "2";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "2";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "2";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "2";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_3:
                if(len == 0) {
                    len++;
                    input_password = "3";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "3";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "3";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "3";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_4:
                if(len == 0) {
                    len++;
                    input_password = "4";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "4";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "4";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "4";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_5:
                if(len == 0) {
                    len++;
                    input_password = "5";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "5";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "5";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "5";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_6:
                if(len == 0) {
                    len++;
                    input_password = "6";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "6";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "6";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "6";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_7:
                if(len == 0) {
                    len++;
                    input_password = "7";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "7";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "7";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "7";
                    password_4.setText("●");
                }
                break;
            case R.id.btn_8:
                if(len == 0) {
                    len++;
                    input_password = "8";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "8";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "8";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "8";
                   password_4.setText("●");
                }
                break;
            case R.id.btn_9:
                if(len == 0) {
                    len++;
                    input_password = "9";
                    password_1.setText("●");
                }
                else if(len == 1) {
                    len++;
                    input_password += "9";
                    password_2.setText("●");
                }
                else if(len == 2) {
                    len++;
                    input_password += "9";
                    password_3.setText("●");
                }
                else if(len == 3) {
                    len++;
                    input_password += "9";
                    password_4.setText("●");
                }
                break;
            case R.id.imgbtn_back:
                //지우기 버튼을 눌렀을때 작동함.
                if(len == 1) {
                    len--;
                    input_password = "";
                    password_1.setText("");
                }
                else if(len == 2) {
                    len--;
                    // input_password를 input_password의 0부터 1까지만 남김
                    input_password = input_password.substring(0, 1);
                    password_2.setText("");
                }
                else if(len == 3) {
                    len--;
                    input_password = input_password.substring(0, 2);
                    password_3.setText("");
                }
                break;
        }
        if(len == 4) {
            if (input_password.equals(pw)) {
                Intent intent = new Intent(Password.this, MainActivity.class);
                startActivity(intent);
                finish();
                print("비밀번호 확인 완료!");
            }
            else {
                print("비밀번호가 틀렸습니다. 다시 입력해주세요.");
                len = 0;
                input_password = "";
                password_1.setText("");
                password_2.setText("");
                password_3.setText("");
                password_4.setText("");
            }
        }
    }
    //Toast 프린트
    public void print(String result){
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
    }
}

