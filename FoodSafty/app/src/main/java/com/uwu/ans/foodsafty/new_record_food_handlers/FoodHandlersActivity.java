package com.uwu.ans.foodsafty.new_record_food_handlers;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.uwu.ans.foodsafty.R;

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

    /*food handlers uniform*/
    @BindView(R.id.marks_uniform)
    TextView mTextViewMarksUniform;
    @BindView(R.id.uniform_apron_cap)
    CheckBox mCheckBoxUniformAprnCap;
    @BindView(R.id.uniform_other)
    CheckBox mCheckBoxUniformOther;

    /*diseases*/
    @BindView(R.id.marks_diseases)
    TextView mTextViewMarksDisease;
    @BindView(R.id.diseases_no_contagious_suffering_just_cured)
    CheckBox mCheckBoxDiseaseNoContagious;
    @BindView(R.id.diseases_no_fbd_suffering_just_curedt)
    CheckBox mCheckBoxDiseaseNoFbd;

    /*training certification*/
    @BindView(R.id.marks_health_training_certification)
    TextView mTextViewMarksTrainingCertification;
    @BindView(R.id.health_training_certification_have_for_handlers)
    CheckBox mCheckBoxTrainingCertificationHaveForHandlers;
    @BindView(R.id.health_training_certification_have_for_owners)
    CheckBox mCheckBoxTrainingCertificationHaveForOwners;


    @BindView(R.id.food_handler_comments)
    EditText mEditTextComments;

    @BindView(R.id.food_handler_total_marks)
    TextView mTextViewTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_handlers);
    }
}
