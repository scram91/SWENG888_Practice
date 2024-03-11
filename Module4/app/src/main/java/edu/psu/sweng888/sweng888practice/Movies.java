package edu.psu.sweng888.sweng888practice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Movies implements Parcelable {
    private int year;
    private float rating;
    private String description;
    private String title;
    private String category;

    public Movies(String title, String category, int year, float rating, String description) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }

    protected Movies(Parcel in) {
        title = in.readString();
        category = in.readString();
        year = in.readInt();
        rating = in.readFloat();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
