package com.example.firebasejava;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DatabaseHelper databaseHelper;
    private List<Category> categoryList;
    RecyclerView parentRecyclerView;
    ParentAdapter parentAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        parentRecyclerView = findViewById(R.id.parent_recycler_view);

        versiyaSQL();
    }

    private void versiyaSQL() {
        Log.d("demo59", "Found1: ");
        // `Shorts` коллекциясидан барча ҳужжатларни оламиз
        db.collection("Test")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        databaseHelper.deleteAllData();
                        for (DocumentSnapshot mainDoc : task.getResult()) {
                            String mainDocId = mainDoc.getId();
                            long categoryId = databaseHelper.addCategory(mainDocId);

                            Log.d("demo59", "Found1: " + mainDocId);
                            // Ҳар бир ҳужжат ичидаги коллекцияни оламиз (масалан, "tabiat" ёки "Ustaxona")
                            db.collection("Test").document(mainDocId).collection(mainDocId)
                                    .get()
                                    .addOnCompleteListener(subTask -> {
                                        if (subTask.isSuccessful()) {
                                            for (DocumentSnapshot subDoc : subTask.getResult()) {
                                                String subDocId = subDoc.getId();
                                                String subImg =subDoc.getData().get("img").toString();
                                                Log.d("demo57", "IMG: " + subImg);

                                                long subcategoryId = databaseHelper.addSubcategory(subDocId, subImg, categoryId);

                                                // Ҳар бир ҳужжат ичидаги `key` массивини оламиз
                                                List<Map<String, Object>> keyArray = (List<Map<String, Object>>) subDoc.get("key");
                                                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                                                if (keyArray != null) {
                                                    for (Map<String, Object> mapItem : keyArray) {
                                                        // `id` ва `img` қийматларни оламиз
                                                        String id = (String) mapItem.get("id");
                                                        String url = (String) mapItem.get("url");
                                                        String img = (String) mapItem.get("img");
                                                        String name = (String) mapItem.get("name");
                                                        String old = (String) mapItem.get("old");
//                                                        ItemModel newItem = new ItemModel(1, id, url, subcategoryId);

                                                        databaseHelper.addItem(id,url, img, name, old, subcategoryId);
//                                                        databaseHelper.addItem(db, newItem);

                                                        Log.d("demo59", "Found1: " + mapItem.get("id"));
//                                                        Log.d("demo56", "Menu: " + mainDocId + ", SubMenu: " + subDocId + ", ID: " + id + ", IMG: " + img);
                                                    }
                                                }
                                            }

                                            categoryList = databaseHelper.getAllCategories();
                                            parentAdapter = new ParentAdapter(categoryList);
                                            parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                                            parentRecyclerView.setAdapter(parentAdapter);

                                        } else {
                                            Log.d("Firestore", "Ички коллекцияни олишда хатолик", subTask.getException());
                                        }
                                    });

                        }
                    } else {
                        Log.d("Firestore", "Асосий ҳужжатларни олишда хатолик", task.getException());
                    }
                });

    }

}




