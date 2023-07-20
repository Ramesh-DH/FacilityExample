package com.example.facilityexample.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.facilityexample.model.Exclusion;
import com.example.facilityexample.model.Facility;
import com.example.facilityexample.model.Option;

import java.util.List;

@Dao
public interface FacilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Facility facility);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Exclusion> exclusion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Option option);

    @Query("Select * from Option")
    List<Option> getOptions();

    @Query("Select * from Facility")
    List<Facility> getFacilityList();

    @Query("Select * from Exclusion")
    List<Exclusion> getExclusionList();

    @Delete
    void clearAllFacilty(List<Facility> facilityList);

    @Delete
    void clearAllOptions(List<Option> optionList);

    @Delete
    void clearAllExclusion(List<Exclusion> exclusionList);

}
