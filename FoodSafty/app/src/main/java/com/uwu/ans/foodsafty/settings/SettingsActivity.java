package com.uwu.ans.foodsafty.settings;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.home.HomeActivity;
import com.uwu.ans.foodsafty.login.SignInActivity;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @OnClick(R.id.button_back_setting)
    public void onBack(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @OnClick(R.id.button_logout_setting)
    public void onLogout(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}
