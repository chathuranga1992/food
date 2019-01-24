package com.uwu.ans.foodsafty.new_record_food_preperation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_building.BuildingActivity;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.CeilingStructure;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Space;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.WallMaintatance;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Cleaning;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Cleanlines;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Contamination;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Drainage;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.FloorStructure;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.FoodProcessingModel;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.HouseKeeping;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.LightNVentilation;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.PestControl;
import com.uwu.ans.foodsafty.new_record_food_preperation.domains.Sanitation;
import com.uwu.ans.foodsafty.result.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodPreperationActivity extends AppCompatActivity {

    public DatabaseReference mDatabaseFoodSafe;

    private int mFoodPreparationFullMarksINT = 0;
    private int mFoodPreparationFloorStructureMarksINT = 0;
    private int mFoodPreparationCeilingStructureMarksINT = 0;
    private int mFoodPreparationWallStructureMarksINT = 0;
    private int mFoodPreparationContaminationMarksINT = 0;
    private int mFoodPreparationSanitationMarksINT = 0;
    private int mFoodPreparationLightsAndVentilationMarksINT = 0;
    private int mFoodPreparationCleanlinessMarksINT = 0;
    private int mFoodPreparationHouseKeepingMarksINT = 0;
    private int mFoodPreparationPestControlMarksINT = 0;
    private int mFoodPreparationCleaningMarksINT = 0;
    private int mFoodPreparationSpaceMarksINT = 0;
    private int mFoodPreparationDrainageMarksINT = 0;

    @BindView(R.id.food_processing_area_full_marks)
    TextView mTextViewFoodProcessingAreaFullMarks;

    @BindView(R.id.food_processing_area_comments)
    EditText mEditTextFoodProcessingAreaComments;

    /*floor structure*/
    @BindView(R.id.food_processing_area_marks_floor_structure)
    TextView mTextViewMarksFoodProcessingAreaFloorStructure;
    @BindView(R.id.food_processing_area_floor_structure_clean)
    CheckBox mCheckBoxFoodProcessingAreaFloorStructureClean;
    @BindView(R.id.food_processing_area_floor_structure_no_accumulation)
    CheckBox mCheckBoxFoodProcessingAreaFloorStructureNoAccumilation;
    @BindView(R.id.food_processing_area_floor_structure_no_contamination)
    CheckBox mCheckBoxFoodProcessingAreaFloorStructureNoContamination;
    @BindView(R.id.food_processing_area_floor_structure_suitable)
    CheckBox mCheckBoxFoodProcessingAreaFloorStructureSuitable;

    /*Ceiling structure*/
    @BindView(R.id.marks_food_processing_area_ceiling_structure)
    TextView mTextViewMarksFoodProcessingAreaCelingStructure;
    @BindView(R.id.food_processing_area_ceiling_structure_clean)
    CheckBox mCheckBoxFoodProcessingAreaCelingStructureClean;
    @BindView(R.id.food_processing_area_ceiling_structure_no_accumulation)
    CheckBox mCheckBoxFoodProcessingAreaCelingStructureNoAccumilation;
    @BindView(R.id.food_processing_area_ceiling_structure_no_contamination)
    CheckBox mCheckBoxFoodProcessingAreaCelingStructureNoContamination;
    @BindView(R.id.food_processing_area_ceiling_structure_suitable)
    CheckBox mCheckBoxFoodProcessingAreaCelingStructureSuitable;

    /*Wall structure*/
    @BindView(R.id.marks_food_processing_area_wall_maintenance)
    TextView mTextViewMarksFoodProcessingAreaWallMaintanance;
    @BindView(R.id.food_processing_area_wall_maintenance_clean)
    CheckBox mCheckBoxFoodProcessingAreaWallMaintananceClean;
    @BindView(R.id.food_processing_area_wall_maintenance_no_accumulation)
    CheckBox mCheckBoxFoodProcessingAreaWallMaintananceNoAccumilation;
    @BindView(R.id.food_processing_area_wall_maintenance_no_contamination)
    CheckBox mCheckBoxFoodProcessingAreaWallMaintananceNoContamination;
    @BindView(R.id.food_processing_area_wall_maintenance_suitable)
    CheckBox mCheckBoxFoodProcessingAreaWallMaintananceSuitable;

    /*Contamination*/
    @BindView(R.id.food_processing_area_marks_contamination)
    TextView mTextViewMarksFoodProcessingAreaContamination;
    @BindView(R.id.food_processing_area_contamination_from_garbage)
    CheckBox mCheckBoxFoodProcessingAreaContaminationFromGarbage;
    @BindView(R.id.food_processing_area_contamination_from_hazards)
    CheckBox mCheckBoxFoodProcessingAreaContaminationFromHazzards;
    @BindView(R.id.food_processing_area_contamination_from_sanitizers)
    CheckBox mCheckBoxFoodProcessingAreaContaminationFromSanitires;
    @BindView(R.id.food_processing_area_contamination_from_toilets)
    CheckBox mCheckBoxFoodProcessingAreaContaminationFromToilets;

    /*Sanitation*/
    @BindView(R.id.marks_food_processing_area_sanitation)
    TextView mTextViewMarksSanitation;
    @BindView(R.id.food_processing_area_sanitation_adequate)
    CheckBox mCheckBoxSanitationAdequate;
    @BindView(R.id.food_processing_area_sanitation_appropriate)
    CheckBox mCheckBoxSanitationAppropriate;

    /*LightsNVentilation*/
    @BindView(R.id.marks_food_processing_area_light_amp_ventilation)
    TextView mTextViewMarksLightsNVentilation;
    @BindView(R.id.food_processing_area_light_amp_ventilation_adequate)
    CheckBox mCheckBoxLightsNVentilationAdequate;
    @BindView(R.id.food_processing_area_light_amp_ventilation_appropriate)
    CheckBox mCheckBoxLightsNVentilationAppropriate;

    /*Cleanliness*/
    @BindView(R.id.marks_food_processing_area_cleanliness)
    TextView mTextViewMarksCleanliness;
    @BindView(R.id.food_processing_area_cleanliness_adequate)
    CheckBox mCheckBoxCleanlinessAdequate;
    @BindView(R.id.food_processing_area_cleanliness_appropriate)
    CheckBox mCheckBoxCleanlinessAppropriate;

    /*HouseKeeping*/
    @BindView(R.id.marks_food_processing_area_house_keeping)
    TextView mTextViewMarksHouseKeeping;
    @BindView(R.id.food_processing_area_house_keeping_adequate)
    CheckBox mCheckBoxHouseKeepingsAdequate;
    @BindView(R.id.food_processing_area_house_keeping_appropriate)
    CheckBox mCheckBoxHouseKeepingAppropriate;

    /*PestControl*/
    @BindView(R.id.marks_food_processing_area_pest_control)
    TextView mTextViewMarksPestControl;
    @BindView(R.id.food_processing_area_pest_control_no_pets)
    CheckBox mCheckBoxPestControlNoPests;
    @BindView(R.id.food_processing_area_pest_control_no_rodents)
    CheckBox mCheckBoxPestControlNoRodents;

    /*Cleaning*/
    @BindView(R.id.food_processing_area_marks_cleaning)
    TextView mTextViewMarksCleaning;
    @BindView(R.id.food_processing_area_clean_adequate)
    CheckBox mCheckBoxCleaningAdequate;
    @BindView(R.id.food_processing_area_clean_appropriate)
    CheckBox mCheckBoxCleaningAppropriate;
    @BindView(R.id.food_processing_area_clean_daily)
    CheckBox mCheckBoxCleaningDaily;
    @BindView(R.id.food_processing_area_clean_no_accumulation)
    CheckBox mCheckBoxCleaningNoAccumilation;

    /*Space*/
    @BindView(R.id.food_processing_area_marks_space)
    TextView mTextViewMarksSpace;
    @BindView(R.id.food_processing_area_space_adequate)
    CheckBox mCheckBoxSpaceAdequate;
    @BindView(R.id.food_processing_area_space_appropriate)
    CheckBox mCheckBoxSpaceAppropriate;

    /*Drainage*/
    @BindView(R.id.food_processing_area_marks_drainage)
    TextView mTextViewMarksDrainage;
    @BindView(R.id.food_processing_area_drainage_adequate)
    CheckBox mCheckBoxDrainageAdequate;
    @BindView(R.id.food_processing_area_drainage_safe)
    CheckBox mCheckBoxDrainageSafe;
    @BindView(R.id.food_processing_area_drainage_suitable)
    CheckBox mCheckBoxDrainageSuitable;
    @BindView(R.id.food_processing_area_drainage_no_accumulation)
    CheckBox mCheckBoxDrainageNoAccumilation;


    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_food_preperation);
        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference("food_preparation");

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
    }

/*    public void setMarksFour(int marks, TextView mTextView) {
        String mStringMarks = Integer.valueOf(marks).toString();
        mTextView.setText(mStringMarks);
        setMarkViewFour(mTextView, mStringMarks);
    }

    public void setMarksTwo(int marks, TextView mTextView) {
        marks = marks + 1;
        String mStringMarks = Integer.valueOf(marks).toString();
        mTextView.setText(mStringMarks);
        setMarkViewTwo(mTextView, mStringMarks);
    }*/

    public void setMarkViewFour(TextView mTextView, String mFoodPreperationSuitabilityMarks) {

        mTextView.setTextColor(Color.RED);
        mTextView.setText(getString(R.string.inadequate));

        if (mFoodPreperationSuitabilityMarks.equals("4")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.good));
        }
        if (mFoodPreperationSuitabilityMarks.equals("3")) {
            mTextView.setTextColor(Color.BLUE);
            mTextView.setText(getString(R.string.adequate));
        }
        if (mFoodPreperationSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        }



    }

    public void setMarkViewTwo(TextView mTextView, String mFoodPreperationSuitabilityMarks) {

        mTextView.setTextColor(Color.RED);
        mTextView.setText(getString(R.string.inadequate));

        if (mFoodPreperationSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.adequate));
        }
        if(mFoodPreperationSuitabilityMarks.equals("1")){
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        }


    }

    @OnClick(R.id.food_processing_area_next_btn)
    public void setFoodPreparation(View view) {

        String mFoodPreperationFloorStructureMarks1, mFoodPreperationFloorStructureMarks2, mFoodPreperationFloorStructureMarks3, mFoodPreperationFloorStructureMarks4;
        String mFoodPreperationCeilingStructureMarks1, mFoodPreperationCeilingStructureMarks2, mFoodPreperationCeilingStructureMarks3, mFoodPreperationCeilingStructureMarks4;
        String mFoodPreperationWallMaintanance1, mFoodPreperationWallMaintanance2, mFoodPreperationWallMaintanance3, mFoodPreperationWallMaintanance4;
        String mFoodPreperationContaminationMarks1, mFoodPreperationContaminationMarks2, mFoodPreperationContaminationMarks3, mFoodPreperationContaminationMarks4;
        String mFoodPreperationSanitationMarks1, mFoodPreperationSanitationMarks2;
        String mFoodPreperationLightAndVentilationMarks1, mFoodPreperationLightAndVentilationMarks2;
        String mFoodPreperationCleanlinessMarks1, mFoodPreperationCleanlinessMarks2;
        String mFoodPreperationHouseKeepingMarksMarks1, mFoodPreperationHouseKeepingMarksMarks2;
        String mFoodPreperationPestControlMarks1, mFoodPreperationPestControlMarks2;
        String mFoodPreperationCleaningMarks1, mFoodPreperationCleaningMarks2, mFoodPreperationCleaningMarks3, mFoodPreperationCleaningMarks4;
        String mFoodPreperationSpaceMarks1, mFoodPreperationSpaceMarks2;
        String mFoodPreperationDrainageMarks1, mFoodPreperationDrainageMarks2, mFoodPreperationDrainageMarks3, mFoodPreperationDrainageMarks4;
        String food_preperation_id = mDatabaseFoodSafe.push().getKey();
        String mComment = mEditTextFoodProcessingAreaComments.getText().toString();

        /*Floor Structure*/
        if (mCheckBoxFoodProcessingAreaFloorStructureClean.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaFloorStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            //setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFoodProcessingAreaFloorStructure);
            mFoodPreperationFloorStructureMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            mFoodPreperationFloorStructureMarks1 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoAccumilation.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaFloorStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            //setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFoodProcessingAreaFloorStructure);
            mFoodPreperationFloorStructureMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            mFoodPreperationFloorStructureMarks2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoContamination.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaFloorStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            //setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFoodProcessingAreaFloorStructure);
            mFoodPreperationFloorStructureMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            mFoodPreperationFloorStructureMarks3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureSuitable.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaFloorStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            //setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFoodProcessingAreaFloorStructure);
            mFoodPreperationFloorStructureMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);
            mFoodPreperationFloorStructureMarks4 = "0";
        }
        String mFoodPreparationFloorStructureMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
        String mFoodPreparationFloorStructureReMarks = mTextViewMarksFoodProcessingAreaFloorStructure.getText().toString();


        /*Ceiling Structure*/
        if (mCheckBoxFoodProcessingAreaCelingStructureClean.isChecked()) {
            mFoodPreparationCeilingStructureMarksINT = mFoodPreparationCeilingStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaCelingStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            //setMarksFour(mFoodPreparationCeilingStructureMarksINT, mTextViewMarksFoodProcessingAreaCelingStructure);
            mFoodPreperationCeilingStructureMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            mFoodPreperationCeilingStructureMarks1 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureNoAccumilation.isChecked()) {
            mFoodPreparationCeilingStructureMarksINT = mFoodPreparationCeilingStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaCelingStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            //setMarksFour(mFoodPreparationCeilingStructureMarksINT, mTextViewMarksFoodProcessingAreaCelingStructure);
            mFoodPreperationCeilingStructureMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            mFoodPreperationCeilingStructureMarks2 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureSuitable.isChecked()) {
            mFoodPreparationCeilingStructureMarksINT = mFoodPreparationCeilingStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaCelingStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            //setMarksFour(mFoodPreparationCeilingStructureMarksINT, mTextViewMarksFoodProcessingAreaCelingStructure);
            mFoodPreperationCeilingStructureMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            mFoodPreperationCeilingStructureMarks3 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureNoContamination.isChecked()) {
            mFoodPreparationCeilingStructureMarksINT = mFoodPreparationCeilingStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaCelingStructure.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            //setMarksFour(mFoodPreparationCeilingStructureMarksINT, mTextViewMarksFoodProcessingAreaCelingStructure);
            mFoodPreperationCeilingStructureMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);
            mFoodPreperationCeilingStructureMarks4 = "0";
        }
        String mFoodPreperationCeilingStructureMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
        String mFoodPreperationCeilingStructureReMarks = mTextViewMarksFoodProcessingAreaCelingStructure.getText().toString();


        /*Wall maintenance*/
        if (mCheckBoxFoodProcessingAreaWallMaintananceNoContamination.isChecked()) {
            mFoodPreparationWallStructureMarksINT = mFoodPreparationWallStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaWallMaintanance.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            //setMarksFour(mFoodPreparationWallStructureMarksINT, mTextViewMarksFoodProcessingAreaWallMaintanance);
            mFoodPreperationWallMaintanance1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            mFoodPreperationWallMaintanance1 = "0";
        }

        if (mCheckBoxFoodProcessingAreaWallMaintananceNoAccumilation.isChecked()) {
            mFoodPreparationWallStructureMarksINT = mFoodPreparationWallStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaWallMaintanance.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            //setMarksFour(mFoodPreparationWallStructureMarksINT, mTextViewMarksFoodProcessingAreaWallMaintanance);
            mFoodPreperationWallMaintanance2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            mFoodPreperationWallMaintanance2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaWallMaintananceClean.isChecked()) {
            mFoodPreparationWallStructureMarksINT = mFoodPreparationWallStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaWallMaintanance.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            //setMarksFour(mFoodPreparationWallStructureMarksINT, mTextViewMarksFoodProcessingAreaWallMaintanance);
            mFoodPreperationWallMaintanance3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            mFoodPreperationWallMaintanance3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaWallMaintananceSuitable.isChecked()) {
            mFoodPreparationWallStructureMarksINT = mFoodPreparationWallStructureMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            mTextViewMarksFoodProcessingAreaWallMaintanance.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            //setMarksFour(mFoodPreparationWallStructureMarksINT, mTextViewMarksFoodProcessingAreaWallMaintanance);
            mFoodPreperationWallMaintanance4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);
            mFoodPreperationWallMaintanance4 = "0";
        }
        String mFoodPreperationWallMaintananceMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
        String mFoodPreperationWallMaintananceRemarks = mTextViewMarksFoodProcessingAreaWallMaintanance.getText().toString();


        /*Contamination*/
        if (mCheckBoxFoodProcessingAreaContaminationFromGarbage.isChecked()) {
            mFoodPreparationContaminationMarksINT = mFoodPreparationContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            mTextViewMarksFoodProcessingAreaContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            //setMarksFour(mFoodPreparationContaminationMarksINT, mTextViewMarksFoodProcessingAreaContamination);
            mFoodPreperationContaminationMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            mFoodPreperationContaminationMarks1 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromHazzards.isChecked()) {
            mFoodPreparationContaminationMarksINT = mFoodPreparationContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            mTextViewMarksFoodProcessingAreaContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            //setMarksFour(mFoodPreparationContaminationMarksINT, mTextViewMarksFoodProcessingAreaContamination);
            mFoodPreperationContaminationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            mFoodPreperationContaminationMarks2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromSanitires.isChecked()) {
            mFoodPreparationContaminationMarksINT = mFoodPreparationContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            mTextViewMarksFoodProcessingAreaContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            //setMarksFour(mFoodPreparationContaminationMarksINT, mTextViewMarksFoodProcessingAreaContamination);
            mFoodPreperationContaminationMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            mFoodPreperationContaminationMarks3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromToilets.isChecked()) {
            mFoodPreparationContaminationMarksINT = mFoodPreparationContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            mTextViewMarksFoodProcessingAreaContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
           // setMarksFour(mFoodPreparationContaminationMarksINT, mTextViewMarksFoodProcessingAreaContamination);
            mFoodPreperationContaminationMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);
            mFoodPreperationContaminationMarks4 = "0";
        }
        String mFoodPreperationContaminationMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
        String mFoodPreperationContaminationReMarks = mTextViewMarksFoodProcessingAreaContamination.getText().toString();


        /*Light and ventilation*/
        if (mCheckBoxLightsNVentilationAdequate.isChecked()) {
            mFoodPreparationLightsAndVentilationMarksINT = mFoodPreparationLightsAndVentilationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
            mTextViewMarksLightsNVentilation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksLightsNVentilation, mStringMarks);
            //setMarksTwo(mFoodPreparationLightsAndVentilationMarksINT, mTextViewMarksLightsNVentilation);
            mFoodPreperationLightAndVentilationMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksLightsNVentilation, mStringMarks);
            mFoodPreperationLightAndVentilationMarks1 = "0";
        }
        if (mCheckBoxLightsNVentilationAppropriate.isChecked()) {
            mFoodPreparationLightsAndVentilationMarksINT = mFoodPreparationLightsAndVentilationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
            mTextViewMarksLightsNVentilation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksLightsNVentilation, mStringMarks);
            //setMarksTwo(mFoodPreparationLightsAndVentilationMarksINT, mTextViewMarksLightsNVentilation);
            mFoodPreperationLightAndVentilationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksLightsNVentilation, mStringMarks);
            mFoodPreperationLightAndVentilationMarks2 = "0";
        }
        String mFoodPreperationLightAndVentilationMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
        String mFoodPreperationLightAndVentilationReMarks = mTextViewMarksLightsNVentilation.getText().toString();



        /*Cleanliness*/
        if (mCheckBoxCleanlinessAdequate.isChecked()) {
            mFoodPreparationCleanlinessMarksINT = mFoodPreparationCleanlinessMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
            mTextViewMarksCleanliness.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksCleanliness, mStringMarks);
            //etMarksTwo(mFoodPreparationCleanlinessMarksINT, mTextViewMarksCleanliness);
            mFoodPreperationCleanlinessMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
            setMarkViewTwo(mTextViewMarksCleanliness, mStringMarks);
            mFoodPreperationCleanlinessMarks1 = "0";
        }
        if (mCheckBoxCleanlinessAppropriate.isChecked()) {
            mFoodPreparationCleanlinessMarksINT = mFoodPreparationCleanlinessMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
            mTextViewMarksCleanliness.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksCleanliness, mStringMarks);
            //setMarksTwo(mFoodPreparationCleanlinessMarksINT, mTextViewMarksCleanliness);
            mFoodPreperationCleanlinessMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
            setMarkViewTwo(mTextViewMarksCleanliness, mStringMarks);
            mFoodPreperationCleanlinessMarks2 = "0";
        }
        String mFoodPreperationCleanlinessMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
        String mFoodPreperationCleanlinessReMarks = mTextViewMarksCleanliness.getText().toString();

        /*HouseKeeping*/
        if (mCheckBoxHouseKeepingsAdequate.isChecked()) {
            mFoodPreparationHouseKeepingMarksINT = mFoodPreparationHouseKeepingMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
            mTextViewMarksHouseKeeping.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksHouseKeeping, mStringMarks);
            //setMarksTwo(mFoodPreparationHouseKeepingMarksINT, mTextViewMarksHouseKeeping);
            mFoodPreperationHouseKeepingMarksMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
            setMarkViewTwo(mTextViewMarksHouseKeeping, mStringMarks);
            mFoodPreperationHouseKeepingMarksMarks1 = "0";
        }
        if (mCheckBoxHouseKeepingAppropriate.isChecked()) {
            mFoodPreparationHouseKeepingMarksINT = mFoodPreparationHouseKeepingMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
            mTextViewMarksHouseKeeping.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksHouseKeeping, mStringMarks);
            //setMarksTwo(mFoodPreparationHouseKeepingMarksINT, mTextViewMarksHouseKeeping);
            mFoodPreperationHouseKeepingMarksMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
            setMarkViewTwo(mTextViewMarksHouseKeeping, mStringMarks);
            mFoodPreperationHouseKeepingMarksMarks2 = "0";
        }
        String mFoodPreperationHouseKeepingMarksMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
        String mFoodPreperationHouseKeepingMarksReMarks = mTextViewMarksHouseKeeping.getText().toString();

        /*Pest Control*/
        if (mCheckBoxPestControlNoPests.isChecked()) {
            mFoodPreparationPestControlMarksINT = mFoodPreparationPestControlMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
            mTextViewMarksPestControl.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksPestControl, mStringMarks);
            //setMarksTwo(mFoodPreparationPestControlMarksINT, mTextViewMarksPestControl);
            mFoodPreperationPestControlMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
            setMarkViewTwo(mTextViewMarksPestControl, mStringMarks);
            mFoodPreperationPestControlMarks1 = "0";
        }

        if (mCheckBoxPestControlNoRodents.isChecked()) {
            mFoodPreparationPestControlMarksINT = mFoodPreparationPestControlMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
            mTextViewMarksPestControl.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksPestControl, mStringMarks);
            //setMarksTwo(mFoodPreparationPestControlMarksINT, mTextViewMarksPestControl);
            mFoodPreperationPestControlMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
            setMarkViewTwo(mTextViewMarksPestControl, mStringMarks);
            mFoodPreperationPestControlMarks2 = "0";
        }
        String mFoodPreperationPestControlMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
        String mFoodPreperationPestControlReMarks = mTextViewMarksPestControl.getText().toString();


        /*Cleaning*/
        if (mCheckBoxCleaningAdequate.isChecked()) {
            mFoodPreparationCleaningMarksINT = mFoodPreparationCleaningMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            mTextViewMarksCleaning.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            //setMarksFour(mFoodPreparationCleaningMarksINT, mTextViewMarksCleaning);
            mFoodPreperationCleaningMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            mFoodPreperationCleaningMarks1 = "0";
        }

        if (mCheckBoxCleaningAppropriate.isChecked()) {
            mFoodPreparationCleaningMarksINT = mFoodPreparationCleaningMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            mTextViewMarksCleaning.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            //setMarksFour(mFoodPreparationCleaningMarksINT, mTextViewMarksCleaning);
            mFoodPreperationCleaningMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            mFoodPreperationCleaningMarks2 = "0";
        }

        if (mCheckBoxCleaningDaily.isChecked()) {
            mFoodPreparationCleaningMarksINT = mFoodPreparationCleaningMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            mTextViewMarksCleaning.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            //setMarksFour(mFoodPreparationCleaningMarksINT, mTextViewMarksCleaning);
            mFoodPreperationCleaningMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            mFoodPreperationCleaningMarks3 = "0";
        }

        if (mCheckBoxCleaningNoAccumilation.isChecked()) {
            mFoodPreparationCleaningMarksINT = mFoodPreparationCleaningMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            mTextViewMarksCleaning.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            //setMarksFour(mFoodPreparationCleaningMarksINT, mTextViewMarksCleaning);
            mFoodPreperationCleaningMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
            setMarkViewFour(mTextViewMarksCleaning, mStringMarks);
            mFoodPreperationCleaningMarks4 = "0";
        }
        String mFoodPreperationCleaningMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
        String mFoodPreperationCleaningReMarks = mTextViewMarksPestControl.getText().toString();



        /*space*/
        if (mCheckBoxSpaceAdequate.isChecked()) {
            mFoodPreparationSpaceMarksINT = mFoodPreparationSpaceMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
            mTextViewMarksSpace.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksSpace, mStringMarks);
            //setMarksTwo(mFoodPreparationSpaceMarksINT, mTextViewMarksSpace);
            mFoodPreperationSpaceMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
            setMarkViewTwo(mTextViewMarksSpace, mStringMarks);
            mFoodPreperationSpaceMarks1 = "0";
        }

        if (mCheckBoxSpaceAppropriate.isChecked()) {
            mFoodPreparationSpaceMarksINT = mFoodPreparationSpaceMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
            mTextViewMarksSpace.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksSpace, mStringMarks);
            //setMarksTwo(mFoodPreparationSpaceMarksINT, mTextViewMarksSpace);
            mFoodPreperationSpaceMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
            setMarkViewTwo(mTextViewMarksSpace, mStringMarks);
            mFoodPreperationSpaceMarks2 = "0";
        }
        String mFoodPreperationSpaceMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
        String mFoodPreperationSpaceReMarks = mTextViewMarksSpace.getText().toString();

        /*sanitation*/
        if (mCheckBoxSanitationAdequate.isChecked()) {
            mFoodPreparationSanitationMarksINT = mFoodPreparationSanitationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
            mTextViewMarksSanitation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksSanitation, mStringMarks);
            //setMarksTwo(mFoodPreparationSanitationMarksINT, mTextViewMarksSanitation);
            mFoodPreperationSanitationMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksSanitation, mStringMarks);
            mFoodPreperationSanitationMarks1 = "0";
        }
        if (mCheckBoxSanitationAppropriate.isChecked()) {
            mFoodPreparationSanitationMarksINT = mFoodPreparationSanitationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
            mTextViewMarksSanitation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksSanitation, mStringMarks);
            //setMarksTwo(mFoodPreparationSanitationMarksINT, mTextViewMarksSanitation);
            mFoodPreperationSanitationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksSanitation, mStringMarks);
            mFoodPreperationSanitationMarks2 = "0";
        }
        String mFoodPreperationSanitationMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
        String mFoodPreperationSanitationReMarks = mTextViewMarksSanitation.getText().toString();

        /*drainage*/
        if (mCheckBoxDrainageAdequate.isChecked()) {
            mFoodPreparationDrainageMarksINT = mFoodPreparationDrainageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            mTextViewMarksDrainage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            //setMarksFour(mFoodPreparationDrainageMarksINT, mTextViewMarksDrainage);
            mFoodPreperationDrainageMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            mFoodPreperationDrainageMarks1 = "0";
        }

        if (mCheckBoxDrainageNoAccumilation.isChecked()) {
            mFoodPreparationDrainageMarksINT = mFoodPreparationDrainageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            mTextViewMarksDrainage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            //setMarksFour(mFoodPreparationDrainageMarksINT, mTextViewMarksDrainage);
            mFoodPreperationDrainageMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            mFoodPreperationDrainageMarks2 = "0";
        }

        if (mCheckBoxDrainageSafe.isChecked()) {
            mFoodPreparationDrainageMarksINT = mFoodPreparationDrainageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            mTextViewMarksDrainage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            //setMarksFour(mFoodPreparationDrainageMarksINT, mTextViewMarksDrainage);
            mFoodPreperationDrainageMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            mFoodPreperationDrainageMarks3 = "0";
        }

        if (mCheckBoxDrainageSuitable.isChecked()) {
            mFoodPreparationDrainageMarksINT = mFoodPreparationDrainageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            mTextViewMarksDrainage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            //setMarksFour(mFoodPreparationDrainageMarksINT, mTextViewMarksDrainage);
            mFoodPreperationDrainageMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
            setMarkViewFour(mTextViewMarksDrainage, mStringMarks);
            mFoodPreperationDrainageMarks4 = "0";
        }
        String mFoodPreperationDrainageMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
        String mFoodPreperationDrainageReMarks = mTextViewMarksDrainage.getText().toString();


       mFoodPreparationFullMarksINT = mFoodPreparationFloorStructureMarksINT + mFoodPreparationCeilingStructureMarksINT +
               mFoodPreparationWallStructureMarksINT + mFoodPreparationContaminationMarksINT
               + mFoodPreparationSanitationMarksINT + mFoodPreparationLightsAndVentilationMarksINT
               +mFoodPreparationCleanlinessMarksINT + mFoodPreparationHouseKeepingMarksINT
               +mFoodPreparationSpaceMarksINT + mFoodPreparationPestControlMarksINT
               + mFoodPreparationCleaningMarksINT +mFoodPreparationDrainageMarksINT ;

        String mFoodPreparationFullMarks = Integer.valueOf(mFoodPreparationFullMarksINT).toString();


        CeilingStructure ceilingStructure  = new CeilingStructure(mFoodPreperationCeilingStructureMarks1
                ,mFoodPreperationCeilingStructureMarks,mFoodPreperationCeilingStructureMarks2,
                mFoodPreperationCeilingStructureMarks3,mFoodPreperationCeilingStructureReMarks,
                mFoodPreperationCeilingStructureMarks4);

        Cleaning cleaning = new Cleaning(mFoodPreperationCleaningMarks1,mFoodPreperationCleaningMarks2
                ,mFoodPreperationCleaningMarks3,mFoodPreperationCleaningMarks4,
                mFoodPreperationCleaningMarks,mFoodPreperationCleaningReMarks);

        Cleanlines cleanlines = new Cleanlines(mFoodPreperationCleanlinessMarks1,mFoodPreperationCleanlinessMarks2,
                mFoodPreperationCleanlinessMarks,mFoodPreperationCleanlinessReMarks);

        Contamination contamination = new Contamination(mFoodPreperationContaminationMarks1,
                mFoodPreperationContaminationMarks2,mFoodPreperationContaminationMarks3,
                mFoodPreperationContaminationMarks4,mFoodPreperationContaminationMarks,
                mFoodPreperationContaminationReMarks);

        Drainage drainage = new Drainage(mFoodPreperationDrainageMarks1,mFoodPreperationDrainageMarks2,
                mFoodPreperationDrainageMarks3,mFoodPreperationDrainageMarks4,
                mFoodPreperationDrainageMarks,mFoodPreperationDrainageReMarks);

        FloorStructure floorStructure = new FloorStructure(mFoodPreperationFloorStructureMarks1,
                mFoodPreperationFloorStructureMarks2,mFoodPreperationFloorStructureMarks3,
                mFoodPreperationFloorStructureMarks4,mFoodPreparationFloorStructureMarks,
                mFoodPreparationFloorStructureReMarks);

        HouseKeeping houseKeeping = new HouseKeeping(mFoodPreperationHouseKeepingMarksMarks1,
                mFoodPreperationHouseKeepingMarksMarks2,mFoodPreperationHouseKeepingMarksMarks,
                mFoodPreperationHouseKeepingMarksReMarks);

        LightNVentilation lightNVentilation = new LightNVentilation(mFoodPreperationLightAndVentilationMarks1,
                mFoodPreperationLightAndVentilationMarks2,mFoodPreperationLightAndVentilationMarks,
                mFoodPreperationLightAndVentilationReMarks);

        PestControl pestControl = new PestControl(mFoodPreperationPestControlMarks1,
                mFoodPreperationPestControlMarks2,mFoodPreperationPestControlMarks,
                mFoodPreparationFloorStructureReMarks);
        //RiskFactors riskFactors = new RiskFactors(m)
        Sanitation sanitation = new Sanitation(mFoodPreperationSanitationMarks1,
                mFoodPreperationSanitationMarks2,mFoodPreperationSanitationMarks
        ,mFoodPreperationSanitationReMarks);
        Space space = new Space(mFoodPreperationSpaceMarks1,mFoodPreperationSpaceMarks2,
                mFoodPreperationSpaceMarks,mFoodPreperationSpaceReMarks);
        //Structure structure = new Structure(mFoodPreperationCeilingStructureMarks1,)
        WallMaintatance wallMaintatance = new WallMaintatance(mFoodPreperationWallMaintanance1,
                mFoodPreperationWallMaintanance2,mFoodPreperationWallMaintanance3,mFoodPreperationWallMaintanance4,
                mFoodPreperationWallMaintananceMarks,mFoodPreperationWallMaintananceRemarks);



        FoodProcessingModel foodProcessingModel = new FoodProcessingModel(food_preperation_id,food_preperation_id,
                ceilingStructure,cleaning,cleanlines,contamination,drainage,floorStructure,houseKeeping,
                lightNVentilation,pestControl,sanitation,space,wallMaintatance,mFoodPreparationFullMarks,mComment
                );

        //mDatabaseFoodSafe.child(food_preperation_id).setValue(foodProcessingModel);

        //startActivity(new Intent(FoodPreperationActivity.this,ResultActivity.class));
        mDatabaseFoodSafe.child(food_preperation_id).setValue(foodProcessingModel,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Data Binding successful..");
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(FoodPreperationActivity.this,FoodPreperationActivity.class));
                        }
                    }, 3000);

                }
            }
        });

    }
}
