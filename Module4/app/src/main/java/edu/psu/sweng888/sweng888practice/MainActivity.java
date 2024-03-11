package edu.psu.sweng888.sweng888practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final String CHANNEL_ID = "";
    private static final int NOTIFICATION_ID = 0;
    private ListView mListView;
    private RecyclerView mRecyclerView;
    private MovieDatabaseHelper mMovieDatabaseHelper;
    private MovieAdapter mMovieAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mMovieDatabaseHelper = new MovieDatabaseHelper(this);

        this.deleteDatabase("movie_database");
        List<Movies> movies = mMovieDatabaseHelper.getAllMovies();
        if(movies.isEmpty()) {
            mMovieDatabaseHelper.populateMoviesDatabase();
            movies = mMovieDatabaseHelper.getAllMovies();
        }

        mMovieAdapter = new MovieAdapter(movies);
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecorator(0));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) //replace with a different icon
                .setContentTitle("Welcome Sr. Chuck Norris")
                .setContentText("The legend has arrived")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(this, TargetActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) != 
        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;
        public SpaceItemDecorator(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }

}