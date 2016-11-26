package com.mobidev.carmelalouise.schedpet.page;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

public class EditPetActivity extends AppCompatActivity {

    EditText etName, etSpecies, etBreed, etBirthday, etDescription;
    Button buttonEditDone;
    Pet pet;
    PetsSQLHelper petsSQLHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        etName = (EditText) findViewById(R.id.et_edit_name);
        etSpecies = (EditText) findViewById(R.id.et_edit_species);
        etBreed = (EditText) findViewById(R.id.et_edit_breed);
        etBirthday = (EditText) findViewById(R.id.et_edit_birthday);
        etDescription = (EditText) findViewById(R.id.et_edit_description);
        buttonEditDone = (Button) findViewById(R.id.button_edit_done);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        final int id = getIntent().getExtras().getInt("id");
        pet = petsSQLHelper.retrievePet(id);

        etName.setText(pet.getName());
        etSpecies.setText(pet.getSpecies());
        etBreed.setText(pet.getBreed());
        etBirthday.setText(pet.getBirthday());
        etDescription.setText(pet.getDescription());

        buttonEditDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String species = etSpecies.getText().toString();
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
}
