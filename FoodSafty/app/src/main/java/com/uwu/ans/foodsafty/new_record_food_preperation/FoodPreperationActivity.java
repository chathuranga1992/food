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
import android.widget.CompoundButton;
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
import com.uwu.ans.foodsafty.new_record_storage.StorageActivity;
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

    String mFoodPreparationGrade ="F";

    double FoodPreparationPrecent =0;

    String RestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_food_preperation);
        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();

        RestKey = getIntent().getStringExtra("RestName");


        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);


        mCheckBoxFoodProcessingAreaFloorStructureClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorSturctureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaFloorStructureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorSturctureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaFloorStructureNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorSturctureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaFloorStructureNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorSturctureMarks();
            }
        });

        mCheckBoxFoodProcessingAreaCelingStructureClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaCelingStructureNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaCelingStructureNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });
        mCheckBoxFoodProcessingAreaCelingStructureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });


        mCheckBoxFoodProcessingAreaWallMaintananceClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });
        mCheckBoxFoodProcessingAreaWallMaintananceNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });
        mCheckBoxFoodProcessingAreaWallMaintananceNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });
        mCheckBoxFoodProcessingAreaWallMaintananceSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });

        mCheckBoxFoodProcessingAreaContaminationFromGarbage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contaminationMarks();
            }
        });
        mCheckBoxFoodProcessingAreaContaminationFromSanitires.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contaminationMarks();
            }
        });
        mCheckBoxFoodProcessingAreaContaminationFromHazzards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contaminationMarks();
            }
        });
        mCheckBoxFoodProcessingAreaContaminationFromToilets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contaminationMarks();
            }
        });


        mCheckBoxSanitationAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sanitationMarks();
            }
        });
        mCheckBoxSanitationAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sanitationMarks();
            }
        });

        mCheckBoxLightsNVentilationAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightVentilationMarks();
            }
        });
        mCheckBoxLightsNVentilationAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightVentilationMarks();
            }
        });


        mCheckBoxCleanlinessAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleanlinessMarks();
            }
        });
        mCheckBoxCleanlinessAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleanlinessMarks();
            }
        });


        mCheckBoxHouseKeepingAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                houseKeepingMarks();
            }
        });
        mCheckBoxHouseKeepingsAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                houseKeepingMarks();
            }
        });


        mCheckBoxPestControlNoPests.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pestControlMarks();
            }
        });
        mCheckBoxPestControlNoRodents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pestControlMarks();
            }
        });


        mCheckBoxCleaningAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleaningMarks();
            }
        });
        mCheckBoxCleaningAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleaningMarks();
            }
        });
        mCheckBoxCleaningDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleaningMarks();
            }
        });
        mCheckBoxCleaningNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleaningMarks();
            }
        });


        mCheckBoxSpaceAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spaceMarks();
            }
        });
        mCheckBoxSpaceAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spaceMarks();
            }
        });


        mCheckBoxDrainageAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                drainageMarks();
            }
        });
        mCheckBoxDrainageNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                drainageMarks();
            }
        });
        mCheckBoxDrainageSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                drainageMarks();
            }
        });
        mCheckBoxDrainageSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                drainageMarks();
            }
        });

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

        String food_preperation_id = mDatabaseFoodSafe.push().getKey();
        String mComment = mEditTextFoodProcessingAreaComments.getText().toString();

        floorSturctureMarks();

        String mFoodPreparationFloorStructureMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
        String mFoodPreparationFloorStructureReMarks = mTextViewMarksFoodProcessingAreaFloorStructure.getText().toString();

       ceilingStructureMarks();
       wallMaintananceMarks();
       contaminationMarks();
       lightVentilationMarks();
       cleanlinessMarks();
       houseKeepingMarks();
       pestControlMarks();
       cleaningMarks();
       spaceMarks();
       sanitationMarks();
       drainageMarks();


        String mFoodPreperationCeilingStructureMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
        String mFoodPreperationCeilingStructureReMarks = mTextViewMarksFoodProcessingAreaCelingStructure.getText().toString();

        String mFoodPreperationWallMaintananceMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
        String mFoodPreperationWallMaintananceRemarks = mTextViewMarksFoodProcessingAreaWallMaintanance.getText().toString();

        String mFoodPreperationContaminationMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
        String mFoodPreperationContaminationReMarks = mTextViewMarksFoodProcessingAreaContamination.getText().toString();

        String mFoodPreperationLightAndVentilationMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
        String mFoodPreperationLightAndVentilationReMarks = mTextViewMarksLightsNVentilation.getText().toString();

        String mFoodPreperationCleanlinessMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
        String mFoodPreperationCleanlinessReMarks = mTextViewMarksCleanliness.getText().toString();

        String mFoodPreperationHouseKeepingMarksMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
        String mFoodPreperationHouseKeepingMarksReMarks = mTextViewMarksHouseKeeping.getText().toString();

        String mFoodPreperationPestControlMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
        String mFoodPreperationPestControlReMarks = mTextViewMarksPestControl.getText().toString();

        String mFoodPreperationCleaningMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
        String mFoodPreperationCleaningReMarks = mTextViewMarksPestControl.getText().toString();

        String mFoodPreperationSpaceMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
        String mFoodPreperationSpaceReMarks = mTextViewMarksSpace.getText().toString();

        String mFoodPreperationSanitationMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
        String mFoodPreperationSanitationReMarks = mTextViewMarksSanitation.getText().toString();

        String mFoodPreperationDrainageMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
        String mFoodPreperationDrainageReMarks = mTextViewMarksDrainage.getText().toString();


       setTotalMarks();

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
        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("foodPreparationMarks").setValue(FoodPreparationPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Food processing/Serving Area Grade : " +mFoodPreparationGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(FoodPreperationActivity.this,StorageActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 3000);

                }
            }
        });

    }

    private void drainageMarks() {
        if (mCheckBoxDrainageAdequate.isChecked()) {
            mFoodPreperationDrainageMarks1 = "1";
        } else {
            mFoodPreperationDrainageMarks1 = "0";
        }

        if (mCheckBoxDrainageNoAccumilation.isChecked()) {
            mFoodPreperationDrainageMarks2 = "1";
        } else {
            mFoodPreperationDrainageMarks2 = "0";
        }

        if (mCheckBoxDrainageSafe.isChecked()) {
            mFoodPreperationDrainageMarks3 = "1";
        } else {
            mFoodPreperationDrainageMarks3 = "0";
        }

        if (mCheckBoxDrainageSuitable.isChecked()) {
            mFoodPreperationDrainageMarks4 = "1";
        } else {
            mFoodPreperationDrainageMarks4 = "0";
        }

        mFoodPreparationDrainageMarksINT = Integer.valueOf(mFoodPreperationDrainageMarks1) +Integer.valueOf(mFoodPreperationDrainageMarks2)+
                Integer.valueOf(mFoodPreperationDrainageMarks3) + Integer.valueOf(mFoodPreperationDrainageMarks4);
        String mStringMarks = Integer.valueOf(mFoodPreparationDrainageMarksINT).toString();
        setMarkViewFour(mTextViewMarksDrainage, mStringMarks);

        setTotalMarks();
    }

    private void sanitationMarks() {
        if (mCheckBoxSanitationAdequate.isChecked()) {
            mFoodPreperationSanitationMarks1 = "1";
        } else {
            mFoodPreperationSanitationMarks1 = "0";
        }
        if (mCheckBoxSanitationAppropriate.isChecked()) {
            mFoodPreperationSanitationMarks2 = "1";
        } else {
            mFoodPreperationSanitationMarks2 = "0";
        }

        mFoodPreparationSanitationMarksINT = Integer.valueOf(mFoodPreperationSanitationMarks1) +Integer.valueOf(mFoodPreperationSanitationMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationSanitationMarksINT).toString();
        setMarkViewTwo(mTextViewMarksSanitation, mStringMarks);

        setTotalMarks();
    }

    private void spaceMarks() {
        if (mCheckBoxSpaceAdequate.isChecked()) {
            mFoodPreperationSpaceMarks1 = "1";
        } else {
            mFoodPreperationSpaceMarks1 = "0";
        }

        if (mCheckBoxSpaceAppropriate.isChecked()) {
            mFoodPreperationSpaceMarks2 = "1";
        } else {
            mFoodPreperationSpaceMarks2 = "0";
        }

        mFoodPreparationSpaceMarksINT = Integer.valueOf(mFoodPreperationSpaceMarks1) +Integer.valueOf(mFoodPreperationSpaceMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationSpaceMarksINT).toString();
        setMarkViewTwo(mTextViewMarksSpace, mStringMarks);

        setTotalMarks();
    }

    private void cleaningMarks() {
        if (mCheckBoxCleaningAdequate.isChecked()) {
            mFoodPreperationCleaningMarks1 = "1";
        } else {
            mFoodPreperationCleaningMarks1 = "0";
        }

        if (mCheckBoxCleaningAppropriate.isChecked()) {
            mFoodPreperationCleaningMarks2 = "1";
        } else {
            mFoodPreperationCleaningMarks2 = "0";
        }

        if (mCheckBoxCleaningDaily.isChecked()) {
            mFoodPreperationCleaningMarks3 = "1";
        } else {
            mFoodPreperationCleaningMarks3 = "0";
        }

        if (mCheckBoxCleaningNoAccumilation.isChecked()) {
            mFoodPreperationCleaningMarks4 = "1";
        } else {
            mFoodPreperationCleaningMarks4 = "0";
        }

        mFoodPreparationCleaningMarksINT = Integer.valueOf(mFoodPreperationCleaningMarks1) +Integer.valueOf(mFoodPreperationCleaningMarks2)+
                Integer.valueOf(mFoodPreperationCleaningMarks3) + Integer.valueOf(mFoodPreperationCleaningMarks4);
        String mStringMarks = Integer.valueOf(mFoodPreparationCleaningMarksINT).toString();
        setMarkViewFour(mTextViewMarksCleaning, mStringMarks);

        setTotalMarks();
    }

    private void pestControlMarks() {
        if (mCheckBoxPestControlNoPests.isChecked()) {
            mFoodPreperationPestControlMarks1 = "1";
        } else {
            mFoodPreperationPestControlMarks1 = "0";
        }

        if (mCheckBoxPestControlNoRodents.isChecked()) {
            mFoodPreperationPestControlMarks2 = "1";
        } else {
            mFoodPreperationPestControlMarks2 = "0";
        }

        mFoodPreparationPestControlMarksINT = Integer.valueOf(mFoodPreperationPestControlMarks1) +Integer.valueOf(mFoodPreperationPestControlMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationPestControlMarksINT).toString();
        setMarkViewTwo(mTextViewMarksPestControl, mStringMarks);

        setTotalMarks();
    }

    private void houseKeepingMarks() {
        if (mCheckBoxHouseKeepingsAdequate.isChecked()) {
            mFoodPreperationHouseKeepingMarksMarks1 = "1";
        } else {
            mFoodPreperationHouseKeepingMarksMarks1 = "0";
        }
        if (mCheckBoxHouseKeepingAppropriate.isChecked()) {
            mFoodPreperationHouseKeepingMarksMarks2 = "1";
        } else {
            mFoodPreperationHouseKeepingMarksMarks2 = "0";
        }

        mFoodPreparationHouseKeepingMarksINT = Integer.valueOf(mFoodPreperationHouseKeepingMarksMarks1) +Integer.valueOf(mFoodPreperationHouseKeepingMarksMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationHouseKeepingMarksINT).toString();
        setMarkViewTwo(mTextViewMarksHouseKeeping, mStringMarks);

        setTotalMarks();
    }

    private void cleanlinessMarks() {
        if (mCheckBoxCleanlinessAdequate.isChecked()) {
            mFoodPreperationCleanlinessMarks1 = "1";
        } else {
            mFoodPreperationCleanlinessMarks1 = "0";
        }
        if (mCheckBoxCleanlinessAppropriate.isChecked()) {
            mFoodPreperationCleanlinessMarks2 = "1";
        } else {
            mFoodPreperationCleanlinessMarks2 = "0";
        }

        mFoodPreparationCleanlinessMarksINT =Integer.valueOf(mFoodPreperationCleanlinessMarks1) +Integer.valueOf(mFoodPreperationCleanlinessMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationCleanlinessMarksINT).toString();
        setMarkViewTwo(mTextViewMarksCleanliness, mStringMarks);

        setTotalMarks();
    }

    private void lightVentilationMarks() {
        if (mCheckBoxLightsNVentilationAdequate.isChecked()) {
            mFoodPreperationLightAndVentilationMarks1 = "1";
        } else {
            mFoodPreperationLightAndVentilationMarks1 = "0";
        }
        if (mCheckBoxLightsNVentilationAppropriate.isChecked()) {
            mFoodPreperationLightAndVentilationMarks2 = "1";
        } else {
            mFoodPreperationLightAndVentilationMarks2 = "0";
        }

        mFoodPreparationLightsAndVentilationMarksINT =Integer.valueOf(mFoodPreperationLightAndVentilationMarks1) +Integer.valueOf(mFoodPreperationLightAndVentilationMarks2);
        String mStringMarks = Integer.valueOf(mFoodPreparationLightsAndVentilationMarksINT).toString();
        setMarkViewTwo(mTextViewMarksLightsNVentilation, mStringMarks);

        setTotalMarks();
    }

    private void contaminationMarks() {
        if (mCheckBoxFoodProcessingAreaContaminationFromGarbage.isChecked()) {
            mFoodPreperationContaminationMarks1 = "1";
        } else {
            mFoodPreperationContaminationMarks1 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromHazzards.isChecked()) {
            mFoodPreperationContaminationMarks2 = "1";
        } else {
            mFoodPreperationContaminationMarks2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromSanitires.isChecked()) {
            mFoodPreperationContaminationMarks3 = "1";
        } else {
            mFoodPreperationContaminationMarks3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaContaminationFromToilets.isChecked()) {
            mFoodPreperationContaminationMarks4 = "1";
        } else {
            mFoodPreperationContaminationMarks4 = "0";
        }
        mFoodPreparationContaminationMarksINT = Integer.valueOf(mFoodPreperationContaminationMarks1) +Integer.valueOf(mFoodPreperationContaminationMarks2)+
                Integer.valueOf(mFoodPreperationContaminationMarks3) + Integer.valueOf(mFoodPreperationContaminationMarks4);
        String mStringMarks = Integer.valueOf(mFoodPreparationContaminationMarksINT).toString();
        setMarkViewFour(mTextViewMarksFoodProcessingAreaContamination, mStringMarks);

        setTotalMarks();
    }

    private void wallMaintananceMarks() {
        if (mCheckBoxFoodProcessingAreaWallMaintananceNoContamination.isChecked()) {
            mFoodPreperationWallMaintanance1 = "1";
        } else {
            mFoodPreperationWallMaintanance1 = "0";
        }

        if (mCheckBoxFoodProcessingAreaWallMaintananceNoAccumilation.isChecked()) {
            mFoodPreperationWallMaintanance2 = "1";
        } else {
            mFoodPreperationWallMaintanance2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaWallMaintananceClean.isChecked()) {
            mFoodPreperationWallMaintanance3 = "1";
        } else {
            mFoodPreperationWallMaintanance3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaWallMaintananceSuitable.isChecked()) {
            mFoodPreperationWallMaintanance4 = "1";
        } else {
            mFoodPreperationWallMaintanance4 = "0";
        }

        mFoodPreparationWallStructureMarksINT = Integer.valueOf(mFoodPreperationWallMaintanance1) +Integer.valueOf(mFoodPreperationWallMaintanance2)+
                Integer.valueOf(mFoodPreperationWallMaintanance3) + Integer.valueOf(mFoodPreperationWallMaintanance4);
        String mStringMarks = Integer.valueOf(mFoodPreparationWallStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksFoodProcessingAreaWallMaintanance, mStringMarks);

        setTotalMarks();
    }

    private void ceilingStructureMarks() {
        if (mCheckBoxFoodProcessingAreaCelingStructureClean.isChecked()) {
            mFoodPreperationCeilingStructureMarks1 = "1";
        } else {
            mFoodPreperationCeilingStructureMarks1 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureNoAccumilation.isChecked()) {
            mFoodPreperationCeilingStructureMarks2 = "1";
        } else {
            mFoodPreperationCeilingStructureMarks2 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureSuitable.isChecked()) {
            mFoodPreperationCeilingStructureMarks3 = "1";
        } else {
            mFoodPreperationCeilingStructureMarks3 = "0";
        }

        if (mCheckBoxFoodProcessingAreaCelingStructureNoContamination.isChecked()) {
            mFoodPreperationCeilingStructureMarks4 = "1";
        } else {
            mFoodPreperationCeilingStructureMarks4 = "0";
        }

        mFoodPreparationCeilingStructureMarksINT = Integer.valueOf(mFoodPreperationCeilingStructureMarks1) +Integer.valueOf(mFoodPreperationCeilingStructureMarks2)+
                Integer.valueOf(mFoodPreperationCeilingStructureMarks3) + Integer.valueOf(mFoodPreperationCeilingStructureMarks4);
        String mStringMarks = Integer.valueOf(mFoodPreparationCeilingStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksFoodProcessingAreaCelingStructure, mStringMarks);

        setTotalMarks();
    }

    private void floorSturctureMarks() {
        if (mCheckBoxFoodProcessingAreaFloorStructureClean.isChecked()) {
            mFoodPreperationFloorStructureMarks1 = "1";
        } else {
            mFoodPreperationFloorStructureMarks1 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoAccumilation.isChecked()) {
            mFoodPreperationFloorStructureMarks2 = "1";
        } else {
            mFoodPreperationFloorStructureMarks2 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureNoContamination.isChecked()) {
            mFoodPreperationFloorStructureMarks3 = "1";
        } else {
            mFoodPreperationFloorStructureMarks3 = "0";
        }
        if (mCheckBoxFoodProcessingAreaFloorStructureSuitable.isChecked()) {
            mFoodPreperationFloorStructureMarks4 = "1";
        } else {
            mFoodPreperationFloorStructureMarks4 = "0";
        }

        mFoodPreparationFloorStructureMarksINT = Integer.valueOf(mFoodPreperationFloorStructureMarks1) +Integer.valueOf(mFoodPreperationFloorStructureMarks2)+
                Integer.valueOf(mFoodPreperationFloorStructureMarks3) + Integer.valueOf(mFoodPreperationFloorStructureMarks4);
        String mStringMarks = Integer.valueOf(mFoodPreparationFloorStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksFoodProcessingAreaFloorStructure, mStringMarks);

        setTotalMarks();
    }

    private void setTotalMarks()
    {
        mFoodPreparationFullMarksINT = mFoodPreparationFloorStructureMarksINT + mFoodPreparationCeilingStructureMarksINT +
                mFoodPreparationWallStructureMarksINT + mFoodPreparationContaminationMarksINT
                + mFoodPreparationSanitationMarksINT + mFoodPreparationLightsAndVentilationMarksINT
                +mFoodPreparationCleanlinessMarksINT + mFoodPreparationHouseKeepingMarksINT
                +mFoodPreparationSpaceMarksINT + mFoodPreparationPestControlMarksINT
                + mFoodPreparationCleaningMarksINT +mFoodPreparationDrainageMarksINT ;

        String mFoodPreparationFullMarks = Integer.valueOf(mFoodPreparationFullMarksINT).toString();
        mTextViewFoodProcessingAreaFullMarks.setText(mFoodPreparationFullMarks);

        FoodPreparationPrecent = (Float.valueOf(mFoodPreparationFullMarksINT)/36)*100;

        if(FoodPreparationPrecent >= 75){
            mFoodPreparationGrade = "A";
        }
        else if(FoodPreparationPrecent >= 65){
            mFoodPreparationGrade = "B";
        }
        else if(FoodPreparationPrecent >=50){
            mFoodPreparationGrade = "C";
        }
        else if(FoodPreparationPrecent >=35){
            mFoodPreparationGrade = "S";
        }
        else{
            mFoodPreparationGrade = "F";
        }

    }
}
