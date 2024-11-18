package com.example.firebasejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MultiLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_SUBCATEGORY = 1;
    private static final int TYPE_ITEM = 2;

    private List<Object> dataList;
    private Context context;

    public MultiLevelAdapter(Context context, List<Object> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof Category) return TYPE_CATEGORY;
        if (dataList.get(position) instanceof Subcategory) return TYPE_SUBCATEGORY;
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        } else if (viewType == TYPE_SUBCATEGORY) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_subcategory, parent, false);
            return new SubcategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_item, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoryViewHolder) {
            Category category = (Category) dataList.get(position);
            ((CategoryViewHolder) holder).categoryName.setText(category.getName());
        } else if (holder instanceof SubcategoryViewHolder) {
            Subcategory subcategory = (Subcategory) dataList.get(position);
            ((SubcategoryViewHolder) holder).subcategoryName.setText(subcategory.getName());
        } else {
            Item item = (Item) dataList.get(position);
            ((ItemViewHolder) holder).itemName.setText(item.getName());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }

    public static class SubcategoryViewHolder extends RecyclerView.ViewHolder {
        TextView subcategoryName;

        public SubcategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            subcategoryName = itemView.findViewById(R.id.subcategory_name);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }
}
