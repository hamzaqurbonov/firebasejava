package com.example.firebasejava;

import android.os.Bundle;
import android.util.Log;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView itemRecyclerView;
    private DatabaseHelper databaseHelper;
    private ItemAdapter itemAdapter;
    private List<ItemModel> itemList;
    String subcategoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemRecyclerView = findViewById(R.id.item_recycler_view);
        databaseHelper = new DatabaseHelper(this);

        // Intent орқали `subcategoryId` олиш
        int subcategoryId = getIntent().getIntExtra("subcategory", -1);
      Log.d("demo60", "ItemListActivity: " + subcategoryId);


        // Маълумотларни фильтрлаш
        itemList = databaseHelper.getItemsBySubcategory(subcategoryId);

        // RecyclerView тайёрлаш
        itemAdapter = new ItemAdapter(itemList);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.setAdapter(itemAdapter);
    }
}