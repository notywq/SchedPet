package com.mobidev.carmelalouise.schedpet.page;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

import java.util.Calendar;

public class RegisterPetActivity extends AppCompatActivity {

    EditText etName, etBreed, etBirthday, etDescription;
    Button buttonDone;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    TextInputLayout nameWrapper, breedWrapper, descriptionWrapper, birthdayWrapper;
    Spinner spinnerSpecies;
    DatePicker datePicker;
    Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        etName = (EditText) findViewById(R.id.et_name);
        spinnerSpecies = (Spinner) findViewById(R.id.spinner_species);
        etBreed = (EditText) findViewById(R.id.et_breed);
        etBirthday = (EditText) findViewById(R.id.et_birthday);
        etDescription = (EditText) findViewById(R.id.et_description);
        buttonDone = (Button) findViewById(R.id.button_done);
        nameWrapper = (TextInputLayout) findViewById(R.id.name_wrapper);
        breedWrapper = (TextInputLayout) findViewById(R.id.breed_wrapper);
        descriptionWrapper = (TextInputLayout) findViewById(R.id.description_wrapper);
        birthdayWrapper = (TextInputLayout) findViewById(R.id.birthday_wrapper);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String species = String.valueOf(spinnerSpecies.getSelectedItem());
                String breed = etBreed.getText().toString();
                String birthday = etBirthday.getText().toString();
                String description = etDescription.getText().toString();

                if(!name.trim().isEmpty() && !species.trim().isEmpty() && !breed.trim().isEmpty()
                        && !birthday.trim().isEmpty() && !description.trim().isEmpty()){
                    pet.setName(name);
                    pet.setSpecies(species);
                    pet.setBreed(breed);
                    pet.setBirthday(birthday);
                    pet.setDescription(description);
                    petsSQLHelper.insertPet(pet);
                    finish();
                }

                else{
                    Snackbar.make(buttonDone, "Please enter values into the missing fields.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void addBirthdayToCalendar(int month, int day, int year)
    {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, pet.getName() + "'s Birthday");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
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

        etBirthday.setText(yearString + "-" + monthString + "-" + dayString);
    }
}
