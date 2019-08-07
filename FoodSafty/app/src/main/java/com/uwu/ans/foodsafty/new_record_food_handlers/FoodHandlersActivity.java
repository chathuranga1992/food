package com.uwu.ans.foodsafty.new_record_food_handlers;

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
import com.uwu.ans.foodsafty.new_record_equipment_and_utensils.EquipmentsAndUtencilsActivity;
import com.uwu.ans.foodsafty.new_record_packaging_materials.PackagingMaterialActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

public class FoodHandlersActivity extends AppCompatActivity {


    /*food handlers personal hygiene*/
    @BindView(R.id.marks_personal_hygiene)
    TextView mTextViewMarksPersanalHygine;
    @BindView(R.id.personal_hygiene_clean)
    CheckBox mCheckBoxPersanalHygineClean;
    @BindView(R.id.personal_hygiene_trimmed_nails)
    CheckBox mCheckBoxPersanalHygineTrimmedNails;
    @BindView(R.id.personal_hygiene_no_spitting)
    CheckBox mCheckBoxPersanalHygineNoSpitting;
    @BindView(R.id.personal_hygiene_sneezing_coughing)
    CheckBox mCheckBoxPersanalHygineSneezingCoughing;
    int personalHygine = 0;

    /*food handlers uniform*/
    @BindView(R.id.marks_uniform)
    TextView mTextViewMarksUniform;
    @BindView(R.id.uniform_apron_cap)
    CheckBox mCheckBoxUniformAprnCap;
    @BindView(R.id.uniform_other)
    CheckBox mCheckBoxUniformOther;
    int unoform = 0;

    /*diseases*/
    @BindView(R.id.marks_diseases)
    TextView mTextViewMarksDisease;
    @BindView(R.id.diseases_no_contagious_suffering_just_cured)
    CheckBox mCheckBoxDiseaseNoContagious;
    @BindView(R.id.diseases_no_fbd_suffering_just_curedt)
    CheckBox mCheckBoxDiseaseNoFbd;
    int disease = 0;

    /*training certification*/
    @BindView(R.id.marks_health_training_certification)
    TextView mTextViewMarksTrainingCertification;
    @BindView(R.id.health_training_certification_have_for_handlers)
    CheckBox mCheckBoxTrainingCertificationHaveForHandlers;
    @BindView(R.id.health_training_certification_have_for_owners)
    CheckBox mCheckBoxTrainingCertificationHaveForOwners;
    int trainingCertifcation = 0;

    int TotalMarksINT = 0;

    double FoodHandlerPrecent = 0;
    String FoodHandlerGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;
    String RestKey;

/*    @BindView(R.id.food_handler_comments)
    EditText mEditTextComments;*/

    @BindView(R.id.food_handler_total_marks)
    TextView mTextViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_handlers);
        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);


        mCheckBoxPersanalHygineClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxPersanalHygineClean, personalHygine, mTextViewMarksPersanalHygine);
            }
        });
        mCheckBoxPersanalHygineTrimmedNails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxPersanalHygineTrimmedNails, personalHygine, mTextViewMarksPersanalHygine);
            }
        });
        mCheckBoxPersanalHygineNoSpitting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxPersanalHygineNoSpitting, personalHygine, mTextViewMarksPersanalHygine);
            }
        });
        mCheckBoxPersanalHygineSneezingCoughing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxPersanalHygineSneezingCoughing, personalHygine, mTextViewMarksPersanalHygine);
            }
        });



        mCheckBoxUniformAprnCap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxUniformAprnCap, unoform, mTextViewMarksUniform);
            }
        });
        mCheckBoxUniformOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxUniformOther, unoform, mTextViewMarksUniform);
            }
        });



        mCheckBoxDiseaseNoContagious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDiseaseNoContagious, disease, mTextViewMarksDisease);
            }
        });
        mCheckBoxDiseaseNoFbd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxDiseaseNoFbd, disease, mTextViewMarksDisease);
            }
        });


        mCheckBoxTrainingCertificationHaveForHandlers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxTrainingCertificationHaveForHandlers, trainingCertifcation, mTextViewMarksTrainingCertification);
            }
        });
        mCheckBoxTrainingCertificationHaveForOwners.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxTrainingCertificationHaveForOwners, trainingCertifcation, mTextViewMarksTrainingCertification);
            }
        });



    }
    @OnClick(R.id.food_handler_next)
    public void nextFoodHandler(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("foodHandlerMarks").setValue(FoodHandlerPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Food Handler Grade : " +FoodHandlerGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(FoodHandlersActivity.this,PackagingMaterialActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = personalHygine + unoform + disease + trainingCertifcation;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        FoodHandlerPrecent = (Float.valueOf(TotalMarksINT) / 12) * 100;


        if (FoodHandlerPrecent >= 75) {
            FoodHandlerGrade = "A";
        } else if (FoodHandlerPrecent >= 65) {
            FoodHandlerGrade = "B";
        } else if (FoodHandlerPrecent >= 50) {
            FoodHandlerGrade = "C";
        } else if (FoodHandlerPrecent >= 35) {
            FoodHandlerGrade = "S";
        } else {
            FoodHandlerGrade = "F";
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
