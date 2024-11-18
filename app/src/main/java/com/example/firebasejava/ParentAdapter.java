package com.example.firebasejava;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {

    private List<Category> parentItemList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public ParentAdapter(List<Category> parentItemList) {
        this.parentItemList = parentItemList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        Category parentItem = parentItemList.get(position);
        holder.textView.setText(parentItem.getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.childRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(parentItem.getSubcategories().size());

        // Subcategoryларни боғлаш
        List<ChildItem> childItems = new ArrayList<>();
        for (Subcategory subcategory : parentItem.getSubcategories()) {
            childItems.add(new ChildItem(subcategory.getImg(), subcategory.getName())); // иккинчи параметр — Subcategory номи
        }

        ChildAdapter childItemAdapter = new ChildAdapter(childItems);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(childItemAdapter);
        holder.childRecyclerView.setRecycledViewPool(viewPool);
    }


    @Override
    public int getItemCount() {
        return parentItemList.size();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView childRecyclerView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.parent_item_title);
            childRecyclerView = itemView.findViewById(R.id.child_recycler_view);
        }
    }

}
