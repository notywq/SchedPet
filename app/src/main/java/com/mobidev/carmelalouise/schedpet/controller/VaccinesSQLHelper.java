package com.mobidev.carmelalouise.schedpet.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobidev.carmelalouise.schedpet.model.Pet;
import com.mobidev.carmelalouise.schedpet.model.Vaccine;

import java.util.ArrayList;

/**
 * Created by Carmela Louise on 11/26/2016.
 */

public class VaccinesSQLHelper extends SQLiteOpenHelper {

    public final static String SCHEMA = "vaccines";
    public final static int VERSION = 1;

    public VaccinesSQLHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Vaccine.TABLE
                + " (" + Pet.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                       + Vaccine.NAME + " TEXT, "
                       + Vaccine.DESCRIPTION + " TEXT, "
                       + Vaccine.DATE_VACCINATED + " DATE, "
                       + Vaccine.DATE_NEXT_DUE + " DATE, "
                       + Vaccine.ACCOMPLISHED + " BOOLEAN);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Pet.TABLE + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert
    public void insertVaccine(Vaccine vaccine){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Vaccine.NAME, vaccine.getName());
        cv.put(Vaccine.DESCRIPTION, vaccine.getDescription());
        cv.put(Vaccine.DATE_VACCINATED, vaccine.getDateVaccinated());
        cv.put(Vaccine.DATE_NEXT_DUE , vaccine.getDateNextDue());
        cv.put(Vaccine.ACCOMPLISHED, vaccine.isAccomplished());

        db.insert(Pet.TABLE, null, cv);
    }

    // update
    public void updateVaccine(Vaccine vaccine){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Vaccine.NAME, vaccine.getName());
        cv.put(Vaccine.DESCRIPTION, vaccine.getDescription());
        cv.put(Vaccine.DATE_VACCINATED, vaccine.getDateVaccinated());
        cv.put(Vaccine.DATE_NEXT_DUE , vaccine.getDateNextDue());
        cv.put(Vaccine.ACCOMPLISHED, vaccine.isAccomplished());

        db.update(Pet.TABLE, cv, Pet.ID + " = ? ",
                new String[]{vaccine.getId() + ""});
    }

    // delete
    public void deleteVaccine(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Vaccine.TABLE,
                  Vaccine.ID + "=? ",
                  new String[]{id+""});
    }

    // retrieve -> a single
    public Vaccine retrieveVaccine(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Vaccine.TABLE,
                 null, // null means get everything or *
                 " " + Vaccine.ID + " = ? ",
                 new String[]{ id+"" },
                 null, null, null
        );

        Vaccine vaccine = null;

        if(c.moveToFirst()){
            // this means that there is an item with that id
            // read the row/item
            vaccine = new Vaccine();
            vaccine.setId(c.getInt(c.getColumnIndex(Pet.ID)));
            vaccine.setName(
                    c.getString(c.getColumnIndex(Vaccine.NAME))
            );
            vaccine.setDescription(
                    c.getString(c.getColumnIndex(Vaccine.DESCRIPTION))
            );
            vaccine.setDateVaccinated(
                    c.getString(c.getColumnIndex(Vaccine.DATE_VACCINATED))
            );
            vaccine.setDateNextDue(
                    c.getString(c.getColumnIndex(Vaccine.DATE_NEXT_DUE))
            );
            vaccine.setAccomplished(
                    c.getString(c.getColumnIndex(Vaccine.ACCOMPLISHED))
            );
        }

        return vaccine;
    }

    public Cursor retrieveAllVaccinesCursor(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(
                Vaccine.TABLE,
                null, // *
                null, // selection string
                null, // selection args
                null, // group by
                null, // having
                null  // order by
        );
    }

    // retrieve -> all items
    public ArrayList<Vaccine> retrieveAllVaccines(){
        ArrayList<Vaccine> vaccines = new ArrayList<>();

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
                Vaccine vaccine = new Vaccine();
                vaccine.setId(c.getInt(c.getColumnIndex(Pet.ID)));
                vaccine.setName(
                        c.getString(c.getColumnIndex(Vaccine.NAME))
                );
                vaccine.setDescription(
                        c.getString(c.getColumnIndex(Vaccine.DESCRIPTION))
                );
                vaccine.setDateVaccinated(
                        c.getString(c.getColumnIndex(Vaccine.DATE_VACCINATED))
                );
                vaccine.setDateNextDue(
                        c.getString(c.getColumnIndex(Vaccine.DATE_NEXT_DUE))
                );
                vaccine.setAccomplished(
                        c.getString(c.getColumnIndex(Vaccine.ACCOMPLISHED))
                );
            }while(c.moveToNext());
        }

        return vaccines;
    }
}









