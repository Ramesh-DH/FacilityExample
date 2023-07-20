package com.example.facilityexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facilityexample.R;
import com.example.facilityexample.databinding.ExclusionItemsBinding;
import com.example.facilityexample.model.Exclusion;

import java.util.List;

public class ExclusionAdapter extends RecyclerView.Adapter<ExclusionAdapter.ExclusionViewHolder> {

    List<Exclusion> exclusionList;
    Context context;

    public ExclusionAdapter(List<Exclusion> exclusionList, Context context) {
        this.exclusionList = exclusionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExclusionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExclusionItemsBinding exclusionItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.exclusion_items,parent,false);
        return new ExclusionViewHolder(exclusionItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExclusionViewHolder holder, int position) {
        Exclusion exclusion = exclusionList.get(position);
        Exclusion exclusion1 =new Exclusion(exclusion.facilityId,exclusion.optionsId);
        holder.exclusionItemsBinding.setExclusion(exclusion1);
    }

    @Override
    public int getItemCount() {
        return exclusionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ExclusionViewHolder extends RecyclerView.ViewHolder{

        private ExclusionItemsBinding exclusionItemsBinding;

        public ExclusionViewHolder(ExclusionItemsBinding exclusionItemsBinding) {
            super(exclusionItemsBinding.getRoot());
            this.exclusionItemsBinding = exclusionItemsBinding;
        }
    }

}
