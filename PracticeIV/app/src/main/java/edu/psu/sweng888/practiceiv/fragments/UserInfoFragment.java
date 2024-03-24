package edu.psu.sweng888.practiceiv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.psu.sweng888.practiceiv.R;


public class UserInfoFragment extends Fragment {

    private TextView mTextViewEmail;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_fragment, container, false);
        mTextViewEmail = view.findViewById(R.id.user_email);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email;
        if(user != null){
            email = user.getEmail();

            mTextViewEmail.setText("Email:  " + email);
        }

        return view;
    }

}
