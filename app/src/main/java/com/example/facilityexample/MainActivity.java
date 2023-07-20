package com.example.facilityexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.facilityexample.adapter.CustomFacilityListAdapter;
import com.example.facilityexample.adapter.ExclusionAdapter;
import com.example.facilityexample.databinding.ActivityMainBinding;
import com.example.facilityexample.model.Exclusion;
import com.example.facilityexample.model.Facility;
import com.example.facilityexample.model.Option;
import com.example.facilityexample.presenter.MainPresenter;
import com.example.facilityexample.view.MainView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainPresenter.class";
    private ActivityMainBinding activityMainBinding;
    private HashMap<Facility, List<Option>> expandableListDetail;
    private MainPresenter mainPresenter;
    private CustomFacilityListAdapter customFacilityListAdapter;
    private ExclusionAdapter exclusionAdapter;
    private boolean isSelected;
    int lastExpandedPosition = -1;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Log.i(TAG,"onCreate");
        mainPresenter = new MainPresenter();
        mainPresenter.initialize(MainActivity.this, MainActivity.this);
    }

    @Override
    public void setAdapterList(List<Facility> facilityList, List<Exclusion> exclusionList) {
        Log.i(TAG,"setAdapterList...");
        activityMainBinding.progressBar.setVisibility(View.GONE);
        activityMainBinding.scrollview.setVisibility(View.VISIBLE);
        activityMainBinding.llOne.setVisibility(View.VISIBLE);
        expandableListDetail = mainPresenter.getData();
        customFacilityListAdapter = new CustomFacilityListAdapter(this, facilityList, expandableListDetail);
        exclusionAdapter = new ExclusionAdapter(exclusionList, this);
        activityMainBinding.recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerViewList.setAdapter(exclusionAdapter);
        activityMainBinding.expandableListView.setAdapter(customFacilityListAdapter);
        activityMainBinding.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Log.i(TAG,"list expanded...");
                if(lastExpandedPosition != -1 && lastExpandedPosition!=i){
                    activityMainBinding.expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        activityMainBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Facility facility = facilityList.get(i);
                isSelected = true;
                index = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(i, i1));
                expandableListView.setItemChecked(index, true);
                Option option = expandableListDetail.get(facility).get(i1);
                List<Exclusion> exclusion = mainPresenter.getExclusionList();
                for (Exclusion exclusion1 : exclusion) {
                    if (facility.getFacility_id() == exclusion1.getFacilityId()
                            && option.getId() == exclusion1.getOptionsId()) {

                        isSelected = false;
                        Toast.makeText(MainActivity.this, "This Item is in exclusion list please check below list \n and select other", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (isSelected) {
                    Toast.makeText(MainActivity.this, "You have Selected Item..", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       Log.i(TAG,"onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onShowError(String s) {
        Log.i(TAG,"onShowError"+s);
        activityMainBinding.llOne.setVisibility(View.GONE);
        activityMainBinding.progressBar.setVisibility(View.GONE);
        activityMainBinding.scrollview.setVisibility(View.GONE);
        activityMainBinding.errorTv.setVisibility(View.VISIBLE);
        activityMainBinding.errorTv.setText(s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}