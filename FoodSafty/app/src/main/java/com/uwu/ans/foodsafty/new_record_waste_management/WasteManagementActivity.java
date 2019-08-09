package com.uwu.ans.foodsafty.new_record_waste_management;

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
import com.uwu.ans.foodsafty.new_record_saniations.SanitationActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WasteManagementActivity extends AppCompatActivity {

    /*Dustbins*/
    @BindView(R.id.marks_dustbin)
    TextView mTextViewMarksDustbins;
    @BindView(R.id.dustbin_enough)
    CheckBox mCheckBoxDustbinsEnough;
    @BindView(R.id.dustbin_clean)
    CheckBox mCheckBoxDustbinsClean;
    @BindView(R.id.dustbin_safe)
    CheckBox mCheckBoxDustbinsSafe;
    @BindView(R.id.dustbin_hyginic)
    CheckBox mCheckBoxDustbinsHyginic;
    int dusbins;

    /*DustbinsWithLids*/
    @BindView(R.id.marks_dustbin_with_lids)
    TextView mTextViewMarksDustbinsWithLids;
    @BindView(R.id.dustbin_with_lids_enough)
    CheckBox mCheckBoxDustbinsWithLidsEnough;
    @BindView(R.id.dustbin_with_lids_have)
    CheckBox mCheckBoxDustbinsWithLidsHave;
    int dusbilWithLids;

    /*DustbinsMaintenance*/
    @BindView(R.id.marks_dustbin_maintenance)
    TextView mTextViewMarksDustbinsMaintenance;
    @BindView(R.id.dustbin_maintenance_hygienic)
    CheckBox mCheckBoxDustbinsMaintenanceHyginic;
    @BindView(R.id.dustbin_maintenance_proper)
    CheckBox mCheckBoxDustbinsMaintenancePropper;
    int dustbinsMaintanance;

    /*WasteClassification*/
    @BindView(R.id.marks_waste_classification)
    TextView mTextViewMarksWasteClassification;
    @BindView(R.id.waste_classification_3r)
    CheckBox mCheckBoxWasteClassification3R;
    @BindView(R.id.waste_classification_signs)
    CheckBox mCheckBoxWasteClassificationSigns;
    @BindView(R.id.waste_classification_have)
    CheckBox mCheckBoxWasteClassificationHave;
    @BindView(R.id.waste_classification_classified)
    CheckBox mCheckBoxWasteClassificationClassified;
    int WasteClassification;

    /*WasteRemoving*/
    @BindView(R.id.marks_waste_removing)
    TextView mTextViewMarksWasteRemoving;
    @BindView(R.id.waste_removing_not_lying_in_vicinity)
    CheckBox mCheckBoxWasteRemovingNotLying;
    @BindView(R.id.waste_removing_enough_vents_exhausts)
    CheckBox mCheckBoxWasteRemovingEnough;
    @BindView(R.id.waste_removing_safe)
    CheckBox mCheckBoxWasteRemovingSafe;
    @BindView(R.id.waste_removing_hygienic)
    CheckBox mCheckBoxWasteRemovingHyginic;
    int WasteRemoving;

    /*ToiletPit*/
    @BindView(R.id.marks_toilet_pit)
    TextView mTextViewMarksToiletPit;
    @BindView(R.id.toilet_pit_appropriate)
    CheckBox mCheckBoxToiletPitApproprirate;
    @BindView(R.id.toilet_pit_adequate)
    CheckBox mCheckBoxToiletPitAdequate;
    int ToiletPit;

    /*Toilets*/
    @BindView(R.id.marks_toilets)
    TextView mTextViewMarksToilets;
    @BindView(R.id.toilets_enough)
    CheckBox mCheckBoxToiletsEnough;
    @BindView(R.id.toilets_hygienic)
    CheckBox mCheckBoxToiletsHyginic;
    @BindView(R.id.toilets_safe)
    CheckBox mCheckBoxToiletsSafe;
    @BindView(R.id.toilets_clean)
    CheckBox mCheckBoxToiletsClean;
    int Toilets;

    /*DrainageSystem*/
    @BindView(R.id.marks_drainage_system_waste_m)
    TextView mTextViewMarksDrainageSystem;
    @BindView(R.id.drainage_system_waste_m_separated)
    CheckBox mCheckBoxDrainageSystemSeperated;
    @BindView(R.id.drainage_system_waste_m_adequate)
    CheckBox mCheckBoxDrainageSystemAdequate;
    @BindView(R.id.drainage_system_waste_m_appropriate)
    CheckBox mCheckBoxDrainageSystemAppropriate;
    @BindView(R.id.drainage_system_waste_m_no_accumulation)
    CheckBox mCheckBoxDrainageSystemNoAccumilation;
    int DrainageSystem;

/*    @BindView(R.id.waste_management_comments)
    EditText mEditTextComments;*/

    @BindView(R.id.waste_management_total_marks)
    TextView mTextViewTotal;

    int TotalMarksINT = 0;

    double WasteManagementPrecent = 0;
    String WasteManagementGrade = "F";

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
        setContentView(R.layout.activity_waste_management);


        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);



        mCheckBoxDrainageSystemSeperated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDrainageSystemSeperated, DrainageSystem, mTextViewMarksDrainageSystem);
            }
        });
        mCheckBoxDrainageSystemAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDrainageSystemAdequate, DrainageSystem, mTextViewMarksDrainageSystem);
            }
        });
        mCheckBoxDrainageSystemAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDrainageSystemAppropriate, DrainageSystem, mTextViewMarksDrainageSystem);
            }
        });
        mCheckBoxDrainageSystemNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDrainageSystemNoAccumilation, DrainageSystem, mTextViewMarksDrainageSystem);
            }
        });


        mCheckBoxToiletsEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxToiletsEnough, Toilets, mTextViewMarksToilets);
            }
        });
        mCheckBoxToiletsHyginic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxToiletsHyginic, Toilets, mTextViewMarksToilets);
            }
        });
        mCheckBoxToiletsSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxToiletsSafe, Toilets, mTextViewMarksToilets);
            }
        });
        mCheckBoxToiletsClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxToiletsClean, Toilets, mTextViewMarksToilets);
            }
        });




        mCheckBoxToiletPitApproprirate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxToiletPitApproprirate, ToiletPit, mTextViewMarksToiletPit);
            }
        });
        mCheckBoxToiletPitAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxToiletPitAdequate, ToiletPit, mTextViewMarksToiletPit);
            }
        });




        mCheckBoxWasteRemovingNotLying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteRemovingNotLying, WasteRemoving, mTextViewMarksWasteRemoving);
            }
        });
        mCheckBoxWasteRemovingEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteRemovingEnough, WasteRemoving, mTextViewMarksWasteRemoving);
            }
        });
        mCheckBoxWasteRemovingSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteRemovingSafe, WasteRemoving, mTextViewMarksWasteRemoving);
            }
        });
        mCheckBoxWasteRemovingHyginic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteRemovingHyginic, WasteRemoving, mTextViewMarksWasteRemoving);
            }
        });





        mCheckBoxWasteClassification3R.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteClassification3R, WasteClassification, mTextViewMarksWasteClassification);
            }
        });
        mCheckBoxWasteClassificationSigns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteClassificationSigns, WasteClassification, mTextViewMarksWasteClassification);
            }
        });
        mCheckBoxWasteClassificationHave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteClassificationHave, WasteClassification, mTextViewMarksWasteClassification);
            }
        });
        mCheckBoxWasteClassificationClassified.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWasteClassificationClassified, WasteClassification, mTextViewMarksWasteClassification);
            }
        });





        mCheckBoxDustbinsMaintenanceHyginic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDustbinsMaintenanceHyginic, dustbinsMaintanance, mTextViewMarksDustbinsMaintenance);
            }
        });
        mCheckBoxDustbinsMaintenancePropper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDustbinsMaintenancePropper, dustbinsMaintanance, mTextViewMarksDustbinsMaintenance);
            }
        });



        mCheckBoxDustbinsWithLidsEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDustbinsWithLidsEnough, dusbilWithLids, mTextViewMarksDustbinsWithLids);
            }
        });
        mCheckBoxDustbinsWithLidsHave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDustbinsWithLidsHave, dusbilWithLids, mTextViewMarksDustbinsWithLids);
            }
        });



        mCheckBoxDustbinsEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDustbinsEnough, dusbins, mTextViewMarksDustbins);
            }
        });
        mCheckBoxDustbinsClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDustbinsClean, dusbins, mTextViewMarksDustbins);
            }
        });
        mCheckBoxDustbinsSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDustbinsSafe, dusbins, mTextViewMarksDustbins);
            }
        });
        mCheckBoxDustbinsHyginic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxDustbinsHyginic, dusbins, mTextViewMarksDustbins);
            }
        });
    }

    @OnClick(R.id.waste_management_next)
    public void nextWasteManagement(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("wasteManagementMarks").setValue(WasteManagementPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Waste Management Grade : " +WasteManagementGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(WasteManagementActivity.this,SanitationActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 3000);

                }
            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = dusbins + dusbilWithLids + dustbinsMaintanance + WasteClassification
                + WasteRemoving + ToiletPit + Toilets+ DrainageSystem;

        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        WasteManagementPrecent = (Float.valueOf(TotalMarksINT) / 26) * 100;


        if (WasteManagementPrecent >= 75) {
            WasteManagementGrade = "A";
        } else if (WasteManagementPrecent >= 65) {
            WasteManagementGrade = "B";
        } else if (WasteManagementPrecent >= 50) {
            WasteManagementGrade = "C";
        } else if (WasteManagementPrecent >= 35) {
            WasteManagementGrade = "S";
        } else {
            WasteManagementGrade = "F";
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
