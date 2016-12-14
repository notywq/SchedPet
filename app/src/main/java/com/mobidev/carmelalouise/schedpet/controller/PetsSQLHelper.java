package com.mobidev.carmelalouise.schedpet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobidev.carmelalouise.schedpet.model.Pet;

import java.util.ArrayList;

/**
 * Created by Carmela Louise on 11/26/2016.
 */

public class PetsSQLHelper extends SQLiteOpenHelper {

    public final static String SCHEMA = "pets";
    public final static int VERSION = 1;

    public PetsSQLHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Pet.TABLE
                + " (" + Pet.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                       + Pet.NAME + " TEXT, "
                       + Pet.SPECIES + " TEXT, "
                       + Pet.BREED + " TEXT, "
                       + Pet.BIRTHDAY + " DATE, "
                       + Pet.DESCRIPTION + " TEXT, "
                       + Pet.BIRTHDAY_EVENT_ID + " TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Pet.TABLE + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert
    public void insertPet(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Pet.NAME, pet.getName());
        cv.put(Pet.SPECIES, pet.getSpecies());
        cv.put(Pet.BREED, pet.getBreed());
        cv.put(Pet.BIRTHDAY, pet.getBirthday());
        cv.put(Pet.DESCRIPTION, pet.getDescription());
        cv.put(Pet.BIRTHDAY_EVENT_ID, pet.getBirthdayEventId());

        db.insert(Pet.TABLE, null, cv);
    }

    // update
    public void updatePet(Pet pet){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Pet.NAME, pet.getName());
        cv.put(Pet.SPECIES, pet.getSpecies());
        cv.put(Pet.BREED, pet.getBreed());
        cv.put(Pet.BIRTHDAY, pet.getBirthday());
        cv.put(Pet.DESCRIPTION, pet.getDescription());
        cv.put(Pet.BIRTHDAY_EVENT_ID, pet.getBirthdayEventId());

        db.update(Pet.TABLE, cv, Pet.ID + " = ? ",
                new String[]{pet.getId() + ""});
    }

    // delete
    public void deletePet(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Pet.TABLE,
                  Pet.ID + "=? ",
                  new String[]{id+""});
    }

    // retrieve -> a single
    public Pet retrievePet(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Pet.TABLE,
                 null, // null means get everything or *
                 " " + Pet.ID + " = ? ",
                 new String[]{ id+"" },
                 null, null, null
        );

        Pet pet = null;

        if(c.moveToFirst()){
            // this means that there is an item with that id
            // read the row/item
            pet = new Pet();
            pet.setId(
                    c.getInt(c.getColumnIndex(Pet.ID))
            );
            pet.setName(
                    c.getString(c.getColumnIndex(Pet.NAME))
            );
            pet.setSpecies(
                    c.getString(c.getColumnIndex(Pet.SPECIES))
            );
            pet.setBreed(
                    c.getString(c.getColumnIndex(Pet.BREED))
            );
            pet.setBirthday(
                    c.getString(c.getColumnIndex(Pet.BIRTHDAY))
            );
            pet.setDescription(
                    c.getString(c.getColumnIndex(Pet.DESCRIPTION))
            );
            pet.setBirthdayEventId(
                    c.getString(c.getColumnIndex(Pet.BIRTHDAY_EVENT_ID))
            );
        }

        return pet;
    }

    public Cursor retrieveAllPetsCursor(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(
                Pet.TABLE,
                null, // *
                null, // selection string
                null, // selection args
                null, // group by
                null, // having
                null  // order by
        );
    }

    // retrieve -> all items
    public ArrayList<Pet> retrieveAllPets(){
        ArrayList<Pet> pets = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Pet.TABLE,
                 null, // columns
                 null, // selection string
                 null, // selection args (values to ?s)
                 null, // group by
                 null, // having
                 null  // order by
                );

        if(c.moveToFirst()){
            do {
                Pet pet = new Pet();
                pet.setId(
                        c.getInt(c.getColumnIndex(Pet.ID))
                );
                pet.setName(
                        c.getString(c.getColumnIndex(Pet.NAME))
                );
                pet.setSpecies(
                        c.getString(c.getColumnIndex(Pet.SPECIES))
                );
                pet.setBreed(
                        c.getString(c.getColumnIndex(Pet.BREED))
                );
                pet.setBirthday(
                        c.getString(c.getColumnIndex(Pet.BIRTHDAY))
                );
                pet.setDescription(
                        c.getString(c.getColumnIndex(Pet.DESCRIPTION))
                );
                pet.setBirthdayEventId(
                        c.getString(c.getColumnIndex(Pet.BIRTHDAY_EVENT_ID))
                );
                pets.add(pet);
            }while(c.moveToNext());
        }

        return pets;
    }
}









