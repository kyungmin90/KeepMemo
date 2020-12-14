package com.apl.keepme;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    ArrayList<MemoList> items = new ArrayList<MemoList>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_memo_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemoList item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MemoList item) {
        items.add(item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView adapter_title;
        TextView adapter_content;
        TextView adapter_time;

        public ViewHolder(View itemView) {
            super(itemView);

            adapter_title = itemView.findViewById(R.id.adapter_title);
            adapter_content = itemView.findViewById(R.id.adapter_content);
            adapter_time = itemView.findViewById(R.id.adapter_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DbHelper dbHelper;
                    SQLiteDatabase database;
                    Context context = view.getContext();

                    dbHelper = new DbHelper(view.getContext());
                    database = dbHelper.getWritableDatabase();

                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Log.d("tag", position + "클릭");
                        Cursor cursor = database.rawQuery("select mNO, mTitle, mContent, mTime from myMemo order by mTime DESC", null);
                        cursor.move(position+1);
                        Intent intent = new Intent(view.getContext(), ReadMemo.class);
                        intent.putExtra("title",cursor.getString(1));
                        intent.putExtra("content",cursor.getString(2));
                        intent.putExtra("time", cursor.getString(3));
                        intent.putExtra("position", cursor.getInt(0));
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        cursor.close();
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    DbHelper dbHelper;
                    final SQLiteDatabase database;
                    Context context = view.getContext();

                    dbHelper = new DbHelper(view.getContext());
                    database = dbHelper.getWritableDatabase();

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setMessage("삭제하시겠습니까?");
                    alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position = getAdapterPosition();
//                            확인 버튼
                            Cursor cursor = database.rawQuery("select mNo, mTitle, mContent from myMemo order by mTime DESC", null);
                            cursor.move(position+1);
                            Toast.makeText(view.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            database.delete("myMemo", "mNo=?", new String[] {String.valueOf(cursor.getInt(0))});
                            cursor.close();
                            ((MainActivity)view.getContext()).executeQueryNotSpace();
                        }
                    });
                    alertDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            취소 버튼
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();

                    return false;
                }
            });
        }

        public void setItem(MemoList item) {
            adapter_title.setText(item.getTitle());
            adapter_content.setText(item.getContent());
            adapter_time.setText(item.getTime());
        }
    }
}