package com.example.facilityexample.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Facility {

    @SerializedName("facility_id")
    @PrimaryKey(autoGenerate = true)
    public int facility_id;
    @SerializedName("name")
    public String name;
    @SerializedName("options")
    public List<Option> options;

    public Facility(int facility_id, String name) {
        this.facility_id = facility_id;
        this.name = name;
    }

    public int getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(int facility_id) {
        this.facility_id = facility_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
