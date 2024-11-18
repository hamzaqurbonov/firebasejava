package com.example.firebasejava;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    private List<ChildItem> childItemList;

    public ChildAdapter(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        ChildItem childItem = childItemList.get(position);
//        holder.imageView.setImageResource(childItem.getImageResId());
        holder.textView.setText(childItem.getText());

        Glide.with(holder.itemView.getContext()) // get context from view
                .load(childItem.getImg())
                .centerCrop()
                .placeholder(R.drawable.baseline_add_circle_outline_24)
                .into(holder.imageView);


        // Клик ивент
        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();

            Log.d("demo59", "getCategoryId(): " + childItem.getCategoryId());
//            Intent intent = new Intent(context, ItemListActivity.class);
//            intent.putExtra("subcategoryId", childItem.getCategoryId());
//            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return childItemList.size();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.child_item_image);
            textView = itemView.findViewById(R.id.child_item_text);
        }
    }

}
