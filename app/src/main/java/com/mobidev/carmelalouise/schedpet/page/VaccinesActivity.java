package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesAdapter;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

public class VaccinesActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_ADD_CONTACT = 0;

    RecyclerView rvVaccines;
    FloatingActionButton buttonAddVaccine;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    VaccinesAdapter vaccinesAdapter;
    VaccinesSQLHelper vaccinesSQLHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines2);

        rvVaccines = (RecyclerView) findViewById(R.id.rv_vaccines);
        buttonAddVaccine = (FloatingActionButton) findViewById(R.id.button_add_vaccine);
        final int pet_id = getIntent().getExtras().getInt("id");

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        pet = petsSQLHelper.retrievePet(pet_id);

        vaccinesSQLHelper = new VaccinesSQLHelper(getBaseContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);

        rvVaccines.setLayoutManager(linearLayoutManager);
        rvVaccines.setAdapter(vaccinesAdapter);

        buttonAddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddVaccineActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", pet.getId());

                getBaseContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(vaccinesAdapter!=null)
        {
            final int pet_id = getIntent().getExtras().getInt("id");
            Log.i("PetID: ", pet_id+"");
            vaccinesAdapter.changeCursor(vaccinesSQLHelper.retrieveAllVaccinesPerPetCursor(pet_id));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
