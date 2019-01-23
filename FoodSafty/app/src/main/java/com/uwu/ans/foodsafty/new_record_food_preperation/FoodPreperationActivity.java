package com.uwu.ans.foodsafty.new_record_food_preperation;

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
    TextView mTextViewMarksFloorStructure;
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
    TextView mTextViewMarksCelingStructure;
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
    TextView mTextViewMarksWallMaintanance;
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
    TextView mTextViewMarksContamination;
    @BindView(R.id.food_processing_area_contamination_from_garbage)
    CheckBox mCheckBoxContaminationFromGarbage;
    @BindView(R.id.food_processing_area_contamination_from_hazards)
    CheckBox mCheckBoxContaminationFromHazzards;
    @BindView(R.id.food_processing_area_contamination_from_sanitizers)
    CheckBox mCheckBoxContaminationFromSanitires;
    @BindView(R.id.food_processing_area_contamination_from_toilets)
    CheckBox mCheckBoxContaminationFromToilets;

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
    CheckBox mCheckBoxCleaninNoAccumilation;

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

    public void setMarkViewFour(TextView mTextView, String mFoodPreperationSuitabilityMarks) {
        if (mFoodPreperationSuitabilityMarks.equals("4")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.good));
        } else if (mFoodPreperationSuitabilityMarks.equals("3")) {
            mTextView.setTextColor(Color.BLUE);
            mTextView.setText(getString(R.string.adequate));
        } else if (mFoodPreperationSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        } else {
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }

    public void setMarkViewTwo(TextView mTextView, String mFoodPreperationSuitabilityMarks) {

        if (mFoodPreperationSuitabilityMarks.equals("2")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.adequate));
        } else {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.inadequate));
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
        String mFoodPreperationCleaningMarks1, mFoodPreperationCleaningMarks2 ,mFoodPreperationCleaningMarks3, mFoodPreperationCleaningMarks4;
        String mFoodPreperationSpaceMarks1, mFoodPreperationSpaceMarks2;
        String mFoodPreperationDrainageMarks1, mFoodPreperationDrainageMarks2, mFoodPreperationDrainageMarks3, mFoodPreperationDrainageMarks4;
        String food_preperation_id = mDatabaseFoodSafe.push().getKey();

        /*Floor Structure*/
        if (mCheckBoxFoodProcessingAreaFloorStructureClean.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFloorStructure);
            mFoodPreperationFloorStructureMarks1 = "1";
        } else {
            mFoodPreperationFloorStructureMarks1 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoAccumilation.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFloorStructure);
            mFoodPreperationFloorStructureMarks2 = "1";
        } else {
            mFoodPreperationFloorStructureMarks2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoContamination.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFloorStructure);
            mFoodPreperationFloorStructureMarks3 = "1";
        } else {
            mFoodPreperationFloorStructureMarks3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureSuitable.isChecked()) {
            mFoodPreparationFloorStructureMarksINT = mFoodPreparationFloorStructureMarksINT + 1;
            setMarksFour(mFoodPreparationFloorStructureMarksINT, mTextViewMarksFloorStructure);
            mFoodPreperationFloorStructureMarks4 = "1";
        } else {
            mFoodPreperationFloorStructureMarks4 = "0";
        }


    }
}
