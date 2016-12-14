package com.mobidev.carmelalouise.schedpet.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobidev.carmelalouise.schedpet.R;
import com.mobidev.carmelalouise.schedpet.model.Pet;
import com.mobidev.carmelalouise.schedpet.page.PetProfileActivity;

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
        viewHolder.tvType.setText(cursor.getString(cursor.getColumnIndex(Pet.BREED)));

        if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Dog"))
            viewHolder.image.setImageResource(R.drawable.dog);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Cat"))
            viewHolder.image.setImageResource(R.drawable.cat);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Bird"))
            viewHolder.image.setImageResource(R.drawable.bird);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Fish"))
            viewHolder.image.setImageResource(R.drawable.fish);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Insect"))
            viewHolder.image.setImageResource(R.drawable.insect);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Reptile"))
            viewHolder.image.setImageResource(R.drawable.reptile);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Rodent"))
            viewHolder.image.setImageResource(R.drawable.rodent);
        else if(cursor.getString(cursor.getColumnIndex(Pet.SPECIES)).equals("Others"))
            viewHolder.image.setImageResource(R.drawable.unique);
    }


    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.list_item_pet, parent, false);
        return new PetViewHolder(itemView);
    }

    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDisplayName;
        TextView tvType;
        ImageView image;
        View container;

        public PetViewHolder(View itemView) {
            super(itemView);
            tvDisplayName = (TextView) itemView.findViewById(R.id.tv_display_name);
            tvType = (TextView) itemView.findViewById(R.id.tv_display_type);
            container = itemView.findViewById(R.id.container);
            image = (ImageView) itemView.findViewById(R.id.tv_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
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
