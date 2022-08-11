package com.example.expandablerecyclerview;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.expandablerecyclerview.databinding.AdapterInventoryBinding;
import com.example.expandablerecyclerview.databinding.AdapterInventoryHeadingBinding;
import com.example.expandablerecyclerview.model.SkuReports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> implements UserViewHolder.HeaderViewHolderCallback {

    private List<SkuReports> tempReports = new ArrayList<>();
    private List<SkuReports> skuReports = new ArrayList<>();

    private SparseIntArray headerExpandTracker;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if (skuReports.get(position).isHeading()) {
            AdapterInventoryHeadingBinding bind = AdapterInventoryHeadingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new UserViewHolder(bind, this);
        }
        AdapterInventoryBinding binding = AdapterInventoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SkuReports dataModel;
        if (skuReports.get(position).isHeading()) {
            dataModel = skuReports.get(position + 1);
        } else {
            dataModel = skuReports.get(position);
        }
        holder.bind(dataModel, skuReports.get(position).isHeading());
    }


    @Override
    public int getItemCount() {
        if (!skuReports.isEmpty()) {
            return skuReports.size();
        }
        return 0;
    }


    public void setSkuReportsData(List<SkuReports> skuReports) {
        if (skuReports != null) {
            headerExpandTracker = new SparseIntArray();
            headerExpandTracker.put(0, 1);
            this.tempReports = skuReports;
            this.skuReports = skuReports;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onHeaderClick(int position) {
        if (skuReports.get(position).isHeading()) {
            skuReports.get(position).setHeading(false);
        } else {
            skuReports.get(position).setHeading(true);
        }



       // notifyItemChanged(position + 1);

       notifyItemRangeChanged(1,2);


//        int dataIndex = position;
//        try {
//            if (headerExpandTracker.get(dataIndex) == 0) {
//                int childListItemCount = tempReports.get(position).getChildCount();
//                for (int i = 0; i < childListItemCount; i++) {
//                    skuReports.add(position + i + 1, tempReports.get(position + i));
//                }
//                // Collapsed. Now expand it
//                headerExpandTracker.put(dataIndex, 1);
////                notifyItemRangeInserted(position + 1, childListItemCount);
//            } else {
//                int childListItemCount = tempReports.get(position).getChildCount();
//                for (int i = 0; i < childListItemCount; i++) {
//                    skuReports.remove(position + i + 1);
//                }
//                // Expanded. Now collapse it
//                headerExpandTracker.put(dataIndex, 0);
//                notifyDataSetChanged();
////                notifyItemRangeRemoved();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public boolean isExpanded(int position) {
        return skuReports.get(position).isHeading();
    }
}
