package edu.psu.sweng888.practiceiv.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.psu.sweng888.practiceiv.R;


public class LoginActivity extends AppCompatActivity {
    EditText emailEt, passEt;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    Button logIn, createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mFirebaseAuth = FirebaseAuth.getInstance();
        logIn = findViewById(R.id.login_button);
        createAccount = findViewById(R.id.create_account_button);
        emailEt = findViewById(R.id.email_edittext);
        passEt = findViewById(R.id.password_edittext);

            // Listener for create account button
        createAccount.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
                                                 startActivity(i);
                                             }
                                         });
        //listener for login button
        logIn.setOnClickListener(v -> {

                loginUser(
                        emailEt.getText().toString().trim(),
                        passEt.getText().toString().trim()
                );
            });
    }
     private void loginUser(String email, String pwd) {
        // Checking for empty texts
        if (!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(pwd)
        ) {
            mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    //Onsuccessful login send user to the "home page"
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                }
            });
        }
    }
}
