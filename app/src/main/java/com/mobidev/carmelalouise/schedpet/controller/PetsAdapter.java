package com.mobidev.carmelalouise.schedpet.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobidev.carmelalouise.schedpet.page.PetProfileActivity;
import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.model.Pet;

import java.util.ArrayList;

/**
 * Created by Carmela Louise on 11/26/2016.
 */

public class PetsAdapter extends CursorRecyclerViewAdapter<PetsAdapter.PetViewHolder> {


    private Cursor petsCursor;
    private Context context;

    public PetsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(PetViewHolder viewHolder, Cursor cursor) {
        viewHolder.tvDisplayName.setText(
                cursor.getString(cursor.getColumnIndex(Pet.NAME))
        );
    }


    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.list_item_pet, parent, false);
        return new PetViewHolder(itemView);
    }

    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDisplayName;
        View container;

        public PetViewHolder(View itemView) {
            super(itemView);
            tvDisplayName = (TextView) itemView.findViewById(R.id.tv_display_name);
            container = itemView.findViewById(R.id.container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(view.getContext(),"PetID: "+getAdapterPosition(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, PetProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PetsSQLHelper sql = new PetsSQLHelper(context);
            ArrayList<Pet> pet = sql.retrieveAllPets();
            Pet currPet = pet.get(getAdapterPosition());

            intent.putExtra("id",currPet.getId());

            context.startActivity(intent);

        }

    }
}
