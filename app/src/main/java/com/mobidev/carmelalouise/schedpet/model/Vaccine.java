package com.mobidev.carmelalouise.schedpet.model;

import java.util.Date;

/**
 * Created by Carmela Louise on 11/28/2016.
 */

public class Vaccine {
    public static final String TABLE = "vaccines";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String DATE_VACCINATED = "dateVaccinated";
    public static final String DATE_NEXT_DUE = "dateNextDue";
    public static final String ACCOMPLISHED = "accomplished";

    private int id;
    private String name;
    private String description;
    private String dateVaccinated;
    private String dateNextDue;
    private String accomplished;

    public Vaccine() {
    }

    public Vaccine(int id, String name, String description, String dateVaccinated, String dateNextDue, String accomplished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateVaccinated = dateVaccinated;
        this.dateNextDue = dateNextDue;
        this.accomplished = accomplished;
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

    public String getDateNextDue() {
        return dateNextDue;
    }

    public void setDateNextDue(String dateNextDue) {
        this.dateNextDue = dateNextDue;
    }

    public String isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(String accomplished) {
        this.accomplished = accomplished;
    }
}
