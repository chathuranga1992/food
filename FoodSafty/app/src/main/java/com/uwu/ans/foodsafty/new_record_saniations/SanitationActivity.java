package com.uwu.ans.foodsafty.new_record_saniations;

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
import com.uwu.ans.foodsafty.new_record_food_handlers.FoodHandlersActivity;
import com.uwu.ans.foodsafty.new_record_packaging_materials.PackagingMaterialActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SanitationActivity extends AppCompatActivity {
//    sanitation_next


    /*WashBins*/
    @BindView(R.id.marks_wash_bins)
    TextView mTextViewMarksWashBins;
    @BindView(R.id.wash_bins_separated_kitchen_hand_washing)
    CheckBox mCheckBoxWashBinsSeperate;
    @BindView(R.id.wash_bins_with_disinfectants)
    CheckBox mCheckBoxWashBinsWithDisinfection;
    @BindView(R.id.wash_bins_clean)
    CheckBox mCheckBoxWashBinsClean;
    @BindView(R.id.wash_bins_enough)
    CheckBox mCheckBoxWashBinsEnough;
    int WashBins;

    /*CleaningEquipments*/
    @BindView(R.id.marks_cleaning_equipment)
    TextView mTextViewMarksCleaningEquipments;
    @BindView(R.id.cleaning_equipment_suitable_clean_easiness_l)
    CheckBox mCheckBoxCleaningEquipmentsSuitable;
    @BindView(R.id.cleaning_equipment_separated_food_contact_nonfood_contact_surfaces)
    CheckBox mCheckBoxCleaningEquipmentsSeperated;
    @BindView(R.id.cleaning_equipment_enough)
    CheckBox mCheckBoxCleaningEquipmentsEnough;
    @BindView(R.id.cleaning_equipment_safe)
    CheckBox mCheckBoxCleaningEquipmentsSafe;
    int CleaningEquipments;

    /*SanitationProcedure*/
    @BindView(R.id.marks_sanitation_procedure)
    TextView mTextViewMarksSanitationProcedure;
    @BindView(R.id.sanitation_procedure_safe)
    CheckBox mCheckBoxSanitationProcedureSafe;
    @BindView(R.id.sanitation_procedure_suitable)
    CheckBox mCheckBoxSanitationProcedureSuitable;
    @BindView(R.id.sanitation_procedure_adequate)
    CheckBox mCheckBoxSanitationProcedureAdequate;
    @BindView(R.id.sanitation_procedure_no_contamination)
    CheckBox mCheckBoxSanitationProcedureNoContamination;
    int SanitationProcedure;

    /*Refrigeration*/
    @BindView(R.id.marks_refrigeration)
    TextView mTextViewMarksRefrigeration;
    @BindView(R.id.refrigeration_accurate_thermometers)
    CheckBox mCheckBoxRefrigerationAccureteThermometers;
    @BindView(R.id.refrigeration_fifo_overstock)
    CheckBox mCheckBoxRefrigerationFIFOOverstock;
    @BindView(R.id.refrigeration_clean)
    CheckBox mCheckBoxRefrigerationClean;
    @BindView(R.id.refrigeration_no_contamination)
    CheckBox mCheckBoxRefrigerationNoContamination;
    int Refrigeration;

    /*StorageFacility*/
    @BindView(R.id.marks_storage_facilities)
    TextView mTextViewMarksStorageFacility;
    @BindView(R.id.storage_facilities_adequate)
    CheckBox mCheckBoxStorageFacilityAdequate;
    @BindView(R.id.storage_facilities_appropriate)
    CheckBox mCheckBoxStorageFacilityAppropriate;
    int StorageFacility;

    /*LightsNVentilation*/
    @BindView(R.id.marks_light_amp_ventilation)
    TextView mTextViewMarksLightsNVentilation;
    @BindView(R.id.light_amp_ventilation_adequate)
    CheckBox mCheckBoxLightsNVentilationAdequate;
    @BindView(R.id.light_amp_ventilation_appropriate)
    CheckBox mCheckBoxLightsNVentilationAppropriate;
    int LightsNVentilation;

    /*frozen*/
    @BindView(R.id.marks_frozen)
    TextView mTextViewMarksFrozen;
    @BindView(R.id.frozen_accurate_thermometers)
    CheckBox mCheckBoxFrozenThermometers;
    @BindView(R.id.frozen_fifo_overstock)
    CheckBox mCheckBoxFrozenFIFO;
    @BindView(R.id.frozen_no_contamination)
    CheckBox mCheckBoxFrozenNoContamination;
    @BindView(R.id.frozen_clean)
    CheckBox mCheckBoxFrozenClean;
    int frozen;

    /*contamination*/
    @BindView(R.id.marks_contamination)
    TextView mTextViewMarksContamination;
    @BindView(R.id.contamination_from_toilets)
    CheckBox mCheckBoxContaminationlFromToiletd;
    @BindView(R.id.contamination_from_garbage)
    CheckBox mCheckBoxContaminationFromGarbage;
    @BindView(R.id.contamination_from_sanitizers)
    CheckBox mCheckBoxContaminationFromSanitation;
    @BindView(R.id.contamination_from_hazards)
    CheckBox mCheckBoxContaminationFromHazads;
    int contamination;

    /*cleaning*/
    @BindView(R.id.marks_cleaning)
    TextView mTextViewMarksCleaning;
    @BindView(R.id.cleaning_appropriate)
    CheckBox mCheckBoxCleaningAppropriate;
    @BindView(R.id.cleaning_adequate)
    CheckBox mCheckBoxCleaningAdequate;
    @BindView(R.id.cleaning_daily)
    CheckBox mCheckBoxCleaningDaily;
    @BindView(R.id.cleaning_no_accumulation)
    CheckBox mCheckBoxCleaningNoAccumilation;
    int cleaning;


    /*pest_control*/
    @BindView(R.id.marks_pest_control)
    TextView mTextViewMarksPestControl;
    @BindView(R.id.pest_control_no_rodents)
    CheckBox mCheckBoxPestControlNoRodents;
    @BindView(R.id.pest_control_no_pets)
    CheckBox mCheckBoxPestControlNoPests;
    int pest_control;

/*    @BindView(R.id.sanitation_comments)
    EditText mEditTextComments;*/

    @BindView(R.id.sanitation_total_marks)
    TextView mTextViewTotal;

    int TotalMarksINT = 0;

    double SanitationPrecent = 0;
    String SanitationlGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;
    String RestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sanitation);

        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxPestControlNoRodents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxPestControlNoRodents, pest_control, mTextViewMarksPestControl);
            }
        });
        mCheckBoxPestControlNoPests.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxPestControlNoPests, pest_control, mTextViewMarksPestControl);
            }
        });


        mCheckBoxCleaningAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningAppropriate, cleaning, mTextViewMarksCleaning);
            }
        });
        mCheckBoxCleaningAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningAdequate, cleaning, mTextViewMarksCleaning);
            }
        });
        mCheckBoxCleaningDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningDaily, cleaning, mTextViewMarksCleaning);
            }
        });
        mCheckBoxCleaningNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningNoAccumilation, cleaning, mTextViewMarksCleaning);
            }
        });



        mCheckBoxContaminationlFromToiletd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminationlFromToiletd, contamination, mTextViewMarksContamination);
            }
        });
        mCheckBoxContaminationFromGarbage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminationFromGarbage, contamination, mTextViewMarksContamination);
            }
        });
        mCheckBoxContaminationFromSanitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminationFromSanitation, contamination, mTextViewMarksContamination);
            }
        });
        mCheckBoxContaminationFromHazads.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminationFromHazads, contamination, mTextViewMarksContamination);
            }
        });



        mCheckBoxFrozenThermometers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxFrozenThermometers, frozen, mTextViewMarksFrozen);
            }
        });
        mCheckBoxFrozenFIFO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxFrozenFIFO, frozen, mTextViewMarksFrozen);
            }
        });

        mCheckBoxFrozenNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxFrozenNoContamination, frozen, mTextViewMarksFrozen);
            }
        });
        mCheckBoxFrozenClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxFrozenClean, frozen, mTextViewMarksFrozen);
            }
        });




        mCheckBoxLightsNVentilationAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxLightsNVentilationAdequate, LightsNVentilation, mTextViewMarksLightsNVentilation);
            }
        });
        mCheckBoxLightsNVentilationAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxLightsNVentilationAppropriate, LightsNVentilation, mTextViewMarksLightsNVentilation);
            }
        });




        mCheckBoxStorageFacilityAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxStorageFacilityAdequate, StorageFacility, mTextViewMarksStorageFacility);
            }
        });
        mCheckBoxStorageFacilityAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxStorageFacilityAppropriate, StorageFacility, mTextViewMarksStorageFacility);
            }
        });


        mCheckBoxRefrigerationAccureteThermometers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRefrigerationAccureteThermometers, Refrigeration, mTextViewMarksRefrigeration);
            }
        });
        mCheckBoxRefrigerationFIFOOverstock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRefrigerationFIFOOverstock, Refrigeration, mTextViewMarksRefrigeration);
            }
        });
        mCheckBoxRefrigerationClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRefrigerationClean, Refrigeration, mTextViewMarksRefrigeration);
            }
        });
        mCheckBoxRefrigerationNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRefrigerationNoContamination, Refrigeration, mTextViewMarksRefrigeration);
            }
        });


        mCheckBoxSanitationProcedureSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxSanitationProcedureSafe, SanitationProcedure, mTextViewMarksSanitationProcedure);
            }
        });
        mCheckBoxSanitationProcedureSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxSanitationProcedureSuitable, SanitationProcedure, mTextViewMarksSanitationProcedure);
            }
        });
        mCheckBoxSanitationProcedureAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxSanitationProcedureAdequate, SanitationProcedure, mTextViewMarksSanitationProcedure);
            }
        });
        mCheckBoxSanitationProcedureNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxSanitationProcedureNoContamination, SanitationProcedure, mTextViewMarksSanitationProcedure);
            }
        });



        mCheckBoxCleaningEquipmentsSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningEquipmentsSuitable, CleaningEquipments, mTextViewMarksCleaningEquipments);
            }
        });
        mCheckBoxCleaningEquipmentsSeperated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningEquipmentsSeperated, CleaningEquipments, mTextViewMarksCleaningEquipments);
            }
        });
        mCheckBoxCleaningEquipmentsEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningEquipmentsEnough, CleaningEquipments, mTextViewMarksCleaningEquipments);
            }
        });
        mCheckBoxCleaningEquipmentsSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCleaningEquipmentsSafe, CleaningEquipments, mTextViewMarksCleaningEquipments);
            }
        });






        mCheckBoxWashBinsSeperate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWashBinsSeperate, WashBins, mTextViewMarksWashBins);
            }
        });
        mCheckBoxWashBinsWithDisinfection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWashBinsWithDisinfection, WashBins, mTextViewMarksWashBins);
            }
        });
        mCheckBoxWashBinsClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWashBinsClean, WashBins, mTextViewMarksWashBins);
            }
        });
        mCheckBoxWashBinsEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWashBinsEnough, WashBins, mTextViewMarksWashBins);
            }
        });
        
    }


    @OnClick(R.id.sanitation_next)
    public void nextSanitation(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("sanitationMarks").setValue(SanitationPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Sanitation Procedure Grade : " +SanitationlGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SanitationActivity.this,ResultActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = WashBins + CleaningEquipments + SanitationProcedure + Refrigeration + StorageFacility +
                LightsNVentilation + frozen + contamination  + cleaning + pest_control ;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        SanitationPrecent = (Float.valueOf(TotalMarksINT) / 34) * 100;


        if (SanitationPrecent >= 75) {
            SanitationlGrade = "A";
        } else if (SanitationPrecent >= 65) {
            SanitationlGrade = "B";
        } else if (SanitationPrecent >= 50) {
            SanitationlGrade = "C";
        } else if (SanitationPrecent >= 35) {
            SanitationlGrade = "S";
        } else {
            SanitationlGrade = "F";
        }
    }
    private void processCheckBoxFour(CheckBox checkBox, int marks, TextView textView) {
        if (checkBox.isChecked()) {
            marks = marks + 1;
            String mStringMarks = Integer.valueOf(marks).toString();
            setMarkViewFour(textView, mStringMarks);
            setTotalMarks();
        } else {
            marks = marks - 1;
            String mStringMarks = Integer.valueOf(marks).toString();
            setMarkViewFour(textView, mStringMarks);
            setTotalMarks();
        }
    }

    private void processCheckBoxTwo(CheckBox checkBox, int marks, TextView textView) {
        if (checkBox.isChecked()) {
            marks = marks + 1;
            String mStringMarks = Integer.valueOf(marks).toString();
            setMarkViewTwo(textView, mStringMarks);
            setTotalMarks();
        } else {
            marks = marks - 1;
            String mStringMarks = Integer.valueOf(marks).toString();
            setMarkViewTwo(textView, mStringMarks);
            setTotalMarks();
        }
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
        if (mMarks.equals("0")) {
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
        } else if (mMarks.equals("1")) {
            mTextView.setTextColor(Color.YELLOW);
            mTextView.setText(getString(R.string.bearly_adequate));
        } else if (mMarks.equals("0") || mMarks.equals(null)) {
            mTextView.setTextColor(Color.RED);
            mTextView.setText(getString(R.string.inadequate));
        }
    }
}
