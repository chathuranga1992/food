package com.uwu.ans.foodsafty.home;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_building.BuildingActivity;
import com.uwu.ans.foodsafty.settings.SettingsActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.new_record)
    public void onNewCheck(View view) {
        startActivity(new Intent(this, BuildingActivity.class));
    }
    @OnClick(R.id.reports)
    public void onReport(View view) {
        //startActivity(new Intent(this, BuildingActivity.class));
        Toast.makeText(this, "Reports Developing", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.settings)
    public void onTips(View view) {
        //startActivity(new Intent(this, BuildingActivity.class));
        Toast.makeText(this, "Tips Developing", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.tips)
    public void onSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
        //Toast.makeText(this, "Tips Clicked", Toast.LENGTH_SHORT).show();
    }
}
