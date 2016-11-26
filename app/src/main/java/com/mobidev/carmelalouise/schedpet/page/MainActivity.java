package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsAdapter;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_ADD_CONTACT = 0;

    RecyclerView rvPets;
    Button buttonAdd;
    PetsSQLHelper petsSQLHelper;
    PetsAdapter petsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPets = (RecyclerView) findViewById(R.id.rv_pets);
        buttonAdd = (Button) findViewById(R.id.button_add);
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        petsAdapter = new PetsAdapter(getBaseContext(), petsSQLHelper.retrieveAllPetsCursor());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);

        rvPets.setLayoutManager(linearLayoutManager);
        rvPets.setAdapter(petsAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), RegisterPetActivity.class), REQUEST_CODE_ADD_CONTACT);
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
