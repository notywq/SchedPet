package com.mobidev.carmelalouise.schedpet.model;

/**
 * Created by Carmela Louise on 11/28/2016.
 */

public class Vaccine {
    public static final String TABLE = "vaccines";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String DATE_VACCINATED = "dateVaccinated";
    public static final String PET_ID = "petId";

    private int id;
    private String name;
    private String description;
    private String dateVaccinated;
    private int petId;

    public Vaccine() {
    }

    public Vaccine(int id, String name, String description, String dateVaccinated, int petId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateVaccinated = dateVaccinated;
        this.petId = petId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateVaccinated() {
        return dateVaccinated;
    }

    public void setDateVaccinated(String dateVaccinated) {
        this.dateVaccinated = dateVaccinated;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}