package com.example.facilityexample.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.facilityexample.R;
import com.example.facilityexample.databinding.AdapterFacilityViewBinding;
import com.example.facilityexample.databinding.ListGroupBinding;
import com.example.facilityexample.model.Facility;
import com.example.facilityexample.model.Option;

import java.util.HashMap;
import java.util.List;

public class CustomFacilityListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private final List<Facility> expandableListTitle;
    private final HashMap<Facility, List<Option>> expandableListDetail;
    private AdapterFacilityViewBinding adapterFacilityViewBinding;
    private ListGroupBinding listGroupBinding;

    public CustomFacilityListAdapter(Context context, List<Facility> expandableListTitle, HashMap<Facility, List<Option>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListDetail.get(expandableListTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Facility listTitle = (Facility) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listGroupBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_group, parent, false);
            convertView = listGroupBinding.getRoot();
        }
        Facility facility = new Facility(listTitle.facility_id, listTitle.name);
        listGroupBinding.setUser(facility);
        TextView listTitleTextViewOption = (TextView) convertView
                .findViewById(R.id.tvOptions);
        listTitleTextViewOption.setText("Options");
//        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Option expandedListOptions = (Option) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapterFacilityViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_facility_view, parent, false);
            convertView = adapterFacilityViewBinding.getRoot();
        }
        Option option = new Option(expandedListOptions.name, expandedListOptions.id);
        adapterFacilityViewBinding.setOptions(option);
        adapterFacilityViewBinding.propertyId.setText(String.valueOf(expandedListOptions.id));
        adapterFacilityViewBinding.name.setTypeface(null, Typeface.BOLD);
        adapterFacilityViewBinding.name.setText(expandedListOptions.name);
        adapterFacilityViewBinding.icon.setText(String.valueOf(expandedListOptions.id));

        if (expandedListOptions.icon.equalsIgnoreCase("apartment")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.apartment, 0, 0, 0);
        } else if (expandedListOptions.icon.equalsIgnoreCase("condo")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.apartment, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("boat")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.boat, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("land")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.land, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("rooms")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rooms, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("no-room")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.noroom, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("swimming")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.swimming, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("garden")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.garden, 0, 0, 0);

        } else if (expandedListOptions.icon.equalsIgnoreCase("garage")) {
            adapterFacilityViewBinding.icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.garage, 0, 0, 0);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
