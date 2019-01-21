package com.uwu.ans.foodsafty.register;

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
import com.uwu.ans.foodsafty.login.SignInActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.text_input_edit_text_name_registration)
    TextInputEditText mTextInputEditTextNameRegister;
    @BindView(R.id.text_input_edit_text_email_registration)
    TextInputEditText mTextInputEditTextEmailRegister;
    @BindView(R.id.text_input_edit_text_password_registration)
    TextInputEditText mTextInputEditTextPasswordRegister;
    @BindView(R.id.text_input_edit_text_confirm_password_registration)
    TextInputEditText mTextInputEditTextConfirmPasswordRegister;

    @BindView(R.id.text_input_layout_name_registration)
    TextInputLayout mTextInputLayoutNameRegister;
    @BindView(R.id.text_input_layout_email_registration)
    TextInputLayout mTextInputLayoutEmailRegister;
    @BindView(R.id.text_input_layout_password_registration)
    TextInputLayout mTextInputLayoutPasswordRegister;
    @BindView(R.id.text_input_layout_confirm_password_registration)
    TextInputLayout mTextInputLayoutConfirmPasswordRegister;

    @BindView(R.id.register_progress)
    ProgressBar mProgressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void OnRegisterSuccess() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void validation() {
        boolean valid = true;
        String mName = mTextInputEditTextNameRegister.getText().toString().trim();
        String mEmail = mTextInputEditTextEmailRegister.getText().toString().trim();
        final String mPassword = mTextInputEditTextPasswordRegister.getText().toString().trim();
        final String mConfirmPassword = mTextInputEditTextConfirmPasswordRegister.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            mTextInputLayoutNameRegister.setError(getString(R.string.required));
            valid = false;
            return;
        }
        if (TextUtils.isEmpty(mEmail)) {
            mTextInputLayoutEmailRegister.setError(getString(R.string.required));
            valid = false;
            return;
        }
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())) {
            mTextInputLayoutEmailRegister.setError(getString(R.string.invalid_email));
            valid = false;
            return;
        }

        if (TextUtils.isEmpty(mPassword)) {
            mTextInputLayoutPasswordRegister.setError(getString(R.string.required));
            valid = false;
            return;
        }

        if (mPassword.length() < 6) {
            mTextInputLayoutPasswordRegister.setError(getString(R.string.too_short));
            valid = false;
            return;
        }
        if (!(mPassword.contentEquals(mConfirmPassword))) {
            mTextInputLayoutPasswordRegister.setError(getString(R.string.password_miss_match));
            mTextInputLayoutConfirmPasswordRegister.setError(getString(R.string.password_miss_match));
            valid = false;
            return;
        }
        if (valid) {
            mProgressBarRegister.setVisibility(VISIBLE);
            //create user
            mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            mProgressBarRegister.setVisibility(GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                OnRegisterSuccess();
                            }
                        }
                    });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBarRegister.setVisibility(GONE);
    }

    @OnClick(R.id.text_view_login_register_page)
    public void backToLoginRegisterScreen(View view) {
        OnRegisterSuccess();
    }

    @OnClick(R.id.button_activity_registration)
    public void onRegistrationRegisterScreen(View view) {
        validation();
    }
}
