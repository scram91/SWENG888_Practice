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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.data.Movie;

public class AddMovieFragment extends Fragment implements View.OnClickListener{

    private EditText titleEditText;
    private EditText directorEditText;
    private EditText yearEditText;
    private EditText studioEditText;
    private Button confirmButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    /** Corresponding collection */
    private CollectionReference mCollectionReference = db.collection("movies");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_movie, container, false);

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
        Map<String,Object> movieFirebase = new HashMap<>();
        /** Getting user inputs */

        String title = titleEditText.getText().toString();
        String director = directorEditText.getText().toString();
        String year = yearEditText.getText().toString();
        String studio = studioEditText.getText().toString();

        movieFirebase.put("title", title);
        movieFirebase.put("director", director);
        movieFirebase.put("year", year);
        movieFirebase.put("studio", studio);

        DocumentReference documentReference = mCollectionReference.document(title);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                /** If album is already in list, notify the user. Otherwise add the album. */
                if(documentSnapshot.exists()){
                    Toast.makeText(getContext(),"Album already exists in list",Toast.LENGTH_SHORT).show();
                }
                else{
                    documentReference.set(movieFirebase).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(),title+" was added successfully",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    public void clearFields(){
        titleEditText.setText("");
        directorEditText.setText("");
        yearEditText.setText("");
        studioEditText.setText("");
    }
}
