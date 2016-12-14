package com.mobidev.carmelalouise.schedpet.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.controller.PetsSQLHelper;
import com.mobidev.carmelalouise.schedpet.model.Pet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppointmentsActivity extends AppCompatActivity {

    Button buttonAddAppointment, buttonEditDeleteAppointment;
    Pet pet;
    PetsSQLHelper petsSQLHelper;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        buttonAddAppointment = (Button) findViewById(R.id.button_add_appointment);
        buttonEditDeleteAppointment = (Button) findViewById(R.id.button_edit_delete_appointment);

        pet = new Pet();
        petsSQLHelper = new PetsSQLHelper(getBaseContext());
        final int id = getIntent().getExtras().getInt("id");
        pet = petsSQLHelper.retrievePet(id);

        buttonAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendarEvent = Calendar.getInstance();
                Intent i = new Intent(Intent.ACTION_EDIT);
                i.setType("vnd.android.cursor.item/event");
                i.putExtra("beginTime", calendarEvent.getTimeInMillis());
                i.putExtra("allDay", true);
                i.putExtra("rule", "FREQ=YEARLY");
                i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                i.putExtra("title", pet.getName() + " - ");
                startActivity(i);
            }
        });

        buttonEditDeleteAppointment.setOnClickListener(new View.OnClickListener() {
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
}
