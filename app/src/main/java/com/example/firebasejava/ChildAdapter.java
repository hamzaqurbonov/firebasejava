package com.example.firebasejava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        holder.imageView.setImageResource(childItem.getImageResId());
        holder.textView.setText(childItem.getText());
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
