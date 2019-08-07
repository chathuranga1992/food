package com.uwu.ans.foodsafty.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.LocationEnvironmentActivity;
import com.uwu.ans.foodsafty.new_record_storage.StorageActivity;
import com.uwu.ans.foodsafty.reports.ReportActivity;
import com.uwu.ans.foodsafty.settings.SettingsActivity;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout rootLayout;
    DatabaseReference rootRef;
    public ProgressDialog dialogm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);
        rootRef = FirebaseDatabase.getInstance().getReference();

        dialogm = new ProgressDialog(this); // this = YourActivity
        dialogm.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialogm.setMessage("Loading. Please wait...");
        dialogm.setIndeterminate(true);
        dialogm.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.new_record)
    public void onNewCheck(View view) {
        shownewInspection();
        //startActivity(new Intent(this,StorageActivity.class));
    }

    private void shownewInspection() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("New Record");
            dialog.setMessage("Please Enter Restaurant Details");
            LayoutInflater inflater = LayoutInflater.from(this);
            View resigter_layout = inflater.inflate(R.layout.layout_new_record,null);

            final MaterialEditText edtOwnerName = resigter_layout.findViewById(R.id.edtOwnerName);
            final MaterialEditText edtPrivateAddress = resigter_layout.findViewById(R.id.edtPrivateAddress);
            final MaterialEditText edtNIC = resigter_layout.findViewById(R.id.edtNIC);
            final MaterialEditText edtRestName = resigter_layout.findViewById(R.id.edtRestName);
            final MaterialEditText edtBRNo = resigter_layout.findViewById(R.id.edtBRNo);
            final MaterialEditText edtLANo = resigter_layout.findViewById(R.id.edtLANo);
            final MaterialEditText edtLAYear = resigter_layout.findViewById(R.id.edtLAYear);

            dialog.setView(resigter_layout);

            dialog.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();

                    if(TextUtils.isEmpty(edtOwnerName.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Owner Name",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtPrivateAddress.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Private Address",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtNIC.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Owner NIC",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtRestName.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Restaurant Name",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtBRNo.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Business Registration Number",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtLANo.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Local Authority Number",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if(TextUtils.isEmpty(edtLAYear.getText().toString()))
                    {
                        Snackbar.make(rootLayout,"Please Enter Local Authority Year",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        String RKey = edtRestName.getText().toString()+ random();
                        final String RestKey = RKey.replaceAll("\\s","");

                        NewRecord newRecord = new NewRecord();

                        newRecord.setOwner(edtOwnerName.getText().toString());
                        newRecord.setPrivate_address(edtPrivateAddress.getText().toString());
                        newRecord.setNic(edtNIC.getText().toString());
                        newRecord.setRestaurant_name(edtRestName.getText().toString());
                        newRecord.setBR_No(edtBRNo.getText().toString());
                        newRecord.setLA_No(edtLANo.getText().toString());
                        newRecord.setLA_Year(edtLAYear.getText().toString());


                        dialogm.setTitle("Proceeding");
                        dialogm.show();
                        Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String key = RestKey;
                                rootRef.child("Inspections").child(key).setValue(newRecord).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(rootLayout,"Restaurant Registered Successfully !!",Snackbar.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(),LocationEnvironmentActivity.class);
                                        intent.putExtra("RestName",key);
                                        startActivity(intent);
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout,"Error Proceeding Data !!",Snackbar.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        },3000);

                    }
                }
            });

            dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            });

            dialog.show();
    }

    public static String random() {
        String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

            final Random random=new Random();
            final StringBuilder sb=new StringBuilder(8);
            for(int i=0;i<8;++i)
                sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
            return sb.toString();
    }

    @OnClick(R.id.reports)
    public void onReport(View view) {
        startActivity(new Intent(this, ReportActivity.class));
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
