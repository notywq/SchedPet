package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsAdapter;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_ADD_CONTACT = 0;

    RecyclerView rvPets;
    FloatingActionButton buttonAdd;
    Button buttonViewAllAppointments;
    PetsSQLHelper petsSQLHelper;
    PetsAdapter petsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvPets = (RecyclerView) findViewById(R.id.rv_pets);
        buttonAdd = (FloatingActionButton) findViewById(R.id.button_add);
        buttonViewAllAppointments = (Button) findViewById(R.id.button_view_all_appointments);
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

        buttonViewAllAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 2);
                long time = cal.getTime().getTime();
                Uri.Builder builder =
                        CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                builder.appendPath(Long.toString(time));
                Intent intent =
                        new Intent(Intent.ACTION_VIEW, builder.build());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
