package com.uwu.ans.foodsafty.new_record_LocationEnvironment;

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
import android.util.Log;
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
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains.Animals;
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains.Appearance;
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains.LocationModel;
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains.Pollution;
import com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains.Suitability;
import com.uwu.ans.foodsafty.new_record_building.BuildingActivity;
import com.uwu.ans.foodsafty.new_record_building.domains.BuildingModel;
import com.uwu.ans.foodsafty.new_record_building.domains.LightNVentilation;
import com.uwu.ans.foodsafty.new_record_building.domains.RiskFactors;
import com.uwu.ans.foodsafty.new_record_building.domains.Structure;
import com.uwu.ans.foodsafty.new_record_building.domains.WallMaintatance;

public class LocationEnvironmentActivity extends AppCompatActivity {

    /*location suitability*/
    @BindView(R.id.marks_location_suitability)
    TextView mTextViewMarksLocationSuitability;
    @BindView(R.id.location_suitability_accessibility)
    CheckBox mCheckBoxLocationSuitabilityaccessibility;
    @BindView(R.id.location_suitability_activity)
    CheckBox mCheckBoxLocationSuitabilityActivity;
    @BindView(R.id.location_suitability_amenity)
    CheckBox mCheckBoxLocationSuitabilityAmenity;
    @BindView(R.id.location_suitability_visibilty)
    CheckBox mCheckBoxLocationSuitabilityVisibility;

    /*appearance*/
    @BindView(R.id.marks_loaction_appearance)
    TextView mTextViewMarksLocationAppearance;
    @BindView(R.id.location_appearance_attractive)
    CheckBox mCheckBoxLocationAppearanceAttractive;
    @BindView(R.id.location_appearance_beautiful)
    CheckBox mCheckBoxLocationAppearanceBeautiful;

    /*pollution*/
    @BindView(R.id.marks_location_pollution)
    TextView mTextViewMarksLocationPollution;
    @BindView(R.id.location_pollution_no_accumilation)
    CheckBox mCheckBoxLocationPollutionNoAccumilation;
    @BindView(R.id.location_pollution_no_adverseeffects)
    CheckBox mCheckBoxLocationPollutionNoAdverseEffects;
    @BindView(R.id.location_pollution_no_dust)
    CheckBox mCheckBoxLocationPollutionNoDust;
    @BindView(R.id.location_pollution_no_smoke)
    CheckBox mCheckBoxLocationPollutionNoSmoke;

    /*animals*/
    @BindView(R.id.marks_location_animals)
    TextView mTextViewMarksLocationAnimals;
    @BindView(R.id.location_animals_dogcats)
    CheckBox mCheckBoxLocationAnimalsDogsCats;
    @BindView(R.id.location_animals_other)
    CheckBox mCheckBoxLocationAnimalsOther;

    @BindView(R.id.location_comments)
    EditText mEditTextComments;

    @BindView(R.id.location_total_marks)
    TextView mTextViewTotal;


    public DatabaseReference mDatabaseFoodSafe;
    public ProgressDialog dialog;
    private int mLocationSuitabilityMarksINT = 0;
    private int mLocationAppearanceMarksINT = 0;
    private int mLocationPollutionMarksINT = 0;
    private int mLocationAnimalsMarksINT = 0;
    private int mLocationTotalMarksINT = 0;

    String mLocationSuitabilityMarks1;
    String mLocationSuitabilityMarks2;
    String mLocationSuitabilityMarks3;
    String mLocationSuitabilityMarks4;
    String mLocationPollutionMarks1, mLocationPollutionMarks2, mLocationPollutionMarks3, mLocationPollutionMarks4;
    String mLocationAppearanceMarks1, mLocationAppearanceMarks2;
    String mLocationAnimalsMarks1, mLocationAnimalsMarks2;

    String mLocationGrade= "F";
    double mLocationPrecent=0;
    String RestKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_location_environment);

        ButterKnife.bind(this);

        RestKey = getIntent().getStringExtra("RestName");

        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference();

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mCheckBoxLocationSuitabilityActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                suitabilityMarks();
            }
        });

        mCheckBoxLocationSuitabilityAmenity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                suitabilityMarks();
            }
        });

        mCheckBoxLocationSuitabilityaccessibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                suitabilityMarks();
            }
        });

        mCheckBoxLocationSuitabilityVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                suitabilityMarks();
            }
        });


        mCheckBoxLocationAppearanceAttractive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appearanceMarks();
            }
        });

        mCheckBoxLocationAppearanceBeautiful.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appearanceMarks();
            }
        });



        mCheckBoxLocationPollutionNoSmoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pollutionMarks();
            }
        });

        mCheckBoxLocationPollutionNoDust.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pollutionMarks();
            }
        });

        mCheckBoxLocationPollutionNoAdverseEffects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pollutionMarks();
            }
        });

        mCheckBoxLocationPollutionNoAccumilation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pollutionMarks();
            }
        });


        mCheckBoxLocationAnimalsDogsCats.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                animalMarks();
            }
        });

        mCheckBoxLocationAnimalsOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                animalMarks();
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


    @OnClick(R.id.location_next)
    public void setLocation(View view) {

        String location_id = mDatabaseFoodSafe.push().getKey();

        suitabilityMarks();

        appearanceMarks();

        pollutionMarks();

        animalMarks();


        String mComment = mEditTextComments.getText().toString();

        String mLocationSuitabilityMarks = Integer.valueOf(mLocationSuitabilityMarksINT).toString();
        String mLocationSuitabilityReMarks = mTextViewMarksLocationSuitability.getText().toString();

        String mLocationAppearanceMarks = Integer.valueOf(mLocationAppearanceMarksINT).toString();
        String mLocationAppearanceReMarks = mTextViewMarksLocationAppearance.getText().toString();

        String mLocationPollutionMarks = Integer.valueOf(mLocationPollutionMarksINT).toString();
        String mLocationPollutionReMarks = mTextViewMarksLocationPollution.getText().toString();

        String mLocationAnimalsMarks = Integer.valueOf(mLocationAnimalsMarksINT).toString();
        String mLocationAnimalsReMarks = mTextViewMarksLocationAnimals.getText().toString();



        Appearance appearance = new Appearance(mLocationAppearanceMarks1
                , mLocationAppearanceMarks2, mLocationAppearanceMarks, mLocationAppearanceReMarks);

        Animals animals = new Animals(mLocationAnimalsMarks1,
                mLocationAnimalsMarks2, mLocationAnimalsMarks, mLocationAnimalsReMarks);


        Suitability suitability = new Suitability(mLocationSuitabilityMarks1,
                mLocationSuitabilityMarks3, mLocationSuitabilityMarks4, mLocationSuitabilityMarks2,
                mLocationSuitabilityMarks, mLocationSuitabilityReMarks);

        Pollution pollution = new Pollution(mLocationPollutionMarks1,
                mLocationPollutionMarks4, mLocationPollutionMarks3, mLocationPollutionMarks2,
                mLocationPollutionMarks, mLocationPollutionReMarks);


        String mLocationTotalMarks = Integer.valueOf(mLocationTotalMarksINT).toString();

        LocationModel locationModel = new LocationModel(location_id, location_id,mComment,
                mLocationTotalMarks, suitability,appearance,pollution,animals, String.valueOf(mLocationPrecent));

        mDatabaseFoodSafe.child("Inspections").child(RestKey).child("locationMarks").setValue(mLocationPrecent,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                    dialog.setTitle("Location/Environment Grade : " +mLocationGrade);
                    dialog.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LocationEnvironmentActivity.this,BuildingActivity.class);
                            intent.putExtra("LocationPrecent",String.valueOf(mLocationPrecent));
                            intent.putExtra("RestName",RestKey);
                            startActivity(intent);
                        }
                    }, 3000);

                }
            }
        });

    }



    private void animalMarks() {
        if (mCheckBoxLocationAnimalsDogsCats.isChecked()) {
            mLocationAnimalsMarks1 = "1";
        } else {
            mLocationAnimalsMarks1 = "0";
        }
        if (mCheckBoxLocationAnimalsOther.isChecked()) {
            mLocationAnimalsMarks2 = "1";
        } else {
            mLocationAnimalsMarks2 = "0";
        }

        mLocationAnimalsMarksINT = Integer.valueOf(mLocationAnimalsMarks1) + Integer.valueOf(mLocationAnimalsMarks2);
        String mStringMarks = Integer.valueOf(mLocationAnimalsMarksINT).toString();
        setMarkViewTwo(mTextViewMarksLocationAnimals, mStringMarks);

        setTotalMarks();

    }

    private void pollutionMarks() {
        if (mCheckBoxLocationPollutionNoSmoke.isChecked()) {
            mLocationPollutionMarks1 = "1";
        } else {
            mLocationPollutionMarks1 = "0";
        }
        if (mCheckBoxLocationPollutionNoAccumilation.isChecked()) {
            mLocationPollutionMarks2 = "1";
        } else {
            mLocationPollutionMarks2 = "0";
        }
        if (mCheckBoxLocationPollutionNoAdverseEffects.isChecked()) {
            mLocationPollutionMarks3 = "1";
        } else {
            mLocationPollutionMarks3 = "0";
        }
        if (mCheckBoxLocationPollutionNoDust.isChecked()) {
            mLocationPollutionMarks4 = "1";
        } else {
            mLocationPollutionMarks4 = "0";
        }

        mLocationPollutionMarksINT = Integer.valueOf(mLocationPollutionMarks1) +Integer.valueOf(mLocationPollutionMarks2)+
                Integer.valueOf(mLocationPollutionMarks3) + Integer.valueOf(mLocationPollutionMarks4);
        String mStringMarks = Integer.valueOf(mLocationPollutionMarksINT).toString();
        setMarkViewFour(mTextViewMarksLocationPollution, mStringMarks);

        setTotalMarks();
    }

    private void appearanceMarks() {
        if (mCheckBoxLocationAppearanceAttractive.isChecked()) {
            mLocationAppearanceMarks1 = "1";
        } else {
            mLocationAppearanceMarks1 = "0";
        }
        if (mCheckBoxLocationAppearanceBeautiful.isChecked()) {
            mLocationAppearanceMarks2 = "1";
        } else {
            mLocationAppearanceMarks2 = "0";
        }

        mLocationAppearanceMarksINT = Integer.valueOf(mLocationAppearanceMarks1) + Integer.valueOf(mLocationAppearanceMarks2);
        String mStringMarks = Integer.valueOf(mLocationAppearanceMarksINT).toString();
        setMarkViewTwo(mTextViewMarksLocationAppearance, mStringMarks);

        setTotalMarks();
    }

    private void suitabilityMarks() {
        /*Structure*/
        if (mCheckBoxLocationSuitabilityActivity.isChecked()) {
            mLocationSuitabilityMarks1 = "1";
        } else {
            mLocationSuitabilityMarks1 = "0";
        }
        if (mCheckBoxLocationSuitabilityAmenity.isChecked()) {
            mLocationSuitabilityMarks2 = "1";
        } else {
            mLocationSuitabilityMarks2 = "0";
        }
        if (mCheckBoxLocationSuitabilityaccessibility.isChecked()) {
            mLocationSuitabilityMarks3 = "1";
        } else {
            mLocationSuitabilityMarks3 = "0";
        }
        if (mCheckBoxLocationSuitabilityVisibility.isChecked()) {
            mLocationSuitabilityMarks4 = "1";
        } else {
            mLocationSuitabilityMarks4 = "0";
        }

        mLocationSuitabilityMarksINT = Integer.valueOf(mLocationSuitabilityMarks4) +Integer.valueOf(mLocationSuitabilityMarks3)+
                Integer.valueOf(mLocationSuitabilityMarks2) + Integer.valueOf(mLocationSuitabilityMarks1);
        String mStringMarks = Integer.valueOf(mLocationSuitabilityMarksINT).toString();
        setMarkViewFour(mTextViewMarksLocationSuitability, mStringMarks);

        setTotalMarks();
    }

    private void setTotalMarks() {
        mLocationTotalMarksINT =  mLocationAnimalsMarksINT + mLocationPollutionMarksINT
                + mLocationAppearanceMarksINT + mLocationSuitabilityMarksINT;
        String mBuildingTotalMarks = Integer.valueOf(mLocationTotalMarksINT).toString();
        mTextViewTotal.setText(mBuildingTotalMarks);

        mLocationPrecent = (Float.valueOf(mLocationTotalMarksINT)/12)*100;


        if(mLocationPrecent >= 75){
            mLocationGrade = "A";
        }
        else if(mLocationPrecent >= 65){
            mLocationGrade = "B";
        }
        else if(mLocationPrecent>=50){
            mLocationGrade = "C";
        }
        else if(mLocationPrecent>=35){
            mLocationGrade = "S";
        }
        else{
            mLocationGrade = "F";
        }
    }


}
