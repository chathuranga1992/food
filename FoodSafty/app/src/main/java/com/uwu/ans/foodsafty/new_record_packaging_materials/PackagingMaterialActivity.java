package com.uwu.ans.foodsafty.new_record_packaging_materials;

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
import com.rengwuxian.materialedittext.MaterialEditText;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_food_handlers.FoodHandlersActivity;
import com.uwu.ans.foodsafty.new_record_record_keeping.RecordKeepingActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackagingMaterialActivity extends AppCompatActivity {

    /*empty crates cartons containers*/
    @BindView(R.id.marks_empty_crates_cartons_containers)
    TextView mTextViewMarksCratesNContainers;
    @BindView(R.id.empty_crates_cartons_containers_safe_disposal)
    CheckBox mCheckBoxCratesNContainersSafeDisposal;
    @BindView(R.id.empty_crates_cartons_containers_separated_storage)
    CheckBox mCheckBoxCratesNContainersSeparatedStorage;
    @BindView(R.id.empty_crates_cartons_containers_no_contamination)
    CheckBox mCheckBoxCratesNContainersNoContamination;
    @BindView(R.id.empty_crates_cartons_containers_enough)
    CheckBox mCheckBoxCratesNContainersEnough;
    int emptyContainers;

    /*empty crates cartons containers*/
    @BindView(R.id.marks_contaminated_substances)
    TextView mTextViewMarksContaminatedSubstance;
    @BindView(R.id.contaminated_substances_separated)
    CheckBox mCheckBoxContaminatedSubstance;
    @BindView(R.id.contaminated_substances_covered)
    CheckBox mCheckBoxContaminatedSubstanceCovered;
    @BindView(R.id.contaminated_substances_labelled)
    CheckBox mCheckBoxContaminatedSubstanceLabeled;
    @BindView(R.id.contaminated_substances_original_container)
    CheckBox mCheckBoxContaminatedSubstanceOriginalContainer;
    int contaminatedSubs;


    /*food handlers uniform*/
    @BindView(R.id.marks_packaging_materials)
    TextView mTextViewMarksPackagingMaterials;
    @BindView(R.id.packaging_materials_food_grade_polythene_hdpe_plastic_pp5)
    CheckBox mCheckBoxPackagingMaterialsFoodGrade;
    @BindView(R.id.packaging_materials_no_contamination)
    CheckBox mCheckBoxPackagingMaterialsNoContamination;
    int packagingMaterial;

    @BindView(R.id.packaging_materials_comment)
    EditText mEditTextComments;

    @BindView(R.id.packaging_materials_total_marks)
    TextView mTextViewTotal;

    int TotalMarksINT = 0;

    double PackagingMaterialPrecent = 0;
    String PackagingMaterialGrade = "F";

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
        setContentView(R.layout.activity_packaging_material);
        
        ButterKnife.bind(this);
        RestKey = getIntent().getStringExtra("RestName");
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);



        mCheckBoxPackagingMaterialsFoodGrade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxPackagingMaterialsFoodGrade, packagingMaterial, mTextViewMarksPackagingMaterials);
            }
        });
        mCheckBoxPackagingMaterialsNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxTwo(mCheckBoxPackagingMaterialsNoContamination, packagingMaterial, mTextViewMarksPackagingMaterials);
            }
        });


        mCheckBoxContaminatedSubstance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminatedSubstance, contaminatedSubs, mTextViewMarksContaminatedSubstance);
            }
        });
        mCheckBoxContaminatedSubstanceCovered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminatedSubstanceCovered, contaminatedSubs, mTextViewMarksContaminatedSubstance);
            }
        });
        mCheckBoxContaminatedSubstanceLabeled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminatedSubstanceLabeled, contaminatedSubs, mTextViewMarksContaminatedSubstance);
            }
        });
        mCheckBoxContaminatedSubstanceOriginalContainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxContaminatedSubstanceOriginalContainer, contaminatedSubs, mTextViewMarksContaminatedSubstance);
            }
        });



        mCheckBoxCratesNContainersSafeDisposal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCratesNContainersSafeDisposal, emptyContainers, mTextViewMarksCratesNContainers);
            }
        });
        mCheckBoxCratesNContainersSeparatedStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCratesNContainersSeparatedStorage, emptyContainers, mTextViewMarksCratesNContainers);
            }
        });
        mCheckBoxCratesNContainersNoContamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCratesNContainersNoContamination, emptyContainers, mTextViewMarksCratesNContainers);
            }
        });
        mCheckBoxCratesNContainersEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                processCheckBoxFour(mCheckBoxCratesNContainersEnough, emptyContainers, mTextViewMarksCratesNContainers);
            }
        });





        
    }



    @OnClick(R.id.packaging_materials_next)
    public void nextPackageMaterial(View view){

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("packagingMaterialMarks").setValue(PackagingMaterialPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Packaging Material Grade : " +PackagingMaterialGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(PackagingMaterialActivity.this,RecordKeepingActivity.class);
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }
        });

    }

    private void setTotalMarks() {
        TotalMarksINT = packagingMaterial + contaminatedSubs + emptyContainers;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        PackagingMaterialPrecent = (Float.valueOf(TotalMarksINT) / 10) * 100;


        if (PackagingMaterialPrecent >= 75) {
            PackagingMaterialGrade = "A";
        } else if (PackagingMaterialPrecent >= 65) {
            PackagingMaterialGrade = "B";
        } else if (PackagingMaterialPrecent >= 50) {
            PackagingMaterialGrade = "C";
        } else if (PackagingMaterialPrecent >= 35) {
            PackagingMaterialGrade = "S";
        } else {
            PackagingMaterialGrade = "F";
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
