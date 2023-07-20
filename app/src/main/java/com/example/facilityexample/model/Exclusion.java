package com.example.facilityexample.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Exclusion {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @SerializedName("facility_id")
    @Expose
    public int facilityId;
    @SerializedName("options_id")
    @Expose
    public int optionsId;

    public Exclusion(int facilityId, int optionsId) {
        this.facilityId = facilityId;
        this.optionsId = optionsId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(int optionsId) {
        this.optionsId = optionsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
