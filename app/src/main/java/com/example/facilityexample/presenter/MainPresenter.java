package com.example.facilityexample.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.facilityexample.db.FacilityDatabase;
import com.example.facilityexample.model.Exclusion;
import com.example.facilityexample.model.Facility;
import com.example.facilityexample.model.Option;
import com.example.facilityexample.model.Response;
import com.example.facilityexample.service.FacilityBuilderApi;
import com.example.facilityexample.service.FacilityService;
import com.example.facilityexample.view.MainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class MainPresenter {

    private static String TAG = "MainPresenter.class";
    private CompositeDisposable compositeDisposable;
    private MainView view;
    private Context context;
    private FacilityService facilityService;
    private FacilityDatabase facilityDatabase;
    private Response responseData;
    private List<Facility> facilityList;
    private List<Exclusion> exclusionList;

    public void initialize(MainView view, Context context) {
        this.view = view;
        this.context = context;
        Log.i(TAG, "initialize...");
        getFacilityList();
    }

    private void getFacilityList() {
        facilityList = new ArrayList<>();
        exclusionList = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
        facilityService = FacilityBuilderApi.getInstance().create(FacilityService.class);
        Observable<Response> responseObservable = facilityService.getLists1();
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> response)
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "subscribed...");
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(@NonNull Response response) {
                        Log.i(TAG, "onNext...");
                        responseData = response;
                        facilityDatabase = FacilityDatabase.getInstance(context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, e.toString());
                        compositeDisposable.dispose();
                        view.onShowError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete...");
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                saveToDB(responseData);
                                facilityList.addAll(facilityDatabase.facilityDao().getFacilityList());
                                exclusionList.addAll(facilityDatabase.facilityDao().getExclusionList());
                                view.setAdapterList(facilityList, exclusionList);
                            }
                        });
                    }
                });
    }

    private void saveToDB(Response response) {
        Log.i(TAG, "saveToDB...");
        if (response != null) {
            if (!facilityDatabase.facilityDao().getFacilityList().isEmpty()) {
                clearAll();
            }
            for (Facility facilityListDatum : response.getFacilities()) {
                for (Option option : facilityListDatum.options) {
                    facilityDatabase.facilityDao().insert(facilityListDatum);
                    facilityDatabase.facilityDao().insert(option);
                }
            }
            for (List<Exclusion> exclusions : response.getExclusions()) {
                facilityDatabase.facilityDao().insert(exclusions);

            }
        }
    }

    private void clearAll() {
        Log.i(TAG, "clearAll");
        facilityDatabase.facilityDao().clearAllFacilty(facilityDatabase.facilityDao().getFacilityList());
        facilityDatabase.facilityDao().clearAllOptions(facilityDatabase.facilityDao().getOptions());
        facilityDatabase.facilityDao().clearAllExclusion(facilityDatabase.facilityDao().getExclusionList());
    }

    public HashMap<Facility, List<Option>> getData() {
        compositeDisposable.dispose();
        HashMap<Facility, List<Option>> expandableListDetail = new HashMap<>();
        for (Facility facility : facilityList) {
            expandableListDetail.put(facility, facility.options);
        }

        return expandableListDetail;
    }


    public List<Exclusion> getExclusionList() {
        return facilityDatabase.facilityDao().getExclusionList();
    }
}
