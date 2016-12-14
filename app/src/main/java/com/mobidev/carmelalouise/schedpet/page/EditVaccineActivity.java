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
import android.widget.Toast;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Vaccine;

import java.util.Calendar;

public class EditVaccineActivity extends AppCompatActivity {

    EditText etName, etDescription, etDateVaccinated;
    Button buttonEditDone;
    Vaccine vaccine;
    VaccinesSQLHelper vaccinesSQLHelper;
    TextInputLayout nameWrapper, descriptionWrapper, dateVaccinatedWrapper;
    DatePicker datePicker;
    Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vaccine);

        etName = (EditText) findViewById(R.id.et_edit_vaccine_name);
        etDescription = (EditText) findViewById(R.id.et_edit_vaccine_description);
        etDateVaccinated = (EditText) findViewById(R.id.et_edit_date_vaccinated);
        buttonEditDone = (Button) findViewById(R.id.button_edit_vaccine_done);
        nameWrapper = (TextInputLayout) findViewById(R.id.edit_vaccine_name_wrapper);
        descriptionWrapper = (TextInputLayout) findViewById(R.id.edit_vaccine_description_wrapper);
        dateVaccinatedWrapper = (TextInputLayout) findViewById(R.id.edit_date_vaccinated_wrapper);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        vaccinesSQLHelper = new VaccinesSQLHelper(getBaseContext());
        final int id = getIntent().getExtras().getInt("id");
        vaccine = vaccinesSQLHelper.retrieveVaccine(id);

        etName.setText(vaccine.getName());
        etDescription.setText(vaccine.getDescription());
        etDateVaccinated.setText(vaccine.getDateVaccinated());

        buttonEditDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                String dateVaccinated = etDateVaccinated.getText().toString();

                if(!name.trim().isEmpty() && !description.trim().isEmpty() && !dateVaccinated.trim().isEmpty()){
                    vaccine.setName(name);
                    vaccine.setDescription(description);
                    vaccine.setDateVaccinated(dateVaccinated);
                    vaccinesSQLHelper.updateVaccine(vaccine);
                    finish();
                }

                else{
                    Snackbar.make(buttonEditDone, "Please enter values into the missing fields.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
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
