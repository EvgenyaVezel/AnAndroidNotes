package com.example.myapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;



public class Note implements Parcelable {


    private String  head;
    private Date date;
    private String  content;


    public Note(String head, Date date, String content) {
        this.head = head;
        this.date = date;
        this.content = content;

    }



    protected Note(Parcel in) {
        head = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getHead() {
        return head;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setHead(String head) {
        this.head = head;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeString(content);
    }
}
