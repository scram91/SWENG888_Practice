package edu.psu.sweng888.practiceiv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    EditText emailEt, passEt;
  //  private FirebaseAuth mFirebaseAuth;
    Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

      //  mFirebaseAuth = FirebaseAuth.getInstance();
        logIn = findViewById(R.id.login_button);
        emailEt = findViewById(R.id.email_edittext);
        passEt = findViewById(R.id.password_edittext);
    }

   /* private void SignIn(String email, String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this,
                                    ItemListActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
  //  }
}
