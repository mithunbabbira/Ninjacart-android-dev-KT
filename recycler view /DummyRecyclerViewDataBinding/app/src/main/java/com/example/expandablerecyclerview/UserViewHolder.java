package com.example.expandablerecyclerview;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expandablerecyclerview.databinding.AdapterInventoryBinding;
import com.example.expandablerecyclerview.databinding.AdapterInventoryHeadingBinding;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public AdapterInventoryBinding binding;
    public AdapterInventoryHeadingBinding bind;

    private HeaderViewHolderCallback callback;

    Drawable arrowUp;
    Drawable arrowDown;

    public UserViewHolder(AdapterInventoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public UserViewHolder(AdapterInventoryHeadingBinding bind, HeaderViewHolderCallback callback) {
        super(bind.getRoot());
        this.bind = bind;
        this.callback = callback;
        arrowUp = ContextCompat.getDrawable(itemView.getContext(), android.R.drawable.arrow_up_float);
        arrowDown = ContextCompat.getDrawable(itemView.getContext(), android.R.drawable.arrow_down_float);
        bind.textviewSectionHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);

    }

    public void bind(Object obj, boolean state) {

        if (state) {
            bind.setVariable(BR.inventoryAdapterEntity, obj);
            bind.executePendingBindings();
            bind.headerLayout.setOnClickListener(this);
        } else {
            binding.setVariable(BR.inventoryAdapterEntity, obj);
            binding.executePendingBindings();
        }


    }

    @Override
    public void onClick(View v) {
        Log.d("res", "onClick: ");
        if (v == bind.headerLayout && callback != null) {
            Log.d("res", "onClick: inside");
            int position = getAdapterPosition();
            callback.onHeaderClick(position);
            if (callback.isExpanded(position)) {
                Log.d("res", "onClick: inside yes");
                bind.textviewSectionHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowUp, null);
            } else {
                Log.d("res", "onClick: inside yes");
                bind.textviewSectionHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);
            }
        }
    }

    public interface HeaderViewHolderCallback {
        void onHeaderClick(int position);

        boolean isExpanded(int position);
    }
}
