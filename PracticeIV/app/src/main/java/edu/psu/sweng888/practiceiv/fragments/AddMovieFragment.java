package edu.psu.sweng888.practiceiv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.data.Movie;

public class AddMovieFragment extends Fragment implements View.OnClickListener{

    private EditText titleEditText;
    private EditText directorEditText;
    private EditText yearEditText;
    private EditText studioEditText;
    private Button confirmButton;
    private DatabaseReference mDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_movie, container, false);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // Get references to the EditText views
        titleEditText = rootView.findViewById(R.id.home_edit_title);
        directorEditText = rootView.findViewById(R.id.home_edit_director);
        yearEditText = rootView.findViewById(R.id.home_edit_year);
        studioEditText = rootView.findViewById(R.id.home_edit_studio);

        // Get references to the Button views
        confirmButton = rootView.findViewById(R.id.button_confirm);
        Button clearButton = rootView.findViewById(R.id.button_clear);

        confirmButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        return rootView;
    }
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button_confirm) {
            confirm();
        } else if(view.getId() == R.id.button_clear){
            clearFields();
        }
    }

    public void confirm() {
        String title = titleEditText.getText().toString().trim();
        String director = directorEditText.getText().toString().trim();
        String year = yearEditText.getText().toString().trim();
        String studio = studioEditText.getText().toString().trim();

        if (title.isEmpty() || director.isEmpty() || year.isEmpty() || studio.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            Movie newMovie = new Movie(title, director, year, studio);
            mDatabaseReference.child("movies").child(title).setValue(newMovie);

            Toast.makeText(getContext(), "New movie added", Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    public void clearFields(){
        titleEditText.setText("");
        directorEditText.setText("");
        yearEditText.setText("");
        studioEditText.setText("");
    }
}
