package edu.psu.sweng888.practiceiv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.adapters.MovieAdapter;
import edu.psu.sweng888.practiceiv.data.Movie;
import edu.psu.sweng888.practiceiv.data.MovieDatabaseHelper;

public class MovieListFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;
    private DatabaseReference mFirebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("movies");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movie> movieList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Movie movie = dataSnapshot.getValue(Movie.class);
                    movieList.add(movie);
                }
                movieAdapter = new MovieAdapter(movieList);
                mRecyclerView.setAdapter(movieAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MovieListFragment", "Error retrieving movies from database",
                        error.toException());
            }
        });

        return view;
    }
}
