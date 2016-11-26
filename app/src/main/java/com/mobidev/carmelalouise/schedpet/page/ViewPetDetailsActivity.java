package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsAdapter;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

public class ViewPetDetailsActivity extends AppCompatActivity {

    TextView tvName, tvSpecies, tvBreed, tvBirthday, tvDescription;
    Button buttonEdit, buttonDelete;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    PetsAdapter petsAdapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet_details);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvSpecies = (TextView) findViewById(R.id.tv_species);
        tvBreed = (TextView) findViewById(R.id.tv_breed);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonDelete = (Button) findViewById(R.id.button_delete);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        id = getIntent().getExtras().getInt("id");
        pet = petsSQLHelper.retrievePet(id);
        petsAdapter = new PetsAdapter(getBaseContext(), petsSQLHelper.retrieveAllPetsCursor());

        tvName.setText("Name: " + pet.getName());
        tvSpecies.setText("Species: "+ pet.getSpecies());
        tvBreed.setText("Breed: " + pet.getBreed());
        tvBirthday.setText("Birthday: "+ pet.getBirthday());
        tvDescription.setText("Description: "+ pet.getDescription());


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditPetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", pet.getId());

                getBaseContext().startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petsSQLHelper.deletePet(id);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(petsAdapter!=null)
        {
            petsAdapter.changeCursor(petsSQLHelper.retrieveAllPetsCursor());
            pet = petsSQLHelper.retrievePet(id);
            tvName.setText("Name: " + pet.getName());
            tvSpecies.setText("Species: "+ pet.getSpecies());
            tvBreed.setText("Breed: " + pet.getBreed());
            tvBirthday.setText("Birthday: "+ pet.getBirthday());
            tvDescription.setText("Description: "+ pet.getDescription());
        }
    }
}
