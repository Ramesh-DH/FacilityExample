package com.example.facilityexample.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.facilityexample.converter.Converter;
import com.example.facilityexample.converter.ExclusionConverter;
import com.example.facilityexample.converter.FacilityConverter;
import com.example.facilityexample.dao.FacilityDao;
import com.example.facilityexample.model.Exclusion;
import com.example.facilityexample.model.Facility;
import com.example.facilityexample.model.Option;
import com.example.facilityexample.model.Response;

@Database(entities = {Response.class, Exclusion.class, Facility.class, Option.class,}, version = 1,exportSchema = false)
@TypeConverters({Converter.class, FacilityConverter.class, ExclusionConverter.class})
public abstract class FacilityDatabase extends RoomDatabase {

    private static String DB_NAME = "facility.db";
    private static FacilityDatabase facilityDatabase = null;

    public abstract FacilityDao facilityDao();

    public static synchronized FacilityDatabase getInstance(Context context) {
        if(facilityDatabase == null) {
            facilityDatabase = Room.databaseBuilder(context.getApplicationContext(),FacilityDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return facilityDatabase;
    }
    
    
}
