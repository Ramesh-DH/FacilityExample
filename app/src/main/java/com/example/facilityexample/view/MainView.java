package com.example.facilityexample.view;

import com.example.facilityexample.model.Exclusion;
import com.example.facilityexample.model.Facility;

import java.util.List;

public interface MainView {

    void setAdapterList(List<Facility> facilityList, List<Exclusion> exclusionList);

    void onShowError(String s);

}
