package com.mobidev.carmelalouise.schedpet.model;

/**
 * Created by Carmela Louise on 11/26/2016.
 */

public class Pet {
    public static final String TABLE = "pet";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SPECIES = "species";
    public static final String BREED = "breed";
    public static final String BIRTHDAY = "birthday";
    public static final String DESCRIPTION = "description";
    public static final String BIRTHDAY_EVENT_ID = "birthdayEventId";


    private int id;
    private String name;
    private String species;
    private String breed;
    private String birthday;
    private String description;
    private String birthdayEventId;

    public Pet() {
    }

    public Pet(int id, String name, String species, String breed, String birthday, String description, String birthdayEventId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthday = birthday;
        this.description = description;
        this.birthdayEventId = birthdayEventId;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthdayEventId() {
        return birthdayEventId;
    }

    public void setBirthdayEventId(String birthdayEventId) {
        this.birthdayEventId = birthdayEventId;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", birthdayEventId='" + birthdayEventId + '\'' +
                '}';
    }
}
