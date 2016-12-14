package com.mobidev.carmelalouise.schedpet.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.model.Pet;
import com.mobidev.carmelalouise.schedpet.model.Vaccine;
import com.mobidev.carmelalouise.schedpet.page.VaccineDetailsActivity;

import java.util.ArrayList;

/**
 * Created by Carmela Louise on 11/28/2016.
 */

public class VaccinesAdapter extends CursorRecyclerViewAdapter<VaccinesAdapter.VaccineViewHolder> {
    private Cursor vaccinessCursor;
    private Context context;

    public VaccinesAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(VaccineViewHolder viewHolder, Cursor cursor) {
        viewHolder.tvDisplayVaccineName.setText(
                cursor.getString(cursor.getColumnIndex(Vaccine.NAME))
        );
    }

    @Override
    public VaccineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_vaccine, parent, false);
        return new VaccinesAdapter.VaccineViewHolder(itemView);
    }

    public class VaccineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDisplayVaccineName;
        View containerVaccine;

        public VaccineViewHolder(View itemView) {
            super(itemView);
            tvDisplayVaccineName = (TextView) itemView.findViewById(R.id.tv_display_vaccine_name);
            containerVaccine = itemView.findViewById(R.id.container_vaccine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, VaccineDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PetsSQLHelper petsql = new PetsSQLHelper(context);
            ArrayList<Pet> pet = petsql.retrieveAllPets();
            Pet currPet = pet.get(getAdapterPosition());

            intent.putExtra("id",currPet.getId());

            VaccinesSQLHelper sql = new VaccinesSQLHelper(context);
            ArrayList<Vaccine> vaccine = sql.retrieveAllVaccinesPerPet(currPet.getId());
            Vaccine currVaccine = vaccine.get(getAdapterPosition());

            intent.putExtra("id",currVaccine.getId());
            intent.putExtra("pet_id", currPet.getId());

            context.startActivity(intent);
        }
    }
}
