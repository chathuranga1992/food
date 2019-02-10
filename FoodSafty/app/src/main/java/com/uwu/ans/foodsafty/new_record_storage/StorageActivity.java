package com.uwu.ans.foodsafty.new_record_storage;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uwu.ans.foodsafty.R;

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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);


        ButterKnife.bind(this);
        mDatabaseFoodSafe = FirebaseDatabase.getInstance().getReference("building");

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
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
    
    @OnClick(R.id.building_next)
    public void setStorage(View view) {
        String mStorageMarks1, mStorageMarks2, mStorageMarks3, mStorageMarks4;
        String mStorageSeperatedMarks1, mStorageSeperatedMarks2, mStorageSeperatedMarks3, mStorageSeperatedMarks4;
        String mStorageLightAndVentilationMarks1, mStorageLightAndVentilationMarks2;
        String mStorageFacilitiesMarks1, mStorageFacilitiesMarks2;
        String mStorageRefridgerationMarks1, mStorageRefridgerationMarks2, mStorageRefridgerationMarks3, mStorageRefridgerationMarks4;
        String mBuildingSpaceMarks1, mBuildingSpaceMarks2;
        String mStorageFrozenMarks1, mStorageFrozenMarks2, mStorageFrozenMarks3, mStorageFrozenMarks4;
        String mStorageContaminationMarks1, mStorageContaminationMarks2, mStorageContaminationMarks3, mStorageContaminationMarks4;
        String mStoragePestControlMarks1, mStoragePestControlMarks2;
        String mStorageCleanMarks1, mStorageCleanMarks2, mStorageCleanMarks3, mStorageCleanMarks4;

        String Storage_id = mDatabaseFoodSafe.push().getKey();

        /*Storage*/
        if (mCheckBoxStorageSeperated.isChecked()) {
            mStorageMarksINT = mStorageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            mTextViewMarksStorage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            //setMarksFour(mStorageMarksINT, mTextViewMarksStorage);

            mStorageMarks1 = "1";
        } else {
            //mStorageMarksINT = mStorageMarksINT;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            mStorageMarks1 = "0";
        }
        if (mCheckBoxStorageFIFO.isChecked()) {
            mStorageMarksINT = mStorageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            mTextViewMarksStorage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            //setMarksFour(mStorageMarksINT, mTextViewMarksStorage);
            mStorageMarks2 = "1";
        } else {
            //mStorageMarksINT = mStorageMarksINT;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            mStorageMarks2 = "0";
        }
        if (mCheckBoxStoragePerishable.isChecked()) {
            mStorageMarksINT = mStorageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            mTextViewMarksStorage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            //setMarksFour(mStorageMarksINT, mTextViewMarksStorage);
            mStorageMarks3 = "1";
        } else {
            //mStorageMarksINT = mStorageMarksINT;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            mStorageMarks3 = "0";
        }
        if (mCheckBoxStorageNonPerishable.isChecked()) {
            mStorageMarksINT = mStorageMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            mTextViewMarksStorage.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            //setMarksFour(mStorageMarksINT, mTextViewMarksStorage);
            mStorageMarks4 = "1";
            Log.e("=============","============= "+mStorageMarksINT);
        } else {
            //mStorageMarksINT = mStorageMarksINT;
            String mStringMarks = Integer.valueOf(mStorageMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorage, mStringMarks);
            mStorageMarks4 = "0";
        }

        /*Light and ventilation*/
        if (mCheckBoxStorageLightNVentilationAdequate.isChecked()) {
            mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
            mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
            //setMarksTwo(mStorageLightAndVentilationMarksINT, mTextViewMarksStorageLightNVentilation);
            mStorageLightAndVentilationMarks1 = "1";
        } else {
            //mBuildingStructureMarksINT = mBuildingStructureMarksINT;
            String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
            mStorageLightAndVentilationMarks1 = "0";
        }
        if (mCheckBoxStorageLightNVentilationAppropriate.isChecked()) {
            mStorageLightAndVentilationMarksINT = mStorageLightAndVentilationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
            mTextViewMarksStorageLightNVentilation.setText(mStringMarks);
            setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
            //setMarksTwo(mStorageLightAndVentilationMarksINT, mTextViewMarksStorageLightNVentilation);
            mStorageLightAndVentilationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageLightAndVentilationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageLightNVentilation, mStringMarks);
            mStorageLightAndVentilationMarks2 = "0";
        }

        /*Storage Separated*/
        if (mCheckBoxStorage_18_h_from_floor_level.isChecked()) {
            mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            mTextViewMarksStorageSeperated.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            //setMarksFour(mStorageSeperatedMarksINT, mTextViewMarksStorageSeperated);
            mStorageSeperatedMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageSeperated, mStringMarks);
            mStorageSeperatedMarks1 = "0";
        }
        if (mCheckBoxStorage_9_from_wall.isChecked()) {
            mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            mTextViewMarksStorageSeperated.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            //setMarksFour(mStorageSeperatedMarksINT, mTextViewMarksStorageSeperated);
            mStorageSeperatedMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            mStorageSeperatedMarks2 = "0";
        }
        if (mCheckBoxStorageClean.isChecked()) {
            mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            mTextViewMarksStorageSeperated.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            //setMarksFour(mStorageSeperatedMarksINT, mTextViewMarksStorageSeperated);
            mStorageSeperatedMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            mStorageSeperatedMarks3 = "0";
        }
        if (mCheckBoxStorageProper.isChecked()) {
            mStorageSeperatedMarksINT = mStorageSeperatedMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            mTextViewMarksStorageSeperated.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            //setMarksFour(mStorageSeperatedMarksINT, mTextViewMarksStorageSeperated);
            mStorageSeperatedMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageSeperatedMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageSeperated, mStringMarks);
            mStorageSeperatedMarks4 = "0";
        }

        /*Wall maintenance*/
        if (mCheckBoxStorageRefridgerationAccurate_thermometers.isChecked()) {
            mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            mTextViewMarksStorageRefridgeration.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            //setMarksFour(mStorageRefrigerationMarksINT, mTextViewMarksStorageRefridgeration);
            mStorageRefridgerationMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageRefridgeration, mStringMarks);
            mStorageRefridgerationMarks1 = "0";
        }
        if (mCheckBoxStorageRefridgerationStorage_refrigeration_fifo_overstock.isChecked()) {
            mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            mTextViewMarksStorageRefridgeration.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            //setMarksFour(mStorageRefrigerationMarksINT, mTextViewMarksStorageRefridgeration);
            mStorageRefridgerationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            mStorageRefridgerationMarks2 = "0";
        }
        if (mCheckBoxStorageRefridgerationClean.isChecked()) {
            mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            mTextViewMarksStorageRefridgeration.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            //setMarksFour(mStorageRefrigerationMarksINT, mTextViewMarksStorageRefridgeration);
            mStorageRefridgerationMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            mStorageRefridgerationMarks3 = "0";
        }
        if (mCheckBoxStorageRefridgerationNoContamination.isChecked()) {
            mStorageRefrigerationMarksINT = mStorageRefrigerationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            mTextViewMarksStorageRefridgeration.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            //setMarksFour(mStorageRefrigerationMarksINT, mTextViewMarksStorageRefridgeration);
            mStorageRefridgerationMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageRefrigerationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageRefridgeration, mStringMarks);
            mStorageRefridgerationMarks4 = "0";
        }

        /*Wall maintenance*/
        if (mCheckBoxStorageFrozenAccurate_thermometers.isChecked()) {
            mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            mTextViewMarksStorageFrozen.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            //setMarksFour(mStorageFrozenMarksINT, mTextViewMarksStorageFrozen);
            mStorageFrozenMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageFrozen, mStringMarks);
            mStorageFrozenMarks1 = "0";
        }
        if (mCheckBoxStorageFrozennStorage_refrigeration_fifo_overstock.isChecked()) {
            mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            mTextViewMarksStorageFrozen.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            //setMarksFour(mStorageFrozenMarksINT, mTextViewMarksStorageFrozen);
            mStorageFrozenMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            mStorageFrozenMarks2 = "0";
        }
        if (mCheckBoxStorageFrozenClean.isChecked()) {
            mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            mTextViewMarksStorageFrozen.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            //setMarksFour(mStorageFrozenMarksINT, mTextViewMarksStorageFrozen);
            mStorageFrozenMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            mStorageFrozenMarks3 = "0";
        }
        if (mCheckBoxStorageFrozenNoContamination.isChecked()) {
            mStorageFrozenMarksINT = mStorageFrozenMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            mTextViewMarksStorageFrozen.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            //setMarksFour(mStorageFrozenMarksINT, mTextViewMarksStorageFrozen);
            mStorageFrozenMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageFrozenMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageFrozen, mStringMarks);
            mStorageFrozenMarks4 = "0";
        }

        /*Storage Facilities*/
        if (mCheckBoxStorageFacilitiesAdequate.isChecked()) {
            mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
            mTextViewMarksStorageFacilities.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFacilities, mStringMarks);
            //setMarksFour(mStorageStorageFacilitiesMarksINT, mTextViewMarksStorageFacilities);
            mStorageFacilitiesMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageFacilities, mStringMarks);
            mStorageFacilitiesMarks1 = "0";
        }
        if (mCheckBoxStorageFacilitiesAppropriate.isChecked()) {
            mStorageStorageFacilitiesMarksINT = mStorageStorageFacilitiesMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
            mTextViewMarksStorageFacilities.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageFacilities, mStringMarks);
            //setMarksFour(mStorageStorageFacilitiesMarksINT, mTextViewMarksStorageFacilities);
            mStorageFacilitiesMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageStorageFacilitiesMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageFacilities, mStringMarks);
            mStorageFacilitiesMarks2 = "0";
        }

        /*StorageClean*/
        if (mCheckBoxStorageCleanAppropriate.isChecked()) {
            mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            mTextViewMarksStorageClean.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            //setMarksFour(mStorageCleanMarksINT, mTextViewMarksStorageClean);
            mStorageCleanMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageClean, mStringMarks);
            mStorageCleanMarks1 = "0";
        }
        if (mCheckBoxStorageCleanAdequate.isChecked()) {
            mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            mTextViewMarksStorageClean.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            //setMarksFour(mStorageCleanMarksINT, mTextViewMarksStorageClean);
            mStorageCleanMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            mStorageCleanMarks2 = "0";
        }
        if (mCheckBoxStorageCleanDaily.isChecked()) {
            mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            mTextViewMarksStorageClean.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            //setMarksFour(mStorageCleanMarksINT, mTextViewMarksStorageClean);
            mStorageCleanMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            mStorageCleanMarks3 = "0";
        }
        if (mCheckBoxStorageCleanNoAccumilation.isChecked()) {
            mStorageCleanMarksINT = mStorageCleanMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            mTextViewMarksStorageClean.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            //setMarksFour(mStorageCleanMarksINT, mTextViewMarksStorageClean);
            mStorageCleanMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageCleanMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageClean, mStringMarks);
            mStorageCleanMarks4 = "0";
        }

        /*Wall maintenance*/
        if (mCheckBoxStorageContaminationFromToilets.isChecked()) {
            mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            mTextViewMarksStorageContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            //setMarksFour(mStorageContaminationMarksINT, mTextViewMarksStorageContamination);
            mStorageContaminationMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStorageContamination, mStringMarks);
            mStorageContaminationMarks1 = "0";
        }
        if (mCheckBoxStorageContaminationFromGarbage.isChecked()) {
            mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            mTextViewMarksStorageContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            //setMarksFour(mStorageContaminationMarksINT, mTextViewMarksStorageContamination);
            mStorageContaminationMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            mStorageContaminationMarks2 = "0";
        }
        if (mCheckBoxStorageContaminationFromSanitires.isChecked()) {
            mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            mTextViewMarksStorageContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            //setMarksFour(mStorageContaminationMarksINT, mTextViewMarksStorageContamination);
            mStorageContaminationMarks3 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            mStorageContaminationMarks3 = "0";
        }
        if (mCheckBoxStorageContaminationFromHzards.isChecked()) {
            mStorageContaminationMarksINT = mStorageContaminationMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            mTextViewMarksStorageContamination.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            //setMarksFour(mStorageContaminationMarksINT, mTextViewMarksStorageContamination);
            mStorageContaminationMarks4 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStorageContaminationMarksINT).toString();
            setMarkViewFour(mTextViewMarksStorageContamination, mStringMarks);
            mStorageContaminationMarks4 = "0";
        }

        /*pest Control*/
        if (mCheckBoxStoragePestControlNoRodents.isChecked()) {
            mStoragePestControlMarksINT = mStoragePestControlMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
            mTextViewMarksStoragePestControl.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStoragePestControl, mStringMarks);
            //setMarksFour(mStoragePestControlMarksINT, mTextViewMarksStoragePestControl);
            mStoragePestControlMarks1 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
            setMarkViewTwo(mTextViewMarksStoragePestControl, mStringMarks);
            mStoragePestControlMarks1 = "0";
        }
        if (mCheckBoxStoragePestControlNoPets.isChecked()) {
            mStoragePestControlMarksINT = mStoragePestControlMarksINT + 1;
            String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
            mTextViewMarksStoragePestControl.setText(mStringMarks);
            setMarkViewFour(mTextViewMarksStoragePestControl, mStringMarks);
            //setMarksFour(mStoragePestControlMarksINT, mTextViewMarksStoragePestControl);
            mStoragePestControlMarks2 = "1";
        } else {
            String mStringMarks = Integer.valueOf(mStoragePestControlMarksINT).toString();
            setMarkViewFour(mTextViewMarksStoragePestControl, mStringMarks);
            mStoragePestControlMarks2 = "0";
        }



    }
}
