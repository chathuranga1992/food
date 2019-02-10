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

    String FoodProcessMarks, FoodProcessComments, FoodProcessCeilingSuitable, FoodProcessCeilingClean,
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
            BuildingSpaceUnsafeStructure, BuildingSpaceRemarks;


    int FinalGrade;
    AlertDialog dialog;

    DatabaseReference rootRef;

    double FoodPreparationPrecent =0;
    double LocationPrecent=0;
    double BuildingPrecent=0;
    double EqPrecent = 0;

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

        RestKey = getIntent().getStringExtra("RestName");;

        rootRef.child("Inspections").child(RestKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LocationPrecent = (Double) dataSnapshot.child("locationMarks").getValue();
                BuildingPrecent = (Double) dataSnapshot.child("buildingMarks").getValue();
                FoodPreparationPrecent = (Double) dataSnapshot.child("foodPreparationMarks").getValue();
                EqPrecent = (Double) dataSnapshot.child("equipmentUtencilsMarks").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        double Totalprecent = (LocationPrecent + BuildingPrecent + FoodPreparationPrecent+EqPrecent)/4;
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
            mTextViewResultText.setBackgroundColor(Color.rgb(255,165,0));
            mTextViewResultText.setText("You Are in Medium Risk");
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




/*setData();*/

    }


    @Optional
    @OnClick(R.id.btn_view_risk)
    public void setRecord(View view) {

        startActivity(new Intent(ResultActivity.this, HomeActivity.class));
    }
}
