package com.uwu.ans.foodsafty.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.home.HomeActivity;
import com.uwu.ans.foodsafty.register.RegisterActivity;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.text_input_edit_text_email_login)
    TextInputEditText mTextInputEditTextEmail;
    @BindView(R.id.text_input_edit_text_password_login)
    TextInputEditText mTextInputEditTextPassword;

    @BindView(R.id.text_input_layout_email_login)
    TextInputLayout mTextInputLayoutEmail;
    @BindView(R.id.text_input_layout_password_login)
    TextInputLayout mTextInputLayoutPassword;

    @BindView(R.id.login_progress)
    ProgressBar mProgressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            OnSuccessLogin();
        }

    }

/*    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/



    private void validate() {

        boolean valid = true;
        String mEmail = mTextInputEditTextEmail.getText().toString().trim();
        final String mPassword = mTextInputEditTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mEmail)) {
            mTextInputLayoutEmail.setError(getString(R.string.required));
            valid = false;
            return;
        }
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())) {
            mTextInputLayoutEmail.setError(getString(R.string.invalid_email));
            valid = false;
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            mTextInputLayoutPassword.setError(getString(R.string.required));
            valid = false;
            return;
        }

        if (mPassword.length() < 6) {
            mTextInputLayoutPassword.setError(getString(R.string.too_short));
            valid = false;
            return;
        }
        if (valid) {
            mProgressBarLogin.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            mProgressBarLogin.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
                                if (mPassword.length() < 6) {
                                    mTextInputLayoutPassword.setError(getString(R.string.too_short));
                                } else {
                                    Toast.makeText(SignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                OnSuccessLogin();
                            }
                        }
                    });
        }


    }

    private void OnSuccessLogin() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @OnClick(R.id.text_view_register_login_page)
    public void onRegisterLoginPage(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.button_activity_login)
    public void onLoginActivity(View view) {
        validate();
    }}
