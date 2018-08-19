package com.example.rathana.recyclerviewdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable{
    private int id;
    private  String title;
    private String description;
    private String view;
    private int thumb;
    private String channel;

    protected Film(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        view = in.readString();
        thumb = in.readInt();
        channel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(view);
        dest.writeInt(thumb);
        dest.writeString(channel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Film(String title, String view, int thumb, String channel) {
        this.title = title;
        this.view = view;
        this.thumb = thumb;
        this.channel = channel;
    }

    public Film(){}
    public Film(int id, String title, String description, String view) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", view='" + view + '\'' +
                ", thumb=" + thumb +
                ", channel='" + channel + '\'' +
                '}';
    }
}
