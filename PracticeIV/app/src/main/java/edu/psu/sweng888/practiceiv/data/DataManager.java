package edu.psu.sweng888.practiceiv.data;

import java.util.ArrayList;

public class DataManager {
    private static DataManager instance;
    private ArrayList<Movie> dataList;

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Movie> getDataList() {
        return dataList;
    }

    public void addData(Movie item) {
        dataList.add(item);
    }
}
