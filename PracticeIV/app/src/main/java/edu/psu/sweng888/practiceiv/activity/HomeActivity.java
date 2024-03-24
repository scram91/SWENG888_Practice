package edu.psu.sweng888.practiceiv.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.fragments.AddMovieFragment;
import edu.psu.sweng888.practiceiv.fragments.MovieListFragment;
import edu.psu.sweng888.practiceiv.fragments.HomeFragment;
import edu.psu.sweng888.practiceiv.fragments.UserInfoFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private FirebaseAuth mFirebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        mDrawerLayout = findViewById(R.id.nav_drawer_layout);

        mNavigationView = findViewById(R.id.nav_view);
        //Set the listener for the NavigationView
        mNavigationView.setNavigationItemSelectedListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, // Activity / Context
                mDrawerLayout, // DrawerLayout
                R.string.navigation_drawer_open, // String to open
                R.string.navigation_drawer_close // String to close
        );

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        //Setting the default fragment to the HomeFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    //Attaching the corresponding fragment with the navigation menu item. Inflate the fragment upon navigation item select.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {// Handle navigation view item clicks here.
        Log.d(TAG, "Entered onNavigationItemSelected");
        int id = item.getItemId();

        if(id == R.id.nav_add_movie){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddMovieFragment()).commit();
        } else if(id == R.id.nav_movie_list){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieListFragment()).commit();
        } else if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_user_info) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserInfoFragment()).commit();
        } else if (id == R.id.nav_log_out) {
            signOut();
        }

        //Close the navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer_items, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

        //Method for user signOut
    private void signOut(){
        mFirebaseAuth.signOut();
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
