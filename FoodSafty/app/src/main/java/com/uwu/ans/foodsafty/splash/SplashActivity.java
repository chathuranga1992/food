package com.uwu.ans.foodsafty.splash;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.login.SignInActivity;
import com.uwu.ans.foodsafty.register.RegisterActivity;
import com.uwu.ans.foodsafty.util.UIUtils;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.iv_animate_chef)
    ImageView mIVChef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        Glide.with(this)
                .load(UIUtils.getDrawable(R.drawable.chef_main))
                .into(mIVChef);
    }

    @OnClick(R.id.button_activity_splash)
    public void goRegisterSplash(View view){
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.text_view_login_splash_screen)
    public void goToLoginSplash(View view){
        startActivity(new Intent(this,SignInActivity.class));
        finish();
    }
}
