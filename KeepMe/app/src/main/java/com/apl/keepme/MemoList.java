package com.apl.keepme;

public class MemoList {
    String title;
    String content;
    String time;
public MemoList(String title, String content, String time){
    this.title = title;
    this.content = content;
    this.time = time;
}
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime(){ return  time; }

}
