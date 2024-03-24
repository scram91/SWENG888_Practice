package edu.psu.sweng888.practiceiv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.adapters.MovieAdapter;
import edu.psu.sweng888.practiceiv.data.Movie;

public class MovieListFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;
    private DatabaseReference mFirebaseDatabase;
    private Button mDeleteButton;
    private List<Movie> mSelectedMovies;
    private List<Movie> mMovieList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mCollectionReference = db.collection("movies");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("movies");
        mMovieList = new ArrayList<>();
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
        mDeleteButton = view.findViewById(R.id.deleteButton);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot albums : queryDocumentSnapshots) {
                    Movie movie = albums.toObject(Movie.class);
                    mMovieList.add(movie);
                }


                movieAdapter = new MovieAdapter(mMovieList);
                mRecyclerView.setAdapter(movieAdapter);

                movieAdapter.notifyDataSetChanged();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                /** Notify user if there is an error. */
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedMovies = movieAdapter.getSelectedItems();
                String collectionPath = "movies";

                /** For each selected item, delete the record from firestore and update the view. */
                for (Movie x : mSelectedMovies) {
                    db.collection(collectionPath).document(x.getTitle()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), x.getTitle().toString() + " was deleted successfully.", Toast.LENGTH_SHORT).show();

                            mMovieList.remove(x);
                            movieAdapter.notifyDataSetChanged();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error deleting Document.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}
