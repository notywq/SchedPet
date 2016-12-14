package com.mobidev.carmelalouise.schedpet.page;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class EditPetActivity extends AppCompatActivity {

    EditText etName, etBreed, etBirthday, etDescription;
    Button buttonEditDone;
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
        setContentView(R.layout.activity_edit_pet);

        etName = (EditText) findViewById(R.id.et_edit_name);
        spinnerSpecies = (Spinner) findViewById(R.id.et_edit_species);
        etBreed = (EditText) findViewById(R.id.et_edit_breed);
        etBirthday = (EditText) findViewById(R.id.et_edit_birthday);
        etDescription = (EditText) findViewById(R.id.et_edit_description);
        buttonEditDone = (Button) findViewById(R.id.button_edit_done);
        nameWrapper = (TextInputLayout) findViewById(R.id.edit_name_wrapper);
        breedWrapper = (TextInputLayout) findViewById(R.id.edit_breed_wrapper);
        descriptionWrapper = (TextInputLayout) findViewById(R.id.edit_description_wrapper);
        birthdayWrapper = (TextInputLayout) findViewById(R.id.edit_birthday_wrapper);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        final int id = getIntent().getExtras().getInt("id");
        pet = petsSQLHelper.retrievePet(id);

        etName.setText(pet.getName());
        spinnerSpecies.setSelection(getIndex());
        etBreed.setText(pet.getBreed());
        etBirthday.setText(pet.getBirthday());
        etDescription.setText(pet.getDescription());


        buttonEditDone.setOnClickListener(new View.OnClickListener() {
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
                    petsSQLHelper.updatePet(pet);
                    finish();
                }

                else{
                    Snackbar.make(buttonEditDone, "Please enter values into the missing fields.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    private int getIndex()
    {
        int index = 0;
        for (int i=0;i<spinnerSpecies.getCount();i++){
            if (spinnerSpecies.getItemAtPosition(i).toString().equalsIgnoreCase(pet.getSpecies())){
                index = i;
            }
        }
        return index;
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
