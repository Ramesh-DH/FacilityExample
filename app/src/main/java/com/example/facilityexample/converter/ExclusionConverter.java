package com.example.facilityexample.converter;

import androidx.room.TypeConverter;

import com.example.facilityexample.model.Exclusion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ExclusionConverter {
    @TypeConverter
    public static List<List<Exclusion>> storedExclusionToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Exclusion>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredSExclusion(List<List<Exclusion>> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
