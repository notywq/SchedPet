package com.mobidev.carmelalouise.schedpet.page;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

public class RegisterPetActivity extends AppCompatActivity {

    EditText etName, etBreed, etBirthday, etDescription;
    Button buttonDone;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    TextInputLayout nameWrapper, breedWrapper, descriptionWrapper, birthdayWrapper;
    Spinner spinnerSpecies;


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
}
