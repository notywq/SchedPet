package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesAdapter;
import com.mobidev.carmelalouise.schedpet.controller.VaccinesSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Vaccine;

public class VaccineDetailsActivity extends AppCompatActivity {

    TextView tvVaccineName, tvVaccineDescription, tvVaccineDateVaccinated;
    Button buttonEditVaccine, buttonDeleteVaccine;
    VaccinesSQLHelper vaccinesSQLHelper;
    VaccinesAdapter vaccinesAdapter;
    Vaccine vaccine;
    int id, pet_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);

        tvVaccineName = (TextView) findViewById(R.id.tv_vaccine_name);
        tvVaccineDescription = (TextView) findViewById(R.id.tv_vaccine_description);
        tvVaccineDateVaccinated = (TextView) findViewById(R.id.tv_vaccine_date_vaccinated);
        buttonEditVaccine = (Button) findViewById(R.id.button_edit_vaccine);
        buttonDeleteVaccine = (Button) findViewById(R.id.button_delete_vaccine);

        id = getIntent().getExtras().getInt("id");
        pet_id = getIntent().getExtras().getInt("pet_id");

        vaccinesSQLHelper = new VaccinesSQLHelper(getBaseContext());
        vaccinesAdapter = new VaccinesAdapter(getBaseContext(), vaccinesSQLHelper.retrieveAllVaccinesPerPetCursor(pet_id));

        vaccine = vaccinesSQLHelper.retrieveVaccine(id);

        tvVaccineName.setText("Vaccine: " + vaccine.getName());
        tvVaccineDescription.setText("Description: "+ vaccine.getDescription());
        tvVaccineDateVaccinated.setText("Date Vaccinated (YYYY-MM-DD): "+ vaccine.getDateVaccinated());

        buttonDeleteVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaccinesSQLHelper.deleteVaccine(id);

                finish();
            }
        });

        buttonEditVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditVaccineActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", vaccine.getId());
                intent.putExtra("pet_id", vaccine.getPetId());

                getBaseContext().startActivity(intent);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(vaccinesAdapter!=null)
        {
            vaccine = vaccinesSQLHelper.retrieveVaccine(id);
            tvVaccineName.setText("Vaccine: " + vaccine.getName());
            tvVaccineDescription.setText("Description: "+ vaccine.getDescription());
            tvVaccineDateVaccinated.setText("Date Vaccinated (YYYY-MM-DD): "+ vaccine.getDateVaccinated());

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
