package com.uwu.ans.foodsafty.new_record_storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_building.BuildingActivity;
import com.uwu.ans.foodsafty.new_record_equipment_and_utensils.EquipmentsAndUtencilsActivity;
import com.uwu.ans.foodsafty.new_record_food_preperation.FoodPreperationActivity;

public class StorageActivity extends AppCompatActivity {
    
    public DatabaseReference mDatabaseFoodSafe;
    public ProgressDialog dialog;

    /*storage*/
    @BindView(R.id.marks_storage)
    TextView mTextViewMarksStorage;
    @BindView(R.id.storage_separated)
    CheckBox mCheckBoxStorageSeperated;
    @BindView(R.id.storage_fifo)
    CheckBox mCheckBoxStorageFIFO;
    @BindView(R.id.storage_perishable)
    CheckBox mCheckBoxStoragePerishable;
    @BindView(R.id.storage_nonperishable)
    CheckBox mCheckBoxStorageNonPerishable;

    /*storage separated*/
    @BindView(R.id.marks_storage_separated)
    TextView mTextViewMarksStorageSeperated;
    @BindView(R.id.storage_18_h_from_floor_level)
    CheckBox mCheckBoxStorage_18_h_from_floor_level;
    @BindView(R.id.storage_9_from_wall)
    CheckBox mCheckBoxStorage_9_from_wall;
    @BindView(R.id.storage_clean)
    CheckBox mCheckBoxStorageClean;
    @BindView(R.id.storage_proper)
    CheckBox mCheckBoxStorageProper;

    /*storage refrigeration*/
    @BindView(R.id.marks_storage_refrigeration)
    TextView mTextViewMarksStorageRefridgeration;
    @BindView(R.id.storage_refrigeration_accurate_thermometers)
    CheckBox mCheckBoxStorageRefridgerationAccurate_thermometers;
    @BindView(R.id.storage_refrigeration_fifo_overstock)
    CheckBox mCheckBoxStorageRefridgerationStorage_refrigeration_fifo_overstock;
    @BindView(R.id.storage_refrigeration_clean)
    CheckBox mCheckBoxStorageRefridgerationClean;
    @BindView(R.id.storage_refrigeration_no_contamination)
    CheckBox mCheckBoxStorageRefridgerationNoContamination;

    /*storage facilities*/
    @BindView(R.id.marks_storage_storage_facilities)
    TextView mTextViewMarksStorageFacilities;
    @BindView(R.id.storage_storage_facilities_adequate)
    CheckBox mCheckBoxStorageFacilitiesAdequate;
    @BindView(R.id.storage_storage_facilities_appropriate)
    CheckBox mCheckBoxStorageFacilitiesAppropriate;

    /*storage lights n ventilation*/
    @BindView(R.id.marks_storage_light_amp_ventilation)
    TextView mTextViewMarksStorageLightNVentilation;
    @BindView(R.id.storage_light_amp_ventilation_adequate)
    CheckBox mCheckBoxStorageLightNVentilationAdequate;
    @BindView(R.id.storage_light_amp_ventilation_appropriate)
    CheckBox mCheckBoxStorageLightNVentilationAppropriate;

    /*storage frozen*/
    @BindView(R.id.marks_storage_frozen)
    TextView mTextViewMarksStorageFrozen;
    @BindView(R.id.storage_frozen_accurate_thermometers)
    CheckBox mCheckBoxStorageFrozenAccurate_thermometers;
    @BindView(R.id.storage_frozen_fifo_overstock)
    CheckBox mCheckBoxStorageFrozennStorage_refrigeration_fifo_overstock;
    @BindView(R.id.storage_frozen_clean)
    CheckBox mCheckBoxStorageFrozenClean;
    @BindView(R.id.storage_frozen_no_contamination)
    CheckBox mCheckBoxStorageFrozenNoContamination;

    /*storage contamination*/
    @BindView(R.id.marks_storage_contamination)
    TextView mTextViewMarksStorageContamination;
    @BindView(R.id.storage_contamination_from_toilets)
    CheckBox mCheckBoxStorageContaminationFromToilets;
    @BindView(R.id.storage_contamination_from_garbage)
    CheckBox mCheckBoxStorageContaminationFromGarbage;
    @BindView(R.id.storage_contamination_from_sanitizers)
    CheckBox mCheckBoxStorageContaminationFromSanitires;
    @BindView(R.id.storage_contamination_from_hazards)
    CheckBox mCheckBoxStorageContaminationFromHzards;

    /*storage pest control*/
    @BindView(R.id.marks_storage_pest_control)
    TextView mTextViewMarksStoragePestControl;
    @BindView(R.id.storage_pest_control_no_rodents)
    CheckBox mCheckBoxStoragePestControlNoRodents;
    @BindView(R.id.storage_pest_control_no_pets)
    CheckBox mCheckBoxStoragePestControlNoPets;

    /*storage clean*/
    @BindView(R.id.marks_storage_cleaning)
    TextView mTextViewMarksStorageClean;
    @BindView(R.id.storage_clean_appropriate)
    CheckBox mCheckBoxStorageCleanAppropriate;
    @BindView(R.id.storage_clean_adequate)
    CheckBox mCheckBoxStorageCleanAdequate;
    @BindView(R.id.storage_clean_daily)
    CheckBox mCheckBoxStorageCleanDaily;
    @BindView(R.id.storage_clean_no_accumulation)
    CheckBox mCheckBoxStorageCleanNoAccumilation;

    @BindView(R.id.storage_comments)
    EditText mEditTextComments;

    @BindView(R.id.food_storage_full_marks)
    TextView mTextViewTotal;



    private int mStorageMarksINT = 0;
    private int mStorageLightAndVentilationMarksINT = 0;
    private int mStorageSeperatedMarksINT = 0;
    private int mStorageRefrigerationMarksINT = 0;
    private int mStorageStorageFacilitiesMarksINT = 0;
    private int mStorageFrozenMarksINT = 0;
    private int mStorageContaminationMarksINT = 0;
    private int mStoragePestControlMarksINT = 0;
    private int mStorageCleanMarksINT = 0;
    private int mStorageTotalMarksINT = 0;

    double StoragePrecent =0;
    String mStorageGrade ="F";
    String RestKey;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);


        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();

        RestKey = getIntent().getStringExtra("RestName");

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);


        mCheckBoxStorageSeperated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageSeperated.isChecked()) {
                    mStorageMarksINT = mStorageMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageMarksINT = mStorageMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStorageFIFO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFIFO.isChecked()) {
                    mStorageMarksINT = mStorageMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageMarksINT = mStorageMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStoragePerishable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStoragePerishable.isChecked()) {
                    mStorageMarksINT = mStorageMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageMarksINT = mStorageMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStorageNonPerishable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageNonPerishable.isChecked()) {
                    mStorageMarksINT = mStorageMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageMarksINT = mStorageMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
                    mTextViewMarksStorage.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });



        mCheckBoxStorageLightNVentilationAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageLightNVentilationAdequate.isChecked()) {
                    mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
                    mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
                    mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStorageLightNVentilationAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageLightNVentilationAppropriate.isChecked()) {
                    mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
                    mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
                    mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
                    setTotalMarks();
                }
            }
        });



        mCheckBoxStorage_18_h_from_floor_level.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorage_18_h_from_floor_level.isChecked()) {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStorageClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageClean.isChecked()) {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageProper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageProper.isChecked()) {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorage_9_from_wall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorage_9_from_wall.isChecked()) {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageSeperatedMarksINT = mStorageSeperatedMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
                    mTextViewMarksStorageSeperated.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStorageRefridgerationAccurate_thermometers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageRefridgerationAccurate_thermometers.isChecked()) {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageRefridgerationStorage_refrigeration_fifo_overstock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageRefridgerationStorage_refrigeration_fifo_overstock.isChecked()) {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageRefridgerationClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageRefridgerationClean.isChecked()) {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageRefridgerationNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageRefridgerationNoContamination.isChecked()) {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
                    mTextViewMarksStorageRefridgeration.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
                    setTotalMarks();
                }
            }
        });


        mCheckBoxStorageFrozenAccurate_thermometers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFrozenAccurate_thermometers.isChecked()) {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageFrozennStorage_refrigeration_fifo_overstock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFrozennStorage_refrigeration_fifo_overstock.isChecked()) {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageFrozenClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFrozenClean.isChecked()) {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageFrozenNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFrozenNoContamination.isChecked()) {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageFrozenMarksINT = mStorageFrozenMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
                    mTextViewMarksStorageFrozen.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
                    setTotalMarks();
                }
            }
        });


        mCheckBoxStorageFacilitiesAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFacilitiesAdequate.isChecked()) {
                    mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
                    mTextViewMarksStorageFacilities.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFacilities, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
                    mTextViewMarksStorageFacilities.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageFacilities, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageFacilitiesAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageFacilitiesAppropriate.isChecked()) {
                    mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
                    mTextViewMarksStorageFacilities.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageFacilities, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
                    mTextViewMarksStorageFacilities.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStorageFacilities, mStringMarks);
                    setTotalMarks();
                }
            }
        });


        mCheckBoxStorageCleanAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageCleanAppropriate.isChecked()) {
                    mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageCleanMarksINT = mStorageCleanMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageCleanAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageCleanAdequate.isChecked()) {
                    mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageCleanMarksINT = mStorageCleanMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageCleanDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageCleanDaily.isChecked()) {
                    mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageCleanMarksINT = mStorageCleanMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageCleanNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageCleanNoAccumilation.isChecked()) {
                    mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageCleanMarksINT = mStorageCleanMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
                    mTextViewMarksStorageClean.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
                    setTotalMarks();
                }
            }
        });



        mCheckBoxStorageContaminationFromToilets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageContaminationFromToilets.isChecked()) {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageContaminationFromGarbage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageContaminationFromGarbage.isChecked()) {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageContaminationFromSanitires.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageContaminationFromSanitires.isChecked()) {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStorageContaminationFromHzards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStorageContaminationFromHzards.isChecked()) {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();

                } else {
                    mStorageContaminationMarksINT = mStorageContaminationMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
                    mTextViewMarksStorageContamination.setText(mStringMarks);
                    setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStoragePestControlNoRodents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStoragePestControlNoRodents.isChecked()) {
                    mStoragePestControlMarksINT = mStoragePestControlMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
                    setMarkViewTwo(mTextViewMarksStoragePestControl, mStringMarks);
                    setTotalMarks();

                } else {
                    mStoragePestControlMarksINT = mStoragePestControlMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
                    mTextViewMarksStoragePestControl.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStoragePestControl, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStoragePestControlNoPets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxStoragePestControlNoPets.isChecked()) {
                    mStoragePestControlMarksINT = mStoragePestControlMarksINT + 1;
                    String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
                    mTextViewMarksStoragePestControl.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStoragePestControl, mStringMarks);
                    setTotalMarks();

                } else {
                    mStoragePestControlMarksINT = mStoragePestControlMarksINT - 1;
                    String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
                    mTextViewMarksStoragePestControl.setText(mStringMarks);
                    setMarkViewTwo(mTextViewMarksStoragePestControl, mStringMarks);
                    setTotalMarks();
                }
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
    
    @OnClick(R.id.foodStorage_next_btn)
    public void setStorage(View view) {

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("foodStorageMarks").setValue(StoragePrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Food Storage Grade : " + mStorageGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(StorageActivity.this,EquipmentsAndUtencilsActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 3000);

                }
            }
        });


    }


    private void setTotalMarks() {

        mStorageTotalMarksINT = mStorageMarksINT + mStorageLightAndVentilationMarksINT+mStorageCleanMarksINT+mStoragePestControlMarksINT+mStoragePestControlMarksINT+mStorageContaminationMarksINT+mStorageContaminationMarksINT+mStorageFrozenMarksINT+mStorageStorageFacilitiesMarksINT +mStorageSeperatedMarksINT+mStorageRefrigerationMarksINT ;
        String mBuildingTotalMarks = Integer.valueOf(mStorageTotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        StoragePrecent = (Float.valueOf(mStorageTotalMarksINT)/22)*100;


        if(StoragePrecent >= 75){
            mStorageGrade = "A";
        }
        else if(StoragePrecent >= 65){
            mStorageGrade = "B";
        }
        else if(StoragePrecent >=50){
            mStorageGrade = "C";
        }
        else if(StoragePrecent >=35){
            mStorageGrade = "S";
        }
        else{
            mStorageGrade = "F";
        }
    }
}
