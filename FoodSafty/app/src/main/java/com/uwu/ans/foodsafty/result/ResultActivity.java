package com.uwu.ans.foodsafty.result;

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
import com.google.firebase.database.ValueEventListener;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.home.HomeActivity;
import com.uwu.ans.foodsafty.new_record_building.domains.BuildingModel;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.FoodProcessingModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class ResultActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.result_grade)
    TextView mTextViewGrade;

    @BindView(R.id.result_improve)
    TextView mTextViewImprove;

    @BindView(R.id.result_text)
    TextView mTextViewResultText;

    /*String FoodProcessMarks, FoodProcessComments, FoodProcessCeilingSuitable, FoodProcessCeilingClean,
            FoodProcessCeilinggetNoAccutilation, FoodProcessCeilinggetNoContamination, FoodProcessCeilinggetRemarks,
            FoodProcessFloorClean, FoodProcessFloorSuitable, FoodProcessFloorNoAccutilation,
            FoodProcessFloorgetNoContamination, FoodProcessFloorgetRemarks, FoodProcessWallSuitable,
            FoodProcessWallNoAccumilation, FoodProcessWallNoContamination, FoodProcessWallClean,
            FoodProcessWallRemarks, FoodProcessContaminationGarbage, FoodProcessContaminationToilets,
            FoodProcessContaminationSanitires, FoodProcessContaminationHazzards, FoodProcessContaminationRemark,
            FoodProcessSanitationAdequate, FoodProcessSanitationAppropriate, FoodProcessSanitationRemark,
            FoodProcessLnVAdequte, FoodProcessLnVAppropriate, FoodProcessLnVRemarks, FoodProcessCleanlinesAdequate,
            FoodProcessCleanlinesAppropriate, FoodProcessCleanlinesRemark, FoodProcessHouseKeepingAdequte,
            FoodProcessHouseKeepingAppropriate, FoodProcessHouseKeepingRemarks, FoodProcessPestControlNoPests,
            FoodProcessPestControlNoRodents, FoodProcessPestControlRemarks, FoodProcessCleaningAdequate,
            FoodProcessCleaningAppropriate, FoodProcessCleaningDaily, FoodProcessCleaningNoAccumilation,
            FoodProcessCleaningRemarks, FoodProcessDrainageSuitable, FoodProcessDrainageAdequate,
            FoodProcessDrainageNoAccumilation, FoodProcessDrainageSafe, FoodProcessDrainageRemark,
            FoodProcessSpaceAdequate, FoodProcessSpaceApproriate, FoodProcessSpaceRemarks, BuildingCommet, BuildingTotal;
    String BuildingCeilingSuitable, BuildingCeilingClean, BuildingCeilinggetNoAccutilation,
            BuildingCeilinggetNoContamination, BuildingCeilinggetRemarks, BuildingFloorClean,
            BuildingFloorSuitable, BuildingFloorNoAccutilation, BuildingloorgetNoContamination,
            BuildingloorgetRemarks, BuildingStructureAdequate, BuildingStructureSuitable,
            BuildingStructurePermanant, BuildingStructureSafe, BuildingStructureRemarks,
            BuildingLnVAdequte, BuildingLnVAppropriate, BuildingLnVRemarks, BuildingRiskFactorHazzards,
            BuildingRiskFactorUnsafeStructure, BuildingRiskFactorRemarks, BuildingSpaceAdequate,
            BuildingSpaceUnsafeStructure, BuildingSpaceRemarks;*/


    int FinalGrade;
    AlertDialog dialog;

    DatabaseReference rootRef;

    long FoodPreparationPrecent =0L,LocationPrecent=0L,BuildingPrecent=0L,EqPrecent = 0L, FsPrecent = 0L,
            FoodHandlerPrecent = 0L, packagingMaterial = 0L, recordKeeping = 0L, SanitationActivity = 0L,
            StanderdsActivity = 0L, WasteMAnagement = 0L, WaterSupply = 0L;

    String RestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        rootRef = FirebaseDatabase.getInstance().getReference();

        RestKey = getIntent().getStringExtra("RestName");

        rootRef.child("Inspections").child(RestKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LocationPrecent =  dataSnapshot.child("locationMarks").getValue(long.class);
                BuildingPrecent =  dataSnapshot.child("buildingMarks").getValue(long.class);
                FoodPreparationPrecent = dataSnapshot.child("foodPreparationMarks").getValue(long.class);
                EqPrecent = dataSnapshot.child("equipmentUtensilsMarks").getValue(long.class);
                FsPrecent = dataSnapshot.child("foodStorageMarks").getValue(long.class);

                FoodHandlerPrecent = dataSnapshot.child("foodHandlerMarks").getValue(long.class);
                packagingMaterial = dataSnapshot.child("packagingMaterialMarks").getValue(long.class);
                recordKeeping = dataSnapshot.child("recordKeepingMarks").getValue(long.class);
                SanitationActivity = dataSnapshot.child("sanitationMarks").getValue(long.class);
                StanderdsActivity = dataSnapshot.child("standardsMarks").getValue(long.class);
                WasteMAnagement = dataSnapshot.child("wasteManagementMarks").getValue(long.class);
                WaterSupply = dataSnapshot.child("waterMarks").getValue(long.class);

                double Totalprecent = (( LocationPrecent + BuildingPrecent + FoodPreparationPrecent
                        + EqPrecent + FsPrecent + FoodHandlerPrecent + packagingMaterial +
                        recordKeeping + SanitationActivity + StanderdsActivity + WasteMAnagement +
                        WaterSupply) / 12);
                String finalGrade;

                if(Totalprecent >= 75){
                    finalGrade = "A";
                    mTextViewResultText.setBackgroundColor(Color.YELLOW);
                    mTextViewResultText.setText("You Are in Low Risk");
                    mTextViewImprove.setVisibility(View.GONE);
                }
                else if(Totalprecent >= 65){
                    finalGrade = "B";
                    mTextViewResultText.setBackgroundColor(Color.YELLOW);
                    mTextViewResultText.setText("You Are in Low Risk");
                    mTextViewImprove.setVisibility(View.GONE);
                }
                else if(Totalprecent >=50){
                    finalGrade = "C";
                    mTextViewResultText.setBackgroundColor(Color.rgb(255,165,0));
                    mTextViewResultText.setText("You Are in Medium Risk");
                    mTextViewImprove.setVisibility(View.VISIBLE);
                    mTextViewImprove.setText("Please Improve");
                }
                else if(Totalprecent >=35){
                    finalGrade = "S";
                    mTextViewResultText.setBackgroundColor(Color.RED);
                    mTextViewResultText.setText("You Are in High Risk");
                    mTextViewImprove.setVisibility(View.VISIBLE);
                    mTextViewImprove.setText("Please Improve");
                }
                else{
                    finalGrade = "F";
                    mTextViewResultText.setBackgroundColor(Color.RED);
                    mTextViewResultText.setText("You Are in High Risk");
                    mTextViewImprove.setVisibility(View.VISIBLE);
                    mTextViewImprove.setText("Please Improve");
                }

                mTextViewGrade.setText(finalGrade);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*setData();*/

    }


    @Optional
    @OnClick(R.id.btn_view_risk)
    public void setRecord(View view) {

        startActivity(new Intent(ResultActivity.this, HomeActivity.class));
    }
}
///*
// E/AndroidRuntime: FATAL EXCEPTION: main
//    Process: com.uwu.ans.foodsafty, PID: 25661
//    java.lang.ClassCastException: java.lang.Double cannot be cast to java.lang.Long
//        at com.uwu.ans.foodsafty.result.ResultActivity$1.onDataChange(ResultActivity.java:99)
//        at com.google.firebase.database.zzp.onDataChange(Unknown Source:7)
//        at com.google.android.gms.internal.firebase_database.zzfc.zza(Unknown Source:13)
//        at com.google.android.gms.internal.firebase_database.zzgx.zzdr(Unknown Source:2)
//        at com.google.android.gms.internal.firebase_database.zzhd.run(Unknown Source:71)
//        at android.os.Handler.handleCallback(Handler.java:907)
//        at android.os.Handler.dispatchMessage(Handler.java:105)
//        at android.os.Looper.loop(Looper.java:216)
//        at android.app.ActivityThread.main(ActivityThread.java:7593)
//        at java.lang.reflect.Method.invoke(Native Method)
//        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:524)
//        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:987)*/