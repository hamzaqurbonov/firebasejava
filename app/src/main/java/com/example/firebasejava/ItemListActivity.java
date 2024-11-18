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

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView itemRecyclerView;
    private DatabaseHelper databaseHelper;
    private ItemAdapter itemAdapter;
    private List<ItemModel> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_list);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        itemRecyclerView = findViewById(R.id.item_recycler_view);
        databaseHelper = new DatabaseHelper(this);

        // Intent орқали `subcategoryId` олиш
//        long subcategoryId = getIntent().getLongExtra("subcategoryId", -1);

//        long subcategoryId = getIntent().getLongExtra("subcategory", 3);

        String subcategoryId = getIntent().getExtras().getString("subcategory");
      Log.d("demo60", "ItemListActivity: " + subcategoryId);


        // Маълумотларни фильтрлаш
        itemList = databaseHelper.getItemsBySubcategory(3);

        // RecyclerView тайёрлаш
        itemAdapter = new ItemAdapter(itemList);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.setAdapter(itemAdapter);
    }
}