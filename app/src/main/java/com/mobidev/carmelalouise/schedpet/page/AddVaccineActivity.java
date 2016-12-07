package com.mobidev.carmelalouise.schedpet.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.mobidev.carmelalouise.schedpet.R;

public class AddVaccineActivity extends AppCompatActivity {

    EditText etVaccineName, etVaccineDescription, etDateVaccinated, etDateNextDue;
    RadioButton rbYes, rbNo;
    Button buttonVaccineDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        etVaccineName = (EditText) findViewById(R.id.et_vaccineName);
        etVaccineDescription = (EditText) findViewById(R.id.et_vaccineDescription);
        etDateVaccinated = (EditText) findViewById(R.id.et_dateVaccinated);
        etDateNextDue = (EditText) findViewById(R.id.et_dateNextDue);
        rbYes = (RadioButton) findViewById(R.id.rb_yes);
        rbNo = (RadioButton) findViewById(R.id.rb_no);
        buttonVaccineDone = (Button) findViewById(R.id.button_vaccineDone);


    }
}
