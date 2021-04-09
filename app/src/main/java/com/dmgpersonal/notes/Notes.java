package com.dmgpersonal.notes;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notes implements Parcelable {

    enum Status {
        New,        // Новая заметка
        Read,       // Прочитано
        Important,  // Важная TODO: статус Important как-то выделяется, например звездочкой в названии
        Reminder;   // Установлено напоминание TODO: придумать и реализовать механизм оповещения

        public int value() {
            return 1 << ordinal();
        }

        // Uses Status status = Status.New.value() | Status.Important.value()
        // if((status && Status.Important.value()) == Status.Important.value()) {true}
    }

    private String title;
    private String body;
    private Status status;
    private String modifyDate;
    private Color color;
    private final String date;
    private final Date now = new Date();

    public Notes(String title, String body) {
        this.title = title;
        this.body = body;
        this.status = Status.New;
        this.date = now.toString();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.modifyDate = now.toString();
    }

    public String getBody() {
        this.status = Status.Read; //TODO: доработать сохранение предыдущих статусов если таковые были установлены
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        this.modifyDate = now.toString();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    protected Notes(Parcel in) {
        title = in.readString();
        body = in.readString();
        date = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(date);
    }
}
