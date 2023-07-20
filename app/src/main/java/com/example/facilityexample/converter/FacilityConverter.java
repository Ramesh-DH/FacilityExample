package com.example.facilityexample.converter;

import androidx.room.TypeConverter;

import com.example.facilityexample.model.Facility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class FacilityConverter {
    @TypeConverter
    public static List<Facility> storedFacilityToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Facility>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredSFacility(List<Facility> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }

}
