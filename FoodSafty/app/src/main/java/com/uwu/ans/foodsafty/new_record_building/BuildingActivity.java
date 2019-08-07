package com.uwu.ans.foodsafty.new_record_building;

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
import com.uwu.ans.foodsafty.new_record_building.domains.BuildingModel;
import com.uwu.ans.foodsafty.new_record_building.domains.CeilingStructure;
import com.uwu.ans.foodsafty.new_record_building.domains.FloorStructure;
import com.uwu.ans.foodsafty.new_record_building.domains.LightNVentilation;
import com.uwu.ans.foodsafty.new_record_building.domains.RiskFactors;
import com.uwu.ans.foodsafty.new_record_building.domains.Space;
import com.uwu.ans.foodsafty.new_record_building.domains.Structure;
import com.uwu.ans.foodsafty.new_record_building.domains.WallMaintatance;
import com.uwu.ans.foodsafty.new_record_food_preperation.FoodPreperationActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @BindView(R.id.building_total_marks)
    TextView mTextViewTotal;


    public DatabaseReference mDatabaseFoodSafe;
    public ProgressDialog dialog;
    private int mBuildingStructureMarksINT = 0;
    private int mBuildingLightAndVentilationMarksINT = 0;
    private int mBuildingWallmaintananceMarksINT = 0;
    private int mBuildingFloorStructureMarksINT = 0;
    private int mBuildingRiskFactorsMarksINT = 0;
    private int mBuildingCeilingStructureMarksINT = 0;
    private int mBuildingSpaceMarksINT = 0;
    private int mBuildingTotalMarksINT = 0;

    String mBuildingStructureMarks1;
    String mBuildingStructureMarks2;
    String mBuildingStructureMarks3;
    String mBuildingStructureMarks4;
    String mBuildingWallMaintanance1, mBuildingWallMaintanance2, mBuildingWallMaintanance3, mBuildingWallMaintanance4;
    String mBuildingLightAndVentilationMarks1, mBuildingLightAndVentilationMarks2;
    String mBuildingRiskFactorsMarks1, mBuildingRiskFactorsMarks2;
    String mBuildingFoorStructureMarks1, mBuildingFoorStructureMarks2, mBuildingFoorStructureMarks3, mBuildingFoorStructureMarks4;
    String mBuildingSpaceMarks1, mBuildingSpaceMarks2;
    String mBuildingCeilingStructureMarks1, mBuildingCeilingStructureMarks2, mBuildingCeilingStructureMarks3, mBuildingCeilingStructureMarks4;

    String mBuildingGrade = "F";

    double BuildingPrecent=0;

    String RestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_building);

        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();

        RestKey = getIntent().getStringExtra("RestName");


        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxBuildingStructureAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                structureMarks();
            }
        });

        mCheckBoxBuildingStructureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                structureMarks();
            }
        });

        mCheckBoxBuildingStructurePermenant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                structureMarks();
            }
        });

        mCheckBoxBuildingStructureSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                structureMarks();
            }
        });


        mCheckBoxBuildingLightandVentilationAttractive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightAndVentilationMarks();
            }
        });

        mCheckBoxBuildingLightandVentilationBeautiful.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightAndVentilationMarks();
            }
        });


        mCheckBoxBuildingSpaceAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spaceMarks();
            }
        });

        mCheckBoxBuildingSpaceAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spaceMarks();
            }
        });

        mCheckBoxBuildingWallMaintananceClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });

        mCheckBoxBuildingWallMaintananceNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });

        mCheckBoxBuildingWallMaintananceNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });

        mCheckBoxBuildingWallMaintananceSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wallMaintananceMarks();
            }
        });


        mCheckBoxBuildingRiskFactorsHazzards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                riskFactorsMarks();
            }
        });

        mCheckBoxBuildingRiskFactorsUnsafeStructures.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                riskFactorsMarks();
            }
        });


        mCheckBoxBuildingFloorStructureClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorStructureMarks();
            }
        });

        mCheckBoxBuildingFloorStructureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorStructureMarks();
            }
        });

        mCheckBoxBuildingFloorStructureNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorStructureMarks();
            }
        });

        mCheckBoxBuildingFloorStructureNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                floorStructureMarks();
            }
        });

        mCheckBoxBuildingCeilingStructureClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });

        mCheckBoxBuildingCeilingStructureNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });

        mCheckBoxBuildingCeilingStructureNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });

        mCheckBoxBuildingCeilingStructureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ceilingStructureMarks();
            }
        });
    }


    public void setMarkViewFour(TextView mTextView, String mMarks) {
        mTextView.setTextColor(Color.RED);
        mTextView.setText(getString(R.string.inadequate));

        if (mMarks.equals("4")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.good));
        }
        if (mMarks.equals("3")) {
            mTextView.setTextColor(Color.BLUE);
            mTextView.setText(getString(R.string.adequate));
        }
        if (mMarks.equals("2")) {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        }
        if(mMarks.equals("0")){
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }

    public void setMarkViewTwo(TextView mTextView, String mMarks) {

        mTextView.setTextColor(Color.RED);
        mTextView.setText(getString(R.string.inadequate));

        if (mMarks.equals("2")) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText(getString(R.string.adequate));
        } else if(mMarks.equals("1")){
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        }else if(mMarks.equals("0")||mMarks.equals(null)){
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }


    @OnClick(R.id.building_next)
    public void setBuilding(View view) {

        //String building_id = mDatabaseFoodSafe.push().getKey();

        structureMarks();

        lightAndVentilationMarks();

        spaceMarks();

        wallMaintananceMarks();

        riskFactorsMarks();

        floorStructureMarks();

        ceilingStructureMarks();


/*        String mComment = mEditTextComments.getText().toString();

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
        String mBuildingSpaceReMarks = mTextViewMarksBuildingSpace.getText().toString();*/


/*        FloorStructure floorStructure = new FloorStructure(mBuildingFoorStructureMarks1,
                mBuildingFoorStructureMarks2, mBuildingFoorStructureMarks3, mBuildingFoorStructureMarks4,
                mBuildingFloorStructureMarks, mBuildingFloorStructureReMarks);

        CeilingStructure ceilingStructure = new CeilingStructure(mBuildingCeilingStructureMarks1,
                mBuildingCeilingStructureMarks2,mBuildingCeilingStructureMarks3, mBuildingCeilingStructureMarks4,
                mBuildingCeilingStructuresMarks, mBuildingCeilingStructuresReMarks);

        LightNVentilation lightNVentilation = new LightNVentilation(mBuildingLightAndVentilationMarks1
                , mBuildingLightAndVentilationMarks2, mBuildingLightAndVentilationMarks, mBuildingLightAndVentilationReMarks);

        RiskFactors riskFactors = new RiskFactors(mBuildingRiskFactorsMarks1,
                mBuildingRiskFactorsMarks2, mBuildingRiskFactorsMarks, mBuildingRiskFactorsReMarks);

        Space space = new Space(mBuildingSpaceMarks1, mBuildingSpaceMarks2, mBuildingSpaceMarks, mBuildingSpaceReMarks);

        Structure structure = new Structure(mBuildingStructureMarks1,
                mBuildingStructureMarks3, mBuildingStructureMarks4,mBuildingStructureMarks2,
                mBuildingStructureMarks, mBuildingStructureReMarks);

        WallMaintatance wallMaintatance = new WallMaintatance(mBuildingWallMaintanance1,
                mBuildingWallMaintanance4,mBuildingWallMaintanance3,mBuildingWallMaintanance2,
                mBuildingWallmaintananceMarks, mBuildingWallmaintananceReMarks);


        String mBuildingTotalMarks = Integer.valueOf(mBuildingTotalMarksINT).toString();

        BuildingModel buildingModel = new BuildingModel(building_id, building_id,mComment,
                mBuildingTotalMarks, ceilingStructure, floorStructure, lightNVentilation,
                riskFactors, space, structure, wallMaintatance);*/



        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("buildingMarks").setValue(BuildingPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Building Grade : " +mBuildingGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(BuildingActivity.this,FoodPreperationActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }

    private void ceilingStructureMarks() {
        if (mCheckBoxBuildingCeilingStructureClean.isChecked()) {
            mBuildingCeilingStructureMarks1 = "1";
        } else {
            mBuildingCeilingStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureNoAccumilation.isChecked()) {
            mBuildingCeilingStructureMarks2 = "1";
        } else {
            mBuildingCeilingStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureNoContamination.isChecked()) {
            mBuildingCeilingStructureMarks3 = "1";
        } else {
            mBuildingCeilingStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingCeilingStructureSuitable.isChecked()) {
            mBuildingCeilingStructureMarks4 = "1";
        } else {
            mBuildingCeilingStructureMarks4 = "0";
        }

        mBuildingCeilingStructureMarksINT = Integer.valueOf(mBuildingCeilingStructureMarks1) +Integer.valueOf(mBuildingCeilingStructureMarks2)+
                Integer.valueOf(mBuildingCeilingStructureMarks3) + Integer.valueOf(mBuildingCeilingStructureMarks4);
        String mStringMarks = Integer.valueOf(mBuildingCeilingStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksBuildingCeilingStructure, mStringMarks);

        setTotalMarks();
    }

    private void floorStructureMarks() {
        if (mCheckBoxBuildingFloorStructureClean.isChecked()) {
            mBuildingFoorStructureMarks1 = "1";
        } else {
            mBuildingFoorStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingFloorStructureNoAccumilation.isChecked()) {
            mBuildingFoorStructureMarks2 = "1";
        } else {
            mBuildingFoorStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingFloorStructureNoContamination.isChecked()) {
            mBuildingFoorStructureMarks3 = "1";
        } else {
            mBuildingFoorStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingFloorStructureSuitable.isChecked()) {
            mBuildingFoorStructureMarks4 = "1";
        } else {
            mBuildingFoorStructureMarks4 = "0";
        }

        mBuildingFloorStructureMarksINT = Integer.valueOf(mBuildingFoorStructureMarks1) +Integer.valueOf(mBuildingFoorStructureMarks2)+
                Integer.valueOf(mBuildingFoorStructureMarks3) + Integer.valueOf(mBuildingFoorStructureMarks4);
        String mStringMarks = Integer.valueOf(mBuildingFloorStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksBuildingFloorStructure, mStringMarks);

        setTotalMarks();
    }

    private void riskFactorsMarks() {
        if (mCheckBoxBuildingRiskFactorsHazzards.isChecked()) {
            mBuildingRiskFactorsMarks1 = "1";
        } else {
            mBuildingRiskFactorsMarks1 = "0";
        }
        if (mCheckBoxBuildingRiskFactorsUnsafeStructures.isChecked()) {
            mBuildingRiskFactorsMarks2 = "1";
        } else {
            mBuildingRiskFactorsMarks2 = "0";
        }

        mBuildingRiskFactorsMarksINT = Integer.valueOf(mBuildingRiskFactorsMarks1) + Integer.valueOf(mBuildingRiskFactorsMarks2);
        String mStringMarks = Integer.valueOf(mBuildingRiskFactorsMarksINT).toString();
        setMarkViewTwo(mTextViewMarksBuildingRiskFactors, mStringMarks);

        setTotalMarks();

    }

    private void wallMaintananceMarks() {
        if (mCheckBoxBuildingWallMaintananceClean.isChecked()) {
            mBuildingWallMaintanance1 = "1";
        } else {
            mBuildingWallMaintanance1 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceSuitable.isChecked()) {
            mBuildingWallMaintanance2 = "1";
        } else {
            mBuildingWallMaintanance2 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceNoContamination.isChecked()) {
            mBuildingWallMaintanance3 = "1";
        } else {
            mBuildingWallMaintanance3 = "0";
        }
        if (mCheckBoxBuildingWallMaintananceNoAccumilation.isChecked()) {
            mBuildingWallMaintanance4 = "1";
        } else {
            mBuildingWallMaintanance4 = "0";
        }

        mBuildingWallmaintananceMarksINT = Integer.valueOf(mBuildingWallMaintanance1) +Integer.valueOf(mBuildingWallMaintanance2)+
                Integer.valueOf(mBuildingWallMaintanance3) + Integer.valueOf(mBuildingWallMaintanance4);
        String mStringMarks = Integer.valueOf(mBuildingWallmaintananceMarksINT).toString();
        setMarkViewFour(mTextViewMarksBuildingWallMaintanance, mStringMarks);

        setTotalMarks();
    }

    private void spaceMarks() {
        if (mCheckBoxBuildingSpaceAdequate.isChecked()) {
            mBuildingSpaceMarks1 = "1";
        } else {
            mBuildingSpaceMarks1 = "0";
        }
        if (mCheckBoxBuildingSpaceAppropriate.isChecked()) {
            mBuildingSpaceMarks2 = "1";
        } else {
            mBuildingSpaceMarks2 = "0";
        }

        mBuildingSpaceMarksINT = Integer.valueOf(mBuildingSpaceMarks1) + Integer.valueOf(mBuildingSpaceMarks2);
        String mStringMarks = Integer.valueOf(mBuildingSpaceMarksINT).toString();
        setMarkViewTwo(mTextViewMarksBuildingSpace, mStringMarks);

        setTotalMarks();
    }

    private void lightAndVentilationMarks() {
        if (mCheckBoxBuildingLightandVentilationAttractive.isChecked()) {
            mBuildingLightAndVentilationMarks1 = "1";
        } else {
            mBuildingLightAndVentilationMarks1 = "0";
        }
        if (mCheckBoxBuildingLightandVentilationBeautiful.isChecked()) {
            mBuildingLightAndVentilationMarks2 = "1";
        } else {
            mBuildingLightAndVentilationMarks2 = "0";
        }

        mBuildingLightAndVentilationMarksINT = Integer.valueOf(mBuildingLightAndVentilationMarks1) + Integer.valueOf(mBuildingLightAndVentilationMarks2);
        String mStringMarks = Integer.valueOf(mBuildingLightAndVentilationMarksINT).toString();
        setMarkViewTwo(mTextViewMarksBuildingLightandVentilation, mStringMarks);

        setTotalMarks();
    }

    private void structureMarks() {
        /*Structure*/
        if (mCheckBoxBuildingStructureAdequate.isChecked()) {
            mBuildingStructureMarks1 = "1";
        } else {
            mBuildingStructureMarks1 = "0";
        }
        if (mCheckBoxBuildingStructureSuitable.isChecked()) {
            mBuildingStructureMarks2 = "1";
        } else {
            mBuildingStructureMarks2 = "0";
        }
        if (mCheckBoxBuildingStructurePermenant.isChecked()) {
            mBuildingStructureMarks3 = "1";
        } else {
            mBuildingStructureMarks3 = "0";
        }
        if (mCheckBoxBuildingStructureSafe.isChecked()) {
            mBuildingStructureMarks4 = "1";
        } else {
            mBuildingStructureMarks4 = "0";
        }

        mBuildingStructureMarksINT = Integer.valueOf(mBuildingStructureMarks4) +Integer.valueOf(mBuildingStructureMarks3)+
                Integer.valueOf(mBuildingStructureMarks2) + Integer.valueOf(mBuildingStructureMarks1);
        String mStringMarks = Integer.valueOf(mBuildingStructureMarksINT).toString();
        setMarkViewFour(mTextViewMarksBuildingStructure, mStringMarks);

        setTotalMarks();
    }

    private void setTotalMarks() {
        mBuildingTotalMarksINT = mBuildingSpaceMarksINT+mBuildingCeilingStructureMarksINT+
                mBuildingRiskFactorsMarksINT+mBuildingFloorStructureMarksINT+mBuildingWallmaintananceMarksINT
                +mBuildingLightAndVentilationMarksINT+mBuildingStructureMarksINT;
        String mBuildingTotalMarks = Integer.valueOf(mBuildingTotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        BuildingPrecent = (Float.valueOf(mBuildingTotalMarksINT)/22)*100;


        if(BuildingPrecent >= 75){
            mBuildingGrade = "A";
        }
        else if(BuildingPrecent >= 65){
            mBuildingGrade = "B";
        }
        else if(BuildingPrecent>=50){
            mBuildingGrade = "C";
        }
        else if(BuildingPrecent>=35){
            mBuildingGrade = "S";
        }
        else{
            mBuildingGrade = "F";
        }
    }


}
