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

public class PetProfileActivity extends AppCompatActivity {

    TextView tvProfileName;
    Button buttonViewDetails;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    PetsAdapter petsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        tvProfileName = (TextView) findViewById(R.id.tv_profile_name);
        buttonViewDetails = (Button) findViewById(R.id.button_view_details);
        final int id = getIntent().getExtras().getInt("id");
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        pet = petsSQLHelper.retrievePet(id);

        tvProfileName.setText(pet.getName());

        buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ViewPetDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("id", pet.getId());

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
        }
    }
}
