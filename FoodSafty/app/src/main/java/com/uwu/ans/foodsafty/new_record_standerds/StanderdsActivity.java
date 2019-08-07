package com.uwu.ans.foodsafty.new_record_standerds;

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
import com.uwu.ans.foodsafty.new_record_waste_management.WasteManagementActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StanderdsActivity extends AppCompatActivity {

    /*Violations*/
    @BindView(R.id.marks_violations)
    TextView mTextViewMarksViolations;
    @BindView(R.id.violations_no_on_labelling_rules)
    CheckBox mCheckBoxViolationsOnLAbelingRules;
    @BindView(R.id.violations_no_on_sanitation)
    CheckBox mCheckBoxViolationsOnSanitation;
    int violation;


    /*FoodStorage*/
    @BindView(R.id.marks_deteriorated_food_storage)
    TextView mTextViewMarksFoodStorage;
    @BindView(R.id.deteriorated_food_storage_separated)
    CheckBox mCheckBoxFoodStorageSeperate;
    @BindView(R.id.deteriorated_food_storage_safe_disposal)
    CheckBox mCheckBoxFoodStorageSafeDisposal;
    int foodStorsge;

    /*QualityOfRawMaterials*/
    @BindView(R.id.marks_quality_of_raw_materials_foods)
    TextView mTextViewMarksQualityOfRawMaterials;
    @BindView(R.id.quality_of_raw_materials_foods_have_standards)
    CheckBox mCheckBoxQualityOfRawMaterialsFoodsAndStanderds;
    @BindView(R.id.quality_of_raw_materials_foods_optimum_shelf_life)
    CheckBox mCheckBoxQualityOfRawMaterialsFoodsOptimuShelfLife;
    int rawMaterials;


    /*FoodDisplay*/
    @BindView(R.id.marks_foods_display)
    TextView mTextViewMarksFoodDisplay;
    @BindView(R.id.foods_display_attractive)
    CheckBox mCheckBoxFoodDisplayAttractive;
    @BindView(R.id.foods_display_clean)
    CheckBox mCheckBoxFoodDisplayClean;
    int foodDisplay;

/*    @BindView(R.id.standard_commets)
    EditText mEditTextComments;*/

    @BindView(R.id.standard_total_marks)
    TextView mTextViewTotal;

    int TotalMarksINT = 0;

    double StanderdsPrecent = 0;
    String StanderdsGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;
    String RestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standerds);

        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);


        mCheckBoxFoodDisplayAttractive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodDisplayAttractive, foodDisplay, mTextViewMarksFoodDisplay);
            }
        });
        mCheckBoxFoodDisplayClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodDisplayClean, foodDisplay, mTextViewMarksFoodDisplay);
            }
        });


        mCheckBoxQualityOfRawMaterialsFoodsAndStanderds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxQualityOfRawMaterialsFoodsAndStanderds, rawMaterials, mTextViewMarksQualityOfRawMaterials);
            }
        });
        mCheckBoxQualityOfRawMaterialsFoodsOptimuShelfLife.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxQualityOfRawMaterialsFoodsOptimuShelfLife, rawMaterials, mTextViewMarksQualityOfRawMaterials);
            }
        });



        mCheckBoxFoodStorageSeperate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodStorageSeperate, foodStorsge, mTextViewMarksFoodStorage);
            }
        });
        mCheckBoxFoodStorageSafeDisposal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodStorageSafeDisposal, foodStorsge, mTextViewMarksFoodStorage);
            }
        });



        mCheckBoxViolationsOnLAbelingRules.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxViolationsOnLAbelingRules, violation, mTextViewMarksViolations);
            }
        });
        mCheckBoxViolationsOnSanitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxViolationsOnSanitation, violation, mTextViewMarksViolations);
            }
        });


    }

    @OnClick(R.id.standard_next)
    public void nextFoodStandards(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("standardsMarks").setValue(StanderdsPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Food Standards Grade : " +StanderdsGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(StanderdsActivity.this,WasteManagementActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = violation + foodStorsge + rawMaterials + foodDisplay;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        StanderdsPrecent = (Float.valueOf(TotalMarksINT) / 12) * 100;


        if (StanderdsPrecent >= 75) {
            StanderdsGrade = "A";
        } else if (StanderdsPrecent >= 65) {
            StanderdsGrade = "B";
        } else if (StanderdsPrecent >= 50) {
            StanderdsGrade = "C";
        } else if (StanderdsPrecent >= 35) {
            StanderdsGrade = "S";
        } else {
            StanderdsGrade = "F";
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
