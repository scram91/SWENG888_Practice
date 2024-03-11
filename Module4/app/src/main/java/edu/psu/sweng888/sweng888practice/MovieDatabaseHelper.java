package edu.psu.sweng888.sweng888practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movie_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIES = "movies";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_YEAR = "year";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DESCRIPTION = "description";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "(" +
                KEY_ID + "INTEGER PRIMARY KEY," +
                KEY_TITLE + "TEXT," +
                KEY_CATEGORY + "TEXT," +
                KEY_YEAR + "INTEGER," +
                KEY_RATING + "REAL," +
                KEY_DESCRIPTION + "TEXT" + ")";

        db.execSQL(QUERY_CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void populateMoviesDatabase() {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, "The Avengers");
        values.put(KEY_CATEGORY, "action");
        values.put(KEY_YEAR, 2012);
        values.put(KEY_RATING, 4.8);
        values.put(KEY_DESCRIPTION, "MARVEL");
        database.insert(TABLE_MOVIES, null, values);

        values = new ContentValues();
        values.put(KEY_TITLE, "Blade Runner 2049");
        values.put(KEY_CATEGORY, "science fiction");
        values.put(KEY_YEAR, 2017);
        values.put(KEY_RATING, 3.8);
        values.put(KEY_DESCRIPTION, "Warner Bros");
        database.insert(TABLE_MOVIES, null, values);
    }

    public List<Movies> getAllMovies() {
        List<Movies> moviesList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MOVIES;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Movies movies = new Movies(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getFloat(4),
                        cursor.getString(5)
                );
                moviesList.add(movies);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return moviesList;
    }
}
