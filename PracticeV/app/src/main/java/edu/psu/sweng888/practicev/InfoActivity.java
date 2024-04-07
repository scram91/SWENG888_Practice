package edu.psu.sweng888.practicev;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        mBottomNavigationView
                = findViewById(R.id.info_bottom_navigation_view);

        mBottomNavigationView
                .setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.home){
            Intent i = new Intent(InfoActivity.this, MainActivity.class);
            startActivity(i);
        } else if(id == R.id.info_go_to_map){
            Intent intent = new Intent(InfoActivity.this, MapsActivity.class);
            startActivity(intent);
        }

        return false;
    }
}
