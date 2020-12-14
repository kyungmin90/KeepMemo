package com.apl.keepme;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    SQLiteDatabase db;
    String tableName;
    String dbName;
    Intent toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbName = "keepme.db";
        tableName = "myMemo";

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createDatabase(); // 데이터베이스 생성
        createTable(tableName); // 테이블 생성
        executeQuery(); // 리싸이클러뷰 어댑터와 아이템 설정

        //플로팅 버튼 클릭시 메모 작성 액티비티
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewMemo.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //xml의 내용이 툴바에 반영
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu) ;
        return true ;
    }
    //툴바의 액션 및 메뉴 클릭 이벤트 처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_password:
                toolbar = new Intent(MainActivity.this, PWsetting.class);
                startActivity(toolbar);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //DB 생성 쓰기 가능한 상태로 설정
    private void createDatabase() {
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    //메모 넣을 테이블 생성 번호, 제목, 내용, 작성시간
    private void createTable(String name) {
        db.execSQL("create table if not exists " + name + "("
                + " mNo integer PRIMARY KEY autoincrement, "
                + " mTitle text, "
                + " mContent text, "
                + " mTime datetime)");
    }
    // 작성 시간으로 내림 차순
    public void executeQuery() {
        Cursor cursor = db.rawQuery("select mNo, mTitle, mContent, mTime from myMemo order by mTime DESC", null);
        int recordCount = cursor.getCount();
        MemoAdapter adapter = new MemoAdapter();
        //어댑터 설정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // 리싸이클뷰러 설정
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);
            adapter.addItem(new MemoList(title, content,time));
        //어댑터에 아이템 추가
        }
        //리싸이클뷰러 간격 설정
        RecyclerDeco spaceDecoration = new RecyclerDeco(20);
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setAdapter(adapter);
        cursor.close();
    }

    public void executeQueryNotSpace() {
        Cursor cursor = db.rawQuery("select mNo, mTitle, mContent, mTime from myMemo order by mTime DESC", null);

        int recordCount = cursor.getCount();
        MemoAdapter adapter = new MemoAdapter();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);
            adapter.addItem(new MemoList(title, content,time)); //어댑터에 아이템 추가(제목, 내용, 시간)
        }
        //리싸이클러뷰에 어댑터 끼우기
        recyclerView.setAdapter(adapter);

        cursor.close();
    }
}









