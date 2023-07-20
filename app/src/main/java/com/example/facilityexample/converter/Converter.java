package com.example.facilityexample.converter;

import androidx.room.TypeConverter;

import com.example.facilityexample.model.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converter {
    @TypeConverter
    public static List<Option> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Option>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<Option> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }


}

