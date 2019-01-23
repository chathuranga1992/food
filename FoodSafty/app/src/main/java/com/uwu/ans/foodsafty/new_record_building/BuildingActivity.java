package com.uwu.ans.foodsafty.new_record_building;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_building.domains.BuildingModel;
import com.uwu.ans.foodsafty.new_record_building.domains.CeilingStructure;
import com.uwu.ans.foodsafty.new_record_building.domains.FloorStructure;
import com.uwu.ans.foodsafty.new_record_building.domains.LightNVentilation;
import com.uwu.ans.foodsafty.new_record_building.domains.RiskFactors;
import com.uwu.ans.foodsafty.new_record_building.domains.Space;
import com.uwu.ans.foodsafty.new_record_building.domains.Structure;
import com.uwu.ans.foodsafty.new_record_building.domains.WallMaintatance;
import com.uwu.ans.foodsafty.new_record_food_preperation.FoodPreperationActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuildingActivity extends AppCompatActivity {

    /*building suitability*/
    @BindView(R.id.marks_building_suitability)
    TextView mTextViewMarksBuildingStructure;
    @BindView(R.id.building_suitability_permanent)
    CheckBox mCheckBoxBuildingStructurePermenant;
    @BindView(R.id.building_suitability_adequate)
    CheckBox mCheckBoxBuildingStructureAdequate;
    @BindView(R.id.building_suitability_suitable)
    CheckBox mCheckBoxBuildingStructureSuitable;
    @BindView(R.id.building_suitability_safe)
    CheckBox mCheckBoxBuildingStructureSafe;

    /*lights and ventilation*/
    @BindView(R.id.marks_building_light_amp_ventilation)
    TextView mTextViewMarksBuildingLightandVentilation;
    @BindView(R.id.building_light_amp_ventilation_attractive)
    CheckBox mCheckBoxBuildingLightandVentilationAttractive;
    @BindView(R.id.building_light_amp_ventilation_beautiful)
    CheckBox mCheckBoxBuildingLightandVentilationBeautiful;

    /*wall maintenance*/
    @BindView(R.id.marks_building_wall_maintenance)
    TextView mTextViewMarksBuildingWallMaintanance;
    @BindView(R.id.building_wall_maintenance_suitable)
    CheckBox mCheckBoxBuildingWallMaintananceSuitable;
    @BindView(R.id.building_wall_maintenance_no_contamination)
    CheckBox mCheckBoxBuildingWallMaintananceNoContamination;
    @BindView(R.id.building_wall_maintenance_no_accumulation)
    CheckBox mCheckBoxBuildingWallMaintananceNoAccumilation;
    @BindView(R.id.building_wall_maintenance_clean)
    CheckBox mCheckBoxBuildingWallMaintananceClean;

    /*risk factors*/
    @BindView(R.id.marks_building_risk_factors)
    TextView mTextViewMarksBuildingRiskFactors;
    @BindView(R.id.building_risk_factors_hazards)
    CheckBox mCheckBoxBuildingRiskFactorsHazzards;
    @BindView(R.id.building_risk_factors_unsafe_structures)
    CheckBox mCheckBoxBuildingRiskFactorsUnsafeStructures;

    /*floor Structure*/
    @BindView(R.id.marks_building_floor_structure)
    TextView mTextViewMarksBuildingFloorStructure;
    @BindView(R.id.building_floor_structure_suitable)
    CheckBox mCheckBoxBuildingFloorStructureSuitable;
    @BindView(R.id.building_floor_structure_no_contamination)
    CheckBox mCheckBoxBuildingFloorStructureNoContamination;
    @BindView(R.id.building_floor_structure_no_accumulation)
    CheckBox mCheckBoxBuildingFloorStructureNoAccumilation;
    @BindView(R.id.building_floor_structure_clean)
    CheckBox mCheckBoxBuildingFloorStructureClean;

    /*Ceiling Structure*/
    @BindView(R.id.marks_building_ceiling_structure)
    TextView mTextViewMarksBuildingCeilingStructure;
    @BindView(R.id.building_ceiling_structuree_suitable)
    CheckBox mCheckBoxBuildingCeilingStructureSuitable;
    @BindView(R.id.building_ceiling_structure_no_contamination)
    CheckBox mCheckBoxBuildingCeilingStructureNoContamination;
    @BindView(R.id.building_ceiling_structure_no_accumulation)
    CheckBox mCheckBoxBuildingCeilingStructureNoAccumilation;
    @BindView(R.id.building_ceiling_structure_clean)
    CheckBox mCheckBoxBuildingCeilingStructureClean;

    /*risk factors*/
    @BindView(R.id.marks_building_space)
    TextView mTextViewMarksBuildingSpace;
    @BindView(R.id.building_space_adequate)
    CheckBox mCheckBoxBuildingSpaceAdequate;
    @BindView(R.id.building_space_appropriate)
    CheckBox mCheckBoxBuildingSpaceAppropriate;

    @BindView(R.id.building_comments)
    EditText mEditTextComments;

    public DatabaseReference mDatabaseFoodSafe;

    private int mBuildingStructureMarksINT = 0;
    private int mBuildingLightAndVentilationMarksINT = 0;
    private int mBuildingWallmaintananceMarksINT = 0;
    private int mBuildingFloorStructureMarksINT = 0;
    private int mBuildingRiskFactorsMarksINT = 0;
    private int mBuildingCeilingStructureMarksINT = 0;
    private int mBuildingSpaceMarksINT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_building);

        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference("building");


    }

    public void setMarksFour(int marks, TextView mTextView) {
        String mStringMarks = Integer.valueOf(marks).toString();
        mTextView.setText(mStringMarks);
        setMarkViewFour(mTextView, mStringMarks);
    }

    public void setMarksTwo(int marks, TextView mTextView) {
        marks = marks + 1;
        String mStringMarks = Integer.valueOf(marks).toString();
        mTextView.setText(mStringMarks);
        setMarkViewTwo(mTextView, mStringMarks);
    }

    public void setMarkViewFour(TextView mTextView, String mBuildingSuitabilityMarks) {
        if (mBuildingSuitabilityMarks.equals("4")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.good));
        } else if (mBuildingSuitabilityMarks.equals("3")) {
            mTextView.setTextColor(Color.BLUE);
            mTextView.setText(getString(R.string.adequate));
        } else if (mBuildingSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        } else {
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }

    public void setMarkViewTwo(TextView mTextView, String mBuildingSuitabilityMarks) {

        if (mBuildingSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.adequate));
        } else if(mBuildingSuitabilityMarks.equals("1")){
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        }else{
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }


    @OnClick(R.id.building_next)
    public void setBuilding(View view) {

        String mBuildingStructureMarks1, mBuildingStructureMarks2, mBuildingStructureMarks3, mBuildingStructureMarks4;
        String mBuildingWallMaintanance1, mBuildingWallMaintanance2, mBuildingWallMaintanance3, mBuildingWallMaintanance4;
        String mBuildingLightAndVentilationMarks1, mBuildingLightAndVentilationMarks2;
        String mBuildingRiskFactorsMarks1, mBuildingRiskFactorsMarks2;
        String mBuildingFoorStructureMarks1, mBuildingFoorStructureMarks2, mBuildingFoorStructureMarks3, mBuildingFoorStructureMarks4;
        String mBuildingSpaceMarks1, mBuildingSpaceMarks2;
        String mBuildinCeilingStructureMarks1, mBuildingCeilingStructureMarks2, mBuildingCeilingStructureMarks3, mBuildingCeilingStructureMarks4;

        String building_id = mDatabaseFoodSafe.push().getKey();

        /*Structure*/
        if (mCheckBoxBuildingStructureAdequate.isChecked()) {
            mBuildingStructureMarksINT = mBuildingStructureMarksINT + 1;
            setMarksFour(mBuildingStructureMarksINT, mTextViewMarksBuildingStructure);

            mBuildingStructureMarks1 = "1";
        } else {
            mBuildingStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingStructureSuitable.isChecked()) {
            mBuildingStructureMarksINT = mBuildingStructureMarksINT + 1;
            setMarksFour(mBuildingStructureMarksINT, mTextViewMarksBuildingStructure);
            mBuildingStructureMarks2 = "1";
        } else {
            mBuildingStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingStructurePermenant.isChecked()) {
            mBuildingStructureMarksINT = mBuildingStructureMarksINT + 1;
            setMarksFour(mBuildingStructureMarksINT, mTextViewMarksBuildingStructure);
            mBuildingStructureMarks3 = "1";
        } else {
            mBuildingStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingStructureSafe.isChecked()) {
            mBuildingStructureMarksINT = mBuildingStructureMarksINT + 1;
            setMarksFour(mBuildingStructureMarksINT, mTextViewMarksBuildingStructure);
            mBuildingStructureMarks4 = "1";
        } else {
            mBuildingStructureMarks4 = "0";
        }

        /*Light and ventilation*/
        if (mCheckBoxBuildingLightandVentilationAttractive.isChecked()) {
            mBuildingLightAndVentilationMarksINT = mBuildingLightAndVentilationMarksINT + 1;
            setMarksTwo(mBuildingLightAndVentilationMarksINT, mTextViewMarksBuildingLightandVentilation);
            mBuildingLightAndVentilationMarks1 = "1";
        } else {
            mBuildingLightAndVentilationMarks1 = "0";
        }
        if (mCheckBoxBuildingLightandVentilationBeautiful.isChecked()) {
            mBuildingLightAndVentilationMarksINT = mBuildingLightAndVentilationMarksINT + 1;
            setMarksTwo(mBuildingLightAndVentilationMarksINT, mTextViewMarksBuildingLightandVentilation);
            mBuildingLightAndVentilationMarks2 = "1";
        } else {
            mBuildingLightAndVentilationMarks2 = "0";
        }

        /*Risk Factors*/
        if (mCheckBoxBuildingRiskFactorsHazzards.isChecked()) {
            mBuildingRiskFactorsMarksINT = mBuildingRiskFactorsMarksINT + 1;
            setMarksTwo(mBuildingRiskFactorsMarksINT, mTextViewMarksBuildingRiskFactors);
            mBuildingRiskFactorsMarks1 = "1";
        } else {
            mBuildingRiskFactorsMarks1 = "0";
        }
        if (mCheckBoxBuildingRiskFactorsUnsafeStructures.isChecked()) {
            mBuildingRiskFactorsMarksINT = mBuildingRiskFactorsMarksINT + 1;
            setMarksTwo(mBuildingRiskFactorsMarksINT, mTextViewMarksBuildingRiskFactors);
            mBuildingRiskFactorsMarks2 = "1";
        } else {
            mBuildingRiskFactorsMarks2 = "0";
        }

        /*Wall maintenance*/
        if (mCheckBoxBuildingWallMaintananceClean.isChecked()) {
            mBuildingWallmaintananceMarksINT = mBuildingWallmaintananceMarksINT + 1;
            setMarksFour(mBuildingWallmaintananceMarksINT, mTextViewMarksBuildingWallMaintanance);
            mBuildingWallMaintanance1 = "1";
        } else {
            mBuildingWallMaintanance1 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceSuitable.isChecked()) {
            mBuildingWallmaintananceMarksINT = mBuildingWallmaintananceMarksINT + 1;
            setMarksFour(mBuildingWallmaintananceMarksINT, mTextViewMarksBuildingWallMaintanance);
            mBuildingWallMaintanance2 = "1";
        } else {
            mBuildingWallMaintanance2 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceNoContamination.isChecked()) {
            mBuildingWallmaintananceMarksINT = mBuildingWallmaintananceMarksINT + 1;
            setMarksFour(mBuildingWallmaintananceMarksINT, mTextViewMarksBuildingWallMaintanance);
            mBuildingWallMaintanance3 = "1";
        } else {
            mBuildingWallMaintanance3 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceNoAccumilation.isChecked()) {
            mBuildingWallmaintananceMarksINT = mBuildingWallmaintananceMarksINT + 1;
            setMarksFour(mBuildingWallmaintananceMarksINT, mTextViewMarksBuildingWallMaintanance);
            mBuildingWallMaintanance4 = "1";
        } else {
            mBuildingWallMaintanance4 = "0";
        }


        /*Floor Structure*/
        if (mCheckBoxBuildingFloorStructureClean.isChecked()) {
            mBuildingFloorStructureMarksINT = mBuildingFloorStructureMarksINT + 1;
            setMarksFour(mBuildingFloorStructureMarksINT, mTextViewMarksBuildingFloorStructure);
            mBuildingFoorStructureMarks1 = "1";
        } else {
            mBuildingFoorStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingFloorStructureNoAccumilation.isChecked()) {
            mBuildingFloorStructureMarksINT = mBuildingFloorStructureMarksINT + 1;
            setMarksFour(mBuildingFloorStructureMarksINT, mTextViewMarksBuildingFloorStructure);
            mBuildingFoorStructureMarks2 = "1";
        } else {
            mBuildingFoorStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingFloorStructureNoContamination.isChecked()) {
            mBuildingFloorStructureMarksINT = mBuildingFloorStructureMarksINT + 1;
            setMarksFour(mBuildingFloorStructureMarksINT, mTextViewMarksBuildingFloorStructure);
            mBuildingFoorStructureMarks3 = "1";
        } else {
            mBuildingFoorStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingFloorStructureSuitable.isChecked()) {
            mBuildingFloorStructureMarksINT = mBuildingFloorStructureMarksINT + 1;
            setMarksFour(mBuildingFloorStructureMarksINT, mTextViewMarksBuildingFloorStructure);
            mBuildingFoorStructureMarks4 = "1";
        } else {
            mBuildingFoorStructureMarks4 = "0";
        }


        /*Space*/
        if (mCheckBoxBuildingSpaceAdequate.isChecked()) {
            mBuildingSpaceMarksINT = mBuildingSpaceMarksINT + 1;
            setMarksTwo(mBuildingSpaceMarksINT, mTextViewMarksBuildingSpace);
            mBuildingSpaceMarks1 = "1";
        } else {
            mBuildingSpaceMarks1 = "0";
        }
        if (mCheckBoxBuildingSpaceAppropriate.isChecked()) {
            mBuildingSpaceMarksINT = mBuildingSpaceMarksINT + 1;
            setMarksTwo(mBuildingSpaceMarksINT, mTextViewMarksBuildingSpace);
            mBuildingSpaceMarks2 = "1";
        } else {
            mBuildingSpaceMarks2 = "0";
        }



        /*Ceiling Structure*/
        if (mCheckBoxBuildingCeilingStructureClean.isChecked()) {
            mBuildingCeilingStructureMarksINT = mBuildingCeilingStructureMarksINT + 1;
            setMarksFour(mBuildingCeilingStructureMarksINT, mTextViewMarksBuildingCeilingStructure);
            mBuildinCeilingStructureMarks1 = "1";
        } else {
            mBuildinCeilingStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureNoAccumilation.isChecked()) {
            mBuildingCeilingStructureMarksINT = mBuildingCeilingStructureMarksINT + 1;
            setMarksFour(mBuildingCeilingStructureMarksINT, mTextViewMarksBuildingCeilingStructure);
            mBuildingCeilingStructureMarks2 = "1";
        } else {
            mBuildingCeilingStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureNoContamination.isChecked()) {
            mBuildingCeilingStructureMarksINT = mBuildingCeilingStructureMarksINT + 1;
            setMarksFour(mBuildingCeilingStructureMarksINT, mTextViewMarksBuildingCeilingStructure);
            mBuildingCeilingStructureMarks3 = "1";
        } else {
            mBuildingCeilingStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureSuitable.isChecked()) {
            mBuildingCeilingStructureMarksINT = mBuildingCeilingStructureMarksINT + 1;
            setMarksFour(mBuildingCeilingStructureMarksINT, mTextViewMarksBuildingCeilingStructure);
            mBuildingCeilingStructureMarks4 = "1";
        } else {
            mBuildingCeilingStructureMarks4 = "0";
        }

        String mComment = mEditTextComments.getText().toString();

        String mBuildingStructureMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        String mBuildingStructureReMarks = mTextViewMarksBuildingStructure.getText().toString();

        String mBuildingLightAndVentilationMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        String mBuildingLightAndVentilationReMarks = mTextViewMarksBuildingLightandVentilation.getText().toString();

        String mBuildingWallmaintananceMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        String mBuildingWallmaintananceReMarks = mTextViewMarksBuildingWallMaintanance.getText().toString();

        String mBuildingFloorStructureMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        String mBuildingFloorStructureReMarks = mTextViewMarksBuildingFloorStructure.getText().toString();

        String mBuildingRiskFactorsMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        String mBuildingRiskFactorsReMarks = mTextViewMarksBuildingRiskFactors.getText().toString();

        String mBuildingCeilingStructuresMarks = Integer.valueOf(mBuildingCeilingStructureMarksINT).toString();
        String mBuildingCeilingStructuresReMarks = mTextViewMarksBuildingCeilingStructure.getText().toString();

        String mBuildingSpaceMarks = Integer.valueOf(mBuildingSpaceMarksINT).toString();
        String mBuildingSpaceReMarks = mTextViewMarksBuildingSpace.getText().toString();


        FloorStructure floorStructure = new FloorStructure(mBuildingFoorStructureMarks1,
                mBuildingFoorStructureMarks2, mBuildingFoorStructureMarks3, mBuildingFoorStructureMarks4,
                mBuildingFloorStructureMarks, mBuildingFloorStructureReMarks);
        CeilingStructure ceilingStructure = new CeilingStructure(mBuildinCeilingStructureMarks1,
                mBuildingCeilingStructureMarks2, mBuildingCeilingStructureMarks3, mBuildingCeilingStructureMarks4,
                mBuildingCeilingStructuresMarks, mBuildingCeilingStructuresReMarks);
        LightNVentilation lightNVentilation = new LightNVentilation(mBuildingLightAndVentilationMarks1
                , mBuildingLightAndVentilationMarks2, mBuildingLightAndVentilationMarks, mBuildingLightAndVentilationReMarks);
        RiskFactors riskFactors = new RiskFactors(mBuildingRiskFactorsMarks1,
                mBuildingRiskFactorsMarks2, mBuildingRiskFactorsMarks, mBuildingRiskFactorsReMarks);
        Space space = new Space(mBuildingSpaceMarks1, mBuildingSpaceMarks2, mBuildingSpaceMarks, mBuildingSpaceReMarks);
        Structure structure = new Structure(mBuildingStructureMarks1,
                mBuildingStructureMarks2, mBuildingStructureMarks3, mBuildingStructureMarks4,
                mBuildingStructureMarks, mBuildingStructureReMarks);
        WallMaintatance wallMaintatance = new WallMaintatance(mBuildingWallMaintanance1,
                mBuildingWallMaintanance2, mBuildingWallMaintanance3, mBuildingWallMaintanance4,
                mBuildingWallmaintananceMarks, mBuildingWallmaintananceReMarks);
        BuildingModel buildingModel = new BuildingModel(ceilingStructure, mComment, floorStructure,
                building_id, lightNVentilation, building_id, riskFactors, space, structure, wallMaintatance);

        mDatabaseFoodSafe.child(building_id).setValue(buildingModel);

        /*startActivity(Intent,BuildingActivity.this,FoodPreperationActivity.class);*/

        Intent intent = new Intent(BuildingActivity.this,FoodPreperationActivity.class);
        startActivity(intent);
    }
}
