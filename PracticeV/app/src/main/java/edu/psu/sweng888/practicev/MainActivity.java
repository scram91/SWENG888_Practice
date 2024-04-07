package edu.psu.sweng888.practicev;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MapsActivity.class.getSimpleName();
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom Navigation Bar
        mBottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        mBottomNavigationView
                .setOnNavigationItemSelectedListener(this);
    }

    //Similar to Navigation Drawer
    //on an item selection got to that specific activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.information){
            Intent i = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(i);
        } else if(id == R.id.go_to_map){
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }

        return false;
    }
}