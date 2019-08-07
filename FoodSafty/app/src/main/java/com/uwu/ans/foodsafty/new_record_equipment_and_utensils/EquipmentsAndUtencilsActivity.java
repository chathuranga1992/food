package com.uwu.ans.foodsafty.new_record_equipment_and_utensils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.new_record_building.BuildingActivity;
import com.uwu.ans.foodsafty.new_record_food_handlers.FoodHandlersActivity;
import com.uwu.ans.foodsafty.new_record_food_preperation.FoodPreperationActivity;
import com.uwu.ans.foodsafty.result.ResultActivity;

public class EquipmentsAndUtencilsActivity extends AppCompatActivity {

    String RestKey;

    @BindView(R.id.marks_handling_equipment)
    TextView remarksEqHandling;
    @BindView(R.id.handling_equipment_separated)
    CheckBox mCheckBoxHandlingEQSeparated;
    @BindView(R.id.handling_equipment_enough)
    CheckBox mCheckBoxHandlingEQEnough;
    @BindView(R.id.handling_equipment_safe)
    CheckBox mCheckBoxHandlingEQSafe;
    @BindView(R.id.handling_equipment_suitable)
    CheckBox mCheckBoxHandlingEQSuitable;

    int EqHandlingMarks=0;

    @BindView(R.id.marks_cleanliness)
    TextView remarksCleanliness;
    @BindView(R.id.cleanliness_adequate)
    CheckBox mCheckBoxCleanlinessAdequate;
    @BindView(R.id.cleanliness_appropriate)
    CheckBox mCheckBoxCleanlinessAppropriate;
    @BindView(R.id.cleanliness_hygienic)
    CheckBox mCheckBoxCleanlinessHygienic;
    @BindView(R.id.cleanliness_proper_maintenance)
    CheckBox mCheckBoxCleanlinessPropMaintanance;

    int CleanlinessMarks=0;

    @BindView(R.id.marks_chopping_boards)
    TextView remarksChoppingBoards;
    @BindView(R.id.chopping_boards_separated_meat_vegetables)
    CheckBox mCheckBoxChB1;
    @BindView(R.id.chopping_boards_clean)
    CheckBox mCheckBoxChB2;
    @BindView(R.id.chopping_boards_sanitized)
    CheckBox mCheckBoxChB3;
    @BindView(R.id.chopping_boards_separated)
    CheckBox mCheckBoxChB4;

    int ChoppingBoardsMarks=0;

    @BindView(R.id.marks_storage)
    TextView remarksStorage;
    @BindView(R.id.storage_separate_clean_unclean)
    CheckBox mCheckBoxStor1;
    @BindView(R.id.storage_hygienic)
    CheckBox mCheckBoxStor2;
    @BindView(R.id.storage_safe)
    CheckBox mCheckBoxStor3;
    @BindView(R.id.storage_no_contamination)
    CheckBox mCheckBoxStor4;

    int StorageMarks=0;

    @BindView(R.id.marks_chinaware)
    TextView remarksChinaware;
    @BindView(R.id.chinaware_enough)
    CheckBox mCheckBoxChw1;
    @BindView(R.id.chinaware_hygienic)
    CheckBox mCheckBoxChw2;
    @BindView(R.id.chinaware_no_cracks)
    CheckBox mCheckBoxChw3;
    @BindView(R.id.chinaware_sanitized)
    CheckBox mCheckBoxChw4;

    int ChinawareMarks=0;

    @BindView(R.id.marks_furniture)
    TextView remarksFurniture;
    @BindView(R.id.furniture_adequate)
    CheckBox mCheckBoxFur1;
    @BindView(R.id.furniture_appropriate)
    CheckBox mCheckBoxFur2;

    int FurnitureMarks=0;

    @BindView(R.id.equipments_n_utensil_Full_marks)
    TextView mTextViewTotal;

    @BindView(R.id.foodStorage_next_btn)
    Button nextBtn;

    int TotalMarksINT =0;

    double EqPrecent =0;
    String mEqGrade = "F";

    DatabaseReference mDatabaseFoodSafe;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments_and_utencils);

        ButterKnife.bind(this);

        RestKey = getIntent().getStringExtra("RestName");

        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxHandlingEQSeparated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxHandlingEQSeparated.isChecked())
                {
                    EqHandlingMarks = EqHandlingMarks +1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
                else {
                    EqHandlingMarks = EqHandlingMarks -1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxHandlingEQEnough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxHandlingEQEnough.isChecked())
                {
                    EqHandlingMarks = EqHandlingMarks +1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
                else {
                    EqHandlingMarks = EqHandlingMarks -1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxHandlingEQSafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxHandlingEQSafe.isChecked())
                {
                    EqHandlingMarks = EqHandlingMarks +1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
                else {
                    EqHandlingMarks = EqHandlingMarks -1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxHandlingEQSuitable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxHandlingEQSuitable.isChecked())
                {
                    EqHandlingMarks = EqHandlingMarks +1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
                else {
                    EqHandlingMarks = EqHandlingMarks -1;
                    String mStringMarks = Integer.valueOf(EqHandlingMarks).toString();
                    setMarkViewFour(remarksEqHandling, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxCleanlinessAdequate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxCleanlinessAdequate.isChecked())
                {
                    CleanlinessMarks = CleanlinessMarks +1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
                else {
                    CleanlinessMarks = CleanlinessMarks -1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxCleanlinessAppropriate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxCleanlinessAppropriate.isChecked())
                {
                    CleanlinessMarks = CleanlinessMarks +1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
                else {
                    CleanlinessMarks = CleanlinessMarks -1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxCleanlinessHygienic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxCleanlinessHygienic.isChecked())
                {
                    CleanlinessMarks = CleanlinessMarks +1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
                else {
                    CleanlinessMarks = CleanlinessMarks -1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxCleanlinessPropMaintanance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxCleanlinessPropMaintanance.isChecked())
                {
                    CleanlinessMarks = CleanlinessMarks +1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
                else {
                    CleanlinessMarks = CleanlinessMarks -1;
                    String mStringMarks = Integer.valueOf(CleanlinessMarks).toString();
                    setMarkViewFour(remarksCleanliness, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxChB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChB1.isChecked())
                {
                    ChoppingBoardsMarks = ChoppingBoardsMarks +1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChoppingBoardsMarks = ChoppingBoardsMarks -1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChB2.isChecked())
                {
                    ChoppingBoardsMarks = ChoppingBoardsMarks +1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChoppingBoardsMarks = ChoppingBoardsMarks -1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChB3.isChecked())
                {
                    ChoppingBoardsMarks = ChoppingBoardsMarks +1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChoppingBoardsMarks = ChoppingBoardsMarks -1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChB4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChB4.isChecked())
                {
                    ChoppingBoardsMarks = ChoppingBoardsMarks +1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChoppingBoardsMarks = ChoppingBoardsMarks -1;
                    String mStringMarks = Integer.valueOf(ChoppingBoardsMarks).toString();
                    setMarkViewFour(remarksChoppingBoards, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxStor1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxStor1.isChecked())
                {
                    StorageMarks = StorageMarks +1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
                else {
                    StorageMarks = StorageMarks -1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStor2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxStor2.isChecked())
                {
                    StorageMarks = StorageMarks +1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
                else {
                    StorageMarks = StorageMarks -1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStor3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxStor3.isChecked())
                {
                    StorageMarks = StorageMarks +1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
                else {
                    StorageMarks = StorageMarks -1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxStor4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxStor4.isChecked())
                {
                    StorageMarks = StorageMarks +1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
                else {
                    StorageMarks = StorageMarks -1;
                    String mStringMarks = Integer.valueOf(StorageMarks).toString();
                    setMarkViewFour(remarksStorage, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxChw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChw1.isChecked())
                {
                    ChinawareMarks = ChinawareMarks +1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChinawareMarks = ChinawareMarks -1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChw2.isChecked())
                {
                    ChinawareMarks = ChinawareMarks +1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChinawareMarks = ChinawareMarks -1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChw3.isChecked())
                {
                    ChinawareMarks = ChinawareMarks +1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChinawareMarks = ChinawareMarks -1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxChw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxChw4.isChecked())
                {
                    ChinawareMarks = ChinawareMarks +1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
                else {
                    ChinawareMarks = ChinawareMarks -1;
                    String mStringMarks = Integer.valueOf(ChinawareMarks).toString();
                    setMarkViewFour(remarksChinaware, mStringMarks);
                    setTotalMarks();
                }
            }
        });

        mCheckBoxFur1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxFur1.isChecked())
                {
                    FurnitureMarks = FurnitureMarks +1;
                    String mStringMarks = Integer.valueOf(FurnitureMarks).toString();
                    setMarkViewTwo(remarksFurniture, mStringMarks);
                    setTotalMarks();
                }
                else
                    {
                    FurnitureMarks = FurnitureMarks -1;
                    String mStringMarks = Integer.valueOf(FurnitureMarks).toString();
                    setMarkViewTwo(remarksFurniture, mStringMarks);
                    setTotalMarks();
                }
            }
        });
        mCheckBoxFur2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mCheckBoxFur2.isChecked())
                {
                    FurnitureMarks = FurnitureMarks +1;
                    String mStringMarks = Integer.valueOf(FurnitureMarks).toString();
                    setMarkViewTwo(remarksFurniture, mStringMarks);
                    setTotalMarks();
                }
                else
                {
                    FurnitureMarks = FurnitureMarks -1;
                    String mStringMarks = Integer.valueOf(FurnitureMarks).toString();
                    setMarkViewTwo(remarksFurniture, mStringMarks);
                    setTotalMarks();
                }
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseFoodSafe.child("Inspections").child(RestKey).child("equipmentUtensilsMarks").setValue(EqPrecent,new DatabaseReference.CompletionListener(){

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        if (databaseError != null) {
                            System.out.println("Data could not be saved. " + databaseError.getMessage());
                        } else {
                            //System.out.println("Data saved successfully.");
                            dialog.setTitle("Equipment/Utensils Grade : " +mEqGrade);
                            dialog.show();
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(EquipmentsAndUtencilsActivity.this,FoodHandlersActivity.class);
                                    intent.putExtra("RestName",RestKey);
                                    startActivity(intent);
                                }
                            }, 2000);

                        }
                    }
                });

            }
        });

    }


    private void setTotalMarks() {
        TotalMarksINT = EqHandlingMarks+ CleanlinessMarks+ChoppingBoardsMarks+StorageMarks+ChinawareMarks;
        String mBuildingTotalMarks = Integer.valueOf(TotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        EqPrecent = (Float.valueOf(TotalMarksINT)/22)*100;


        if(EqPrecent >= 75){
            mEqGrade = "A";
        }
        else if(EqPrecent >= 65){
            mEqGrade = "B";
        }
        else if(EqPrecent >=50){
            mEqGrade = "C";
        }
        else if(EqPrecent >=35){
            mEqGrade = "S";
        }
        else{
            mEqGrade = "F";
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
}
