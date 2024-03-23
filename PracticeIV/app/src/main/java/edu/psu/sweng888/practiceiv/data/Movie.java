package edu.psu.sweng888.practiceiv.data;

import java.io.Serializable;

import edu.psu.sweng888.practiceiv.R;

public class Movie implements Serializable {

    private String title;
    private String director;
    private String year;
    private String studio;
    private int imageResourceId;
    public Movie() {
    }
    public Movie(String title, String director, String year, String studio) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.studio = studio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getImageResourceId() {
        return R.drawable.ic_book;
    }
}
