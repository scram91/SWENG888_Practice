package edu.psu.sweng888.practicev;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int DEFAULT_ZOOM = 15;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private final String GOOGLE_API_KEY = "AIzaSyC2vkifl8EXv316cJy5jprIDcYUnYdU1Gk";

    private GoogleMap mMap;
    private TextView textViewOption1, textViewOption2;
    private SearchView mSearchView;
    private FloatingActionButton mFloatingActionButton;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mSearchView = findViewById(R.id.idSearchView);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /** Instantiate the UI Elements */
        instantiateUIElements();
        /** Include a listener to the searchView */
        createSearchViewListener();


        mDrawerLayout = findViewById(R.id.nav_drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(MapsActivity.this);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, // Activity / Context
                mDrawerLayout, // DrawerLayout
                R.string.navigation_drawer_open, // String to open
                R.string.navigation_drawer_close // String to close
        );

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    //When an item is clicked the map will pan to that location and place a marker
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {// Handle navigation view item clicks here.
        Log.d(TAG, "Entered onNavigationItemSelected");
        int id = item.getItemId();
        LatLng genosLatLng = new LatLng(39.933811, -75.158867);
        String genosName = "Geno's Steaks";
        LatLng patsLatLng = new LatLng(39.933239, -75.159233);
        String patsName = "Pat's King of Steaks";
        LatLng dalLatLng = new LatLng(40.029480, -75.205994);
        String dalName = "Dalessandro's Steaks";
        LatLng chubLatLng = new LatLng(40.029140, -75.206280);
        String chubName = "Chubby's Steaks";

        if(id == R.id.genos_steaks){
            addMarker(genosName, genosLatLng);
        } else if(id == R.id.pats_steaks){
            addMarker(patsName, patsLatLng);
        } else if (id == R.id.del_steaks) {
            addMarker(dalName, dalLatLng);
        } else if (id == R.id.chubbys_steaks) {
            addMarker(chubName, chubLatLng);
        }

        //Close the navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.option_clear_map) {
            mMap.clear();
        } else if (id == R.id.option_back_to_center) {
            moveBackToCenter();
        }
    }

    private void createSearchViewListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /** Getting the location name from the searchView */
                String locationName = mSearchView.getQuery().toString();
                /** Create list of address where we will store the locations found **/
                List<Address> addressList = null;
                /** Check if the location is null */
                if (locationName != null || locationName.equals("")) {
                    /** Initializing the geocode */
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(locationName, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /** Getting the location in the first position */
                    Address address = addressList.get(0);
                    /** Creating the LatLng object to store the address coordinates */
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    /** Add a marker */
                    mMap.addMarker(new MarkerOptions().position(latLng).title(locationName));
                    /** Animate the camera */
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /** Since we are not using autocomplete, this method will not
                 * be implemented at this time */
                return false;
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng philadelphia = new LatLng(39.9526, -75.1652);
        String locationName = "Philadelphia";
        addMarker(locationName, philadelphia);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(philadelphia, 10));
    }

    private void addMarker (String location, LatLng coordinates){
        mMap.addMarker(new MarkerOptions().position(coordinates).title(location));
        //move the camera to a specific location, and set up the zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 10));

    }

   private void instantiateUIElements(){
        mFloatingActionButton = findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsMenu();
            }
        });

        textViewOption1 = findViewById(R.id.option_clear_map);
        textViewOption2 = findViewById(R.id.option_back_to_center);
       /**TODO
        * ADD Options for different locations in philly
        */

       textViewOption1.setOnClickListener(this);
        textViewOption2.setOnClickListener(this);

    }

    private void showOptionsMenu() {
        ConstraintLayout optionsMenu = findViewById(R.id.options_menu);
        if (optionsMenu.getVisibility() == View.VISIBLE) {
            /** Hide the options menu */
            optionsMenu.setVisibility(View.GONE);
        } else {
            /** Show the options menu */
            optionsMenu.setVisibility(View.VISIBLE);
        }
    }

    private void moveBackToCenter(){
        String locationName = "Philadelphia";
        // Represent location we need to use LatLng
        LatLng philadelphia = new LatLng(39.9526, -75.1652);
        addMarker(locationName, philadelphia);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(philadelphia, 10));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}