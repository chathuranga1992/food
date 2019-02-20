package com.uwu.ans.foodsafty.new_record_water_supply;

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
import com.uwu.ans.foodsafty.new_record_food_handlers.FoodHandlersActivity;
import com.uwu.ans.foodsafty.new_record_packaging_materials.PackagingMaterialActivity;
import com.uwu.ans.foodsafty.new_record_standerds.StanderdsActivity;
import com.uwu.ans.foodsafty.new_record_waste_management.WasteManagementActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaterActivity extends AppCompatActivity {

    /*WaterSource*/
    @BindView(R.id.marks_water_source)
    TextView mTextViewMarksWaterSource;
    @BindView(R.id.water_source_safe)
    CheckBox mCheckBoxWaterSourceSafe;
    @BindView(R.id.water_source_proper)
    CheckBox mCheckBoxWaterSourcePropper;
    @BindView(R.id.water_source_hygienic)
    CheckBox mCheckBoxWaterSourceHyginic;
    @BindView(R.id.water_source_no_contamination)
    CheckBox mCheckBoxWaterSourceNoContsminstion;
    int waterResource;

    /*WaterSupply*/
    @BindView(R.id.marks_water_supply)
    TextView mTextViewMarksWaterSupply;
    @BindView(R.id.water_supply_contamination_toilets_garbage)
    CheckBox mCheckBoxWaterSupplyContaminationToiletsGarbage;
    @BindView(R.id.water_supply_enough)
    CheckBox mCheckBoxWaterSupplyEnough;
    @BindView(R.id.water_supply_no_contamination)
    CheckBox mCheckBoxWaterSupplyNoContamination;
    @BindView(R.id.water_supply_separated)
    CheckBox mCheckBoxWaterSupplySeperated;
    int waterSupply;

    /*WaterSample*/
    @BindView(R.id.marks_water_sample_reports)
    TextView mTextViewMarksWaterSample;
    @BindView(R.id.water_sample_reports_bacterial)
    CheckBox mCheckBoxWaterSampleBacterial;
    @BindView(R.id.water_sample_reports_chemical)
    CheckBox mCheckBoxWaterSampleChemical;
    int waterSample;

/*    @BindView(R.id.water_supply_comment)
    EditText mEditTextComments;*/

    @BindView(R.id.water_supply_Total_marks)
    TextView mTextViewTotal;

    int TotalMarksINT = 0;
    String RestKey;
    double waterPrecent = 0;
    String waterGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxWaterSampleBacterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxWaterSampleBacterial, waterSample, mTextViewMarksWaterSample);
            }
        });
        mCheckBoxWaterSampleChemical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxWaterSampleChemical, waterSample, mTextViewMarksWaterSample);
            }
        });



        mCheckBoxWaterSupplyContaminationToiletsGarbage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSupplyContaminationToiletsGarbage, waterSupply, mTextViewMarksWaterSupply);
            }
        });
        mCheckBoxWaterSupplyEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSupplyEnough, waterSupply, mTextViewMarksWaterSupply);
            }
        });
        mCheckBoxWaterSupplyNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSupplyNoContamination, waterSupply, mTextViewMarksWaterSupply);
            }
        });
        mCheckBoxWaterSupplySeperated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSupplySeperated, waterSupply, mTextViewMarksWaterSupply);
            }
        });


        mCheckBoxWaterSourceSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSourceSafe, waterResource, mTextViewMarksWaterSource);
            }
        });
        mCheckBoxWaterSourcePropper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSourcePropper, waterResource, mTextViewMarksWaterSource);
            }
        });
        mCheckBoxWaterSourceHyginic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSourceHyginic, waterResource, mTextViewMarksWaterSource);
            }
        });
        mCheckBoxWaterSourceNoContsminstion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxWaterSourceNoContsminstion, waterResource, mTextViewMarksWaterSource);
            }
        });

    }
    @OnClick(R.id.water_supply_next)
    public void nextWater(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("waterMarks").setValue(waterPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Water Grade : " +waterGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(WaterActivity.this,StanderdsActivity.class);
//                            ResultActivity
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = waterResource + waterSupply + waterSample;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        waterPrecent = (Float.valueOf(TotalMarksINT) / 10) * 100;


        if (waterPrecent >= 75) {
            waterGrade = "A";
        } else if (waterPrecent >= 65) {
            waterGrade = "B";
        } else if (waterPrecent >= 50) {
            waterGrade = "C";
        } else if (waterPrecent >= 35) {
            waterGrade = "S";
        } else {
            waterGrade = "F";
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
