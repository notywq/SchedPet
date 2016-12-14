package com.mobidev.carmelalouise.schedpet.page;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;
import com.mobidev.carmelalouise.schedpet.model.Vaccine;

import java.util.Calendar;

public class AddVaccineActivity extends AppCompatActivity {

    EditText etVaccineName, etVaccineDescription, etDateVaccinated;
    TextInputLayout wrapperVaccineName, wrapperVaccineDescription, wrapperVaccineDateVaccinated;
    Button buttonVaccineDone;
    DatePicker datePicker;
    Calendar calendar;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    Vaccine vaccine;
    VaccinesSQLHelper vaccinesSQLHelper;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        etVaccineName = (EditText) findViewById(R.id.et_vaccineName);
        etVaccineDescription = (EditText) findViewById(R.id.et_vaccineDescription);
        etDateVaccinated = (EditText) findViewById(R.id.et_dateVaccinated);
        buttonVaccineDone = (Button) findViewById(R.id.button_vaccineDone);
        wrapperVaccineName = (TextInputLayout) findViewById(R.id.vaccine_name_wrapper);
        wrapperVaccineDescription = (TextInputLayout) findViewById(R.id.vaccine_description_wrapper);
        wrapperVaccineDateVaccinated = (TextInputLayout) findViewById(R.id.vaccine_date_vaccinated_wrapper);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        final int id = getIntent().getExtras().getInt("id");
        pet = petsSQLHelper.retrievePet(id);
        vaccine = new Vaccine();
        vaccinesSQLHelper = new VaccinesSQLHelper(getBaseContext());

        buttonVaccineDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etVaccineName.getText().toString();
                String description = etVaccineDescription.getText().toString();
                String dateVaccinated = etDateVaccinated.getText().toString();
                final int id = getIntent().getExtras().getInt("id");

                if(!name.trim().isEmpty() &&!description.trim().isEmpty()
                        && !dateVaccinated.trim().isEmpty()){
                    vaccine.setName(name);
                    vaccine.setDescription(description);
                    vaccine.setDateVaccinated(dateVaccinated);
                    vaccine.setPetId(id);
                    vaccinesSQLHelper.insertVaccine(vaccine);
                    finish();
                }

                else{
                    Snackbar.make(buttonVaccineDone, "Please enter values into the missing fields.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {

        String yearString = year + "";
        String monthString = month + "";
        String dayString = day + "";

        if(month < 10){

            monthString = "0" + monthString;
        }
        if(day < 10){

            dayString = "0" + dayString ;
        }

        etDateVaccinated.setText(yearString + "-" + monthString + "-" + dayString);
    }
}
