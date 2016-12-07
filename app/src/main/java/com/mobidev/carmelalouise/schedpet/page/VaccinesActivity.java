package com.mobidev.carmelalouise.schedpet.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.mobidev.carmelalouise.schedpet.R;

public class VaccinesActivity extends AppCompatActivity {

    RecyclerView rvVaccines;
    Button buttonAddVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        rvVaccines = (RecyclerView) findViewById(R.id.rv_vaccines);
        buttonAddVaccine = (Button) findViewById(R.id.button_add_vaccine);
    }
}
