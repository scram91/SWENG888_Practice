package edu.psu.sweng888.practiceiv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.psu.sweng888.practiceiv.R;

public class CreateAccountActivity extends AppCompatActivity {
    EditText email_create, password_create, user_create;
    Button createButton;


    //Firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity);

        createButton = findViewById(R.id.create_account);
        password_create = findViewById(R.id.pass_word);
        email_create = findViewById(R.id.email_text);

        //Firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                //check if user is logged in or not
                if(currentUser!=null){
                    //user logged in

                }else{
                    //user is signed out

                }
            }
        };
        //Create Button Listener
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email_create.getText().toString())&& !TextUtils.isEmpty(password_create.getText().toString())){

                    String email = email_create.getText().toString().trim();
                    String pass = password_create.getText().toString().trim();

                    CreateUserEmailAccount(email,pass);
                }else{
                    Toast.makeText(CreateAccountActivity.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Method to create user account
    private void CreateUserEmailAccount(
            String email,
            String pass
    ){
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //user is created successfully
                        Toast.makeText(CreateAccountActivity.this, "user is created", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

}
