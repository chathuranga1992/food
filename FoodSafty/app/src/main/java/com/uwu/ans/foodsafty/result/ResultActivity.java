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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        AlertDialog.Builder high = new AlertDialog.Builder(this);

        high.setMessage("Please Check for the Critical Factors ")
                .setTitle("You Have High Risk");

        dialog = high.create();
        mTextViewGrade.setTextColor(Color.YELLOW);
        mTextViewGrade.setText("C");
        //dialog.set
        dialog.show();

/*setData();*/

    }

    public void getData(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("building");


// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
/*                Post post = dataSnapshot.getValue(Post.class);
                System.out.println(post);*/
                BuildingModel buildingModel = dataSnapshot.getValue(BuildingModel.class);
                System.out.println(buildingModel);


                BuildingCommet = buildingModel.getComment();
                BuildingTotal = buildingModel.getTotalMarks();

                BuildingCeilingSuitable = buildingModel.getCeilingStructure().getSuitable();
                BuildingCeilingClean = buildingModel.getCeilingStructure().getClean();
                BuildingCeilinggetNoAccutilation = buildingModel.getCeilingStructure().getNoAccumilation();
                BuildingCeilinggetNoContamination = buildingModel.getCeilingStructure().getNoContamination();
                BuildingCeilinggetRemarks = buildingModel.getCeilingStructure().getmRemarks();

                BuildingFloorClean = buildingModel.getFloorStructure().getClean();
                BuildingFloorSuitable = buildingModel.getFloorStructure().getSuitable();
                BuildingFloorNoAccutilation = buildingModel.getFloorStructure().getNoAccumilation();
                BuildingloorgetNoContamination = buildingModel.getCeilingStructure().getNoContamination();
                BuildingloorgetRemarks = buildingModel.getCeilingStructure().getmRemarks();

                BuildingStructureAdequate = buildingModel.getStructure().getAdequate();
                BuildingStructureSuitable = buildingModel.getStructure().getSuitable();
                BuildingStructurePermanant = buildingModel.getStructure().getPermanant();
                BuildingStructureSafe = buildingModel.getStructure().getSafe();
                BuildingStructureRemarks = buildingModel.getStructure().getmRemarks();

                BuildingLnVAdequte = buildingModel.getLightNVentilation().getAdequate();
                BuildingLnVAppropriate = buildingModel.getLightNVentilation().getAppropriate();
                BuildingLnVRemarks = buildingModel.getLightNVentilation().getmRemarks();

                BuildingRiskFactorHazzards = buildingModel.getRiskFactors().getHazzards();
                BuildingRiskFactorUnsafeStructure = buildingModel.getRiskFactors().getUnsafeStructures();
                BuildingRiskFactorRemarks = buildingModel.getRiskFactors().getmRemarks();

                BuildingSpaceAdequate = buildingModel.getSpace().getAdequate();
                BuildingSpaceUnsafeStructure = buildingModel.getSpace().getAppropriate();
                BuildingSpaceRemarks = buildingModel.getSpace().getmRemarks();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: building: " + databaseError.getCode());
            }
        });

        DatabaseReference ref1 = database.getReference("food_preparation");
        //Query lastchield = mDatabaseFoodSafe.child("food_preparation").orderByKey().limitToLast(1);
        //FoodProcessingModel foodProcessingModel = dataSnapshot.getValue(FoodProcessingModel.class);
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FoodProcessingModel foodProcessingModel = dataSnapshot.getValue(FoodProcessingModel.class);
                System.out.println(foodProcessingModel);

                FoodProcessMarks = foodProcessingModel.getTotalMarks();
                FoodProcessComments = foodProcessingModel.getComment();

                FoodProcessCeilingSuitable = foodProcessingModel.getCeilingStructure().getSuitable();
                FoodProcessCeilingClean = foodProcessingModel.getCeilingStructure().getClean();
                FoodProcessCeilinggetNoAccutilation = foodProcessingModel.getCeilingStructure().getNoAccumilation();
                FoodProcessCeilinggetNoContamination = foodProcessingModel.getCeilingStructure().getNoContamination();
                FoodProcessCeilinggetRemarks = foodProcessingModel.getCeilingStructure().getRemark();

                FoodProcessFloorClean = foodProcessingModel.getFloorStructure().getClean();
                FoodProcessFloorSuitable = foodProcessingModel.getFloorStructure().getSuitable();
                FoodProcessFloorNoAccutilation = foodProcessingModel.getFloorStructure().getNoAccumilation();
                FoodProcessFloorgetNoContamination = foodProcessingModel.getCeilingStructure().getNoContamination();
                FoodProcessFloorgetRemarks = foodProcessingModel.getCeilingStructure().getRemark();

                FoodProcessWallSuitable = foodProcessingModel.getWallMaintatance().getSuitable();
                FoodProcessWallNoAccumilation = foodProcessingModel.getWallMaintatance().getNoContamination();
                FoodProcessWallNoContamination = foodProcessingModel.getWallMaintatance().getNoAccumilation();
                FoodProcessWallClean = foodProcessingModel.getWallMaintatance().getClean();
                FoodProcessWallRemarks = foodProcessingModel.getWallMaintatance().getRemark();

                FoodProcessContaminationGarbage = foodProcessingModel.getContamination().getFromGarbage();
                FoodProcessContaminationToilets = foodProcessingModel.getContamination().getFromToilets();
                FoodProcessContaminationSanitires = foodProcessingModel.getContamination().getFromSanitires();
                FoodProcessContaminationHazzards = foodProcessingModel.getContamination().getFromHazards();
                FoodProcessContaminationRemark = foodProcessingModel.getContamination().getRemark();

                FoodProcessSanitationAdequate = foodProcessingModel.getSanitation().getAppropriate();
                FoodProcessSanitationAppropriate = foodProcessingModel.getSanitation().getAdequate();
                FoodProcessSanitationRemark = foodProcessingModel.getSanitation().getRemark();

                FoodProcessLnVAdequte = foodProcessingModel.getLightNVentilation().getAdequate();
                FoodProcessLnVAppropriate = foodProcessingModel.getLightNVentilation().getAppropriate();
                FoodProcessLnVRemarks = foodProcessingModel.getLightNVentilation().getRemark();

                FoodProcessCleanlinesAdequate = foodProcessingModel.getCleanlines().getAppropriate();
                FoodProcessCleanlinesAppropriate = foodProcessingModel.getCleanlines().getAdequate();
                FoodProcessCleanlinesRemark = foodProcessingModel.getCleanlines().getRemark();

                FoodProcessHouseKeepingAdequte = foodProcessingModel.getHouseKeeping().getAdequate();
                FoodProcessHouseKeepingAppropriate = foodProcessingModel.getHouseKeeping().getAppropriate();
                FoodProcessHouseKeepingRemarks = foodProcessingModel.getHouseKeeping().getRemark();

                FoodProcessPestControlNoPests = foodProcessingModel.getPestControl().getNoPests();
                FoodProcessPestControlNoRodents = foodProcessingModel.getPestControl().getNoRodents();
                FoodProcessPestControlRemarks = foodProcessingModel.getPestControl().getRemark();

                FoodProcessCleaningAdequate = foodProcessingModel.getCleaning().getAdequate();
                FoodProcessCleaningAppropriate = foodProcessingModel.getCleaning().getAppropriate();
                FoodProcessCleaningDaily = foodProcessingModel.getCleaning().getDaily();
                FoodProcessCleaningNoAccumilation = foodProcessingModel.getCleaning().getNoAccumilation();
                FoodProcessCleaningRemarks = foodProcessingModel.getCleaning().getRemark();


                FoodProcessDrainageSuitable = foodProcessingModel.getDrainage().getSuitable();
                FoodProcessDrainageAdequate = foodProcessingModel.getDrainage().getAdequate();
                FoodProcessDrainageNoAccumilation = foodProcessingModel.getDrainage().getNoAccumulation();
                FoodProcessDrainageSafe = foodProcessingModel.getDrainage().getSafe();
                FoodProcessDrainageRemark = foodProcessingModel.getDrainage().getRemark();

                FoodProcessSpaceAdequate = foodProcessingModel.getSpace().getAdequate();
                FoodProcessSpaceApproriate = foodProcessingModel.getSpace().getAppropriate();
                FoodProcessSpaceRemarks = foodProcessingModel.getSpace().getRemark();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                System.out.println("The read failed:food_preparation:  " + databaseError.getCode());
            }
        });
    }

    public void setData() {
        int fin=40,x=40,y=50;
     //    x = Integer.parseInt(BuildingTotal);
      //   y = Integer.parseInt(FoodProcessMarks);
        if (x > y) {
            FinalGrade = y;
            fin = y;
        } else {
            FinalGrade = x;
            fin = y;
        }

        if (fin >10 && fin < 100) {
            mTextViewGrade.setTextColor(Color.YELLOW);
            mTextViewGrade.setText("C");
            dialog.show();

        }

    }

    @Optional
    @OnClick(R.id.btn_view_risk)
    public void setRecord(View view) {

        startActivity(new Intent(ResultActivity.this, HomeActivity.class));
    }
}
