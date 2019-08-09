package com.uwu.ans.foodsafty.new_record_record_keeping;

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
import com.uwu.ans.foodsafty.new_record_packaging_materials.PackagingMaterialActivity;
import com.uwu.ans.foodsafty.new_record_water_supply.WaterActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordKeepingActivity extends AppCompatActivity {

    /*record_keeping_food_raw_ingredients*/
    @BindView(R.id.marks_record_keeping_food_raw_ingredients)
    TextView mTextViewRecordKeepingFoorRawIngredient;
    @BindView(R.id.record_keeping_food_raw_ingredients_selling)
    CheckBox mCheckBoxRecordKeepingFoorRawIngredientSelling;
    @BindView(R.id.record_keeping_food_raw_ingredients_receiving)
    CheckBox mCheckBoxRecordKeepingFoorRawIngredientReceiving;
    @BindView(R.id.record_keeping_food_raw_ingredients_workers)
    CheckBox mCheckBoxRecordKeepingFoorRawIngredientWorkors;
    @BindView(R.id.record_keeping_food_raw_ingredients_billing)
    CheckBox mCheckBoxRecordKeepingFoorRawIngredientBilling;
    int rawIngredientd;

    /*signs_display*/
    @BindView(R.id.marks_signs_display)
    TextView mTextViewMarksSignsDisplay;
    @BindView(R.id.signs_display_adequate)
    CheckBox mCheckBoxSignsDisplayAdequate;
    @BindView(R.id.signs_display_appropriate)
    CheckBox mCheckBoxSignsDisplayAppropriate;
    int signs;

    /*smoking*/
    @BindView(R.id.marks_smoking)
    TextView mTextViewMarksDisease;
    @BindView(R.id.smoking_separated)
    CheckBox mCheckBoxSmokingSeperated;
    @BindView(R.id.smoking_proper)
    CheckBox mCheckBoxSmokingPropper;
    int smoking;

    /*food_safety_certifications*/
    @BindView(R.id.marks_food_safety_certifications)
    TextView mTextViewMarksFoodSafetyCertification;
    @BindView(R.id.food_safety_certifications_handlers)
    CheckBox mCheckBoxFoodSafetyCertificationHaveForHandlers;
    @BindView(R.id.food_safety_certifications_owners)
    CheckBox mCheckBoxFoodSafetyCertificationHaveForOwners;
    int FSCertification;


    int TotalMarksINT = 0;
    String RestKey;
    double RecordKeepingPrecent = 0;
    String RecordKeepingGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;
/*
    @BindView(R.id.record_keeping_comments)
    EditText mEditTextComments;*/

    @BindView(R.id.record_keeping_total_marks)
    TextView mTextViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record_keeping);
        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxFoodSafetyCertificationHaveForHandlers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodSafetyCertificationHaveForHandlers, FSCertification, mTextViewMarksFoodSafetyCertification);
            }
        });
        mCheckBoxFoodSafetyCertificationHaveForOwners.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxFoodSafetyCertificationHaveForOwners, FSCertification, mTextViewMarksFoodSafetyCertification);
            }
        });


        mCheckBoxSmokingSeperated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxSmokingSeperated, smoking, mTextViewMarksDisease);
            }
        });
        mCheckBoxSmokingPropper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxSmokingPropper, smoking, mTextViewMarksDisease);
            }
        });


        mCheckBoxSignsDisplayAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxSignsDisplayAdequate, signs, mTextViewMarksSignsDisplay);
            }
        });
        mCheckBoxSignsDisplayAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxSignsDisplayAppropriate, signs, mTextViewMarksSignsDisplay);
            }
        });


        mCheckBoxRecordKeepingFoorRawIngredientSelling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRecordKeepingFoorRawIngredientSelling, rawIngredientd, mTextViewRecordKeepingFoorRawIngredient);
            }
        });
        mCheckBoxRecordKeepingFoorRawIngredientReceiving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRecordKeepingFoorRawIngredientReceiving, rawIngredientd, mTextViewRecordKeepingFoorRawIngredient);
            }
        });
        mCheckBoxRecordKeepingFoorRawIngredientWorkors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRecordKeepingFoorRawIngredientWorkors, rawIngredientd, mTextViewRecordKeepingFoorRawIngredient);
            }
        });
        mCheckBoxRecordKeepingFoorRawIngredientBilling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxRecordKeepingFoorRawIngredientBilling, rawIngredientd, mTextViewRecordKeepingFoorRawIngredient);
            }
        });
        
    }
    @OnClick(R.id.record_keeping_next)
    public void nextRecordKeeping(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("recordKeepingMarks").setValue(RecordKeepingPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Record Keeping Grade : " +RecordKeepingGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RecordKeepingActivity.this,WaterActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }

    private void setTotalMarks() {
        TotalMarksINT = rawIngredientd + signs + smoking + FSCertification;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        RecordKeepingPrecent = (Float.valueOf(TotalMarksINT) / 10) * 100;


        if (RecordKeepingPrecent >= 75) {
            RecordKeepingGrade = "A";
        } else if (RecordKeepingPrecent >= 65) {
            RecordKeepingGrade = "B";
        } else if (RecordKeepingPrecent >= 50) {
            RecordKeepingGrade = "C";
        } else if (RecordKeepingPrecent >= 35) {
            RecordKeepingGrade = "S";
        } else {
            RecordKeepingGrade = "F";
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
