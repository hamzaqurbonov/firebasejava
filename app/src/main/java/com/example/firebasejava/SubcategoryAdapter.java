package com.example.firebasejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {

    private Context context;
    private List<Subcategory> subcategoryList;

    public SubcategoryAdapter(Context context, List<Subcategory> subcategoryList) {
        this.context = context;
        this.subcategoryList = subcategoryList;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subcategory, parent, false);
        return new SubcategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {
        Subcategory subcategory = subcategoryList.get(position);
        holder.subcategoryTitle.setText(subcategory.getTitle());

        // Item RecyclerView'ни ўрнатиш
        ItemAdapter itemAdapter = new ItemAdapter(context, subcategory.getItems());
        holder.itemRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.itemRecyclerView.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }

    public static class SubcategoryViewHolder extends RecyclerView.ViewHolder {
        TextView subcategoryTitle;
        RecyclerView itemRecyclerView;

        public SubcategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            subcategoryTitle = itemView.findViewById(R.id.subcategory_title);
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view);
        }
    }
}
