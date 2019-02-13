package com.uwu.ans.foodsafty.reports;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uwu.ans.foodsafty.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportDetailsActivity extends AppCompatActivity {

    @BindView(R.id.RestName)
    TextView RestName;
    @BindView(R.id.RestAddress)
    TextView RestAddress;
    @BindView(R.id.BRNo)
    TextView BRNo;
    @BindView(R.id.LANo)
    TextView LANo;
    @BindView(R.id.LAYear)
    TextView LAYear;
    @BindView(R.id.OwnerName)
    TextView OwnerName;
    @BindView(R.id.OwnerNic)
    TextView OwnerNic;

    @BindView(R.id.BuldMarks)
    TextView BuildMarks;
    @BindView(R.id.LocMarks)
    TextView LocMarks;
    @BindView(R.id.FpMarks)
    TextView FpMarks;
    @BindView(R.id.FstorageMarks)
    TextView FstorageMarks;
    @BindView(R.id.EqMarks)
    TextView EqMarks;

    @BindView(R.id.TotMarks)
    TextView TotalMarks;

    @BindView(R.id.report_grade)
    TextView FinalGradeText;
    @BindView(R.id.report_text)
    TextView mTextViewResultText;

    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_report_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String Selected = intent.getStringExtra("SelectedRep");

        rootRef = FirebaseDatabase.getInstance().getReference();

        Query query = rootRef.child("Inspections").orderByKey().equalTo(Selected);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        RestName.setText(ds.child("restaurant_name").getValue().toString());
                        RestAddress.setText(ds.child("private_address").getValue().toString());
                        BRNo.setText(ds.child("br_No").getValue().toString());
                        LANo.setText(ds.child("la_No").getValue().toString());
                        LAYear.setText(ds.child("la_Year").getValue().toString());
                        OwnerName.setText(ds.child("owner").getValue().toString());
                        OwnerNic.setText(ds.child("nic").getValue().toString());

                        double dBuildMarks = ds.child("buildingMarks").getValue(double.class);
                        dBuildMarks = Math.round(dBuildMarks * 100D) / 100D;
                        BuildMarks.setText(String.valueOf(dBuildMarks)+"%");

                        long dLocMarks = ds.child("locationMarks").getValue(long.class);
                        dLocMarks = Math.round(dLocMarks * 100L) / 100L;
                        LocMarks.setText(String.valueOf(dLocMarks)+"%");

                        long dFpMarks = ds.child("foodPreparationMarks").getValue(long.class);
                        dFpMarks = Math.round(dFpMarks * 100L) / 100L;
                        FpMarks.setText(String.valueOf(dFpMarks)+"%");

                        long dFstorageMarks = ds.child("foodStorageMarks").getValue(long.class);
                        dFstorageMarks = Math.round(dFstorageMarks * 100L) / 100L;
                        FstorageMarks.setText(String.valueOf(dFstorageMarks)+"%");

                        long dEqMarks = ds.child("equipmentUtencilsMarks").getValue(long.class);
                        dEqMarks = Math.round(dEqMarks * 100L) / 100L;
                        EqMarks.setText(String.valueOf(dEqMarks)+"%");

                        double tot = (dBuildMarks+dLocMarks+dFpMarks+dFstorageMarks+dEqMarks)/5;
                        tot = Math.round(tot * 100D) / 100D;
                        TotalMarks.setText(String.valueOf(tot)+"%");

                        String finalGrade;

                        if(tot >= 75){
                            finalGrade = "A";
                            mTextViewResultText.setBackgroundColor(Color.YELLOW);
                            mTextViewResultText.setText("You Are in Low Risk");
                        }
                        else if(tot >= 65){
                            finalGrade = "B";
                            mTextViewResultText.setBackgroundColor(Color.YELLOW);
                            mTextViewResultText.setText("You Are in Low Risk");
                        }
                        else if(tot >=50){
                            finalGrade = "C";
                            mTextViewResultText.setBackgroundColor(Color.rgb(255,165,0));
                            mTextViewResultText.setText("You Are in Medium Risk");
                        }
                        else if(tot >=35){
                            finalGrade = "S";
                            mTextViewResultText.setBackgroundColor(Color.RED);
                            mTextViewResultText.setText("You Are in High Risk");
                        }
                        else{
                            finalGrade = "F";
                            mTextViewResultText.setBackgroundColor(Color.RED);
                            mTextViewResultText.setText("You Are in High Risk");
                        }

                        FinalGradeText.setText(finalGrade);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
