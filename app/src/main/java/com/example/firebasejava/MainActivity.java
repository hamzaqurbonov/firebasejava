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


public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

//    private DatabaseHelper databaseHelper;
//    private TextView categoryTitle, subcategoryTitle, itemTitle;
//
//    RecyclerView recyclerView;
//
//    ItemAdapter itemAdapter;
//    private List<Category> categoryList;
//
//    private ArrayList<ItemModel> modalArrayList;

//    private RecyclerView recyclerView;
//    private MultiLevelAdapter adapter;
//    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_recycler_view);


        RecyclerView categoryRecyclerView = findViewById(R.id.category_recycler_view);
        List<Category> categoryList = prepareCategoryData();

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryRecyclerView.setAdapter(categoryAdapter);


//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        databaseHelper = new DatabaseHelper(this);
//
//        List<Category> categories = databaseHelper.getCategoriesWithSubcategoriesAndItems();
//        List<Object> dataList = prepareData(categories);
//
//        adapter = new MultiLevelAdapter(this, dataList);
//        recyclerView.setAdapter(adapter);



//        databaseHelper = new DatabaseHelper(this);
//
//
////        long subcategory = 2; // Охирги ID ёки ўзингизга керакли ID
//        categoryList = databaseHelper.getAllCategories();
//        Log.d("demo47", "Номи_Catigory: " + categoryList.toString());
////        if (CategoryArrayList != null) {
////            Log.d("demo47", "Номи: " + CategoryArrayList);
////        } else {
////            Log.d("demo47", "Бундай Subcategory топилмади");
////        }
//
//
//
//
//
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        long subcategory = 2; // Охирги ID ёки ўзингизга керакли ID
//        String subcategoryName = dbHelper.getSubcategoryName(subcategory);
//
//        if (subcategoryName != null) {
//            Log.d("demo47", "Номи: " + subcategoryName);
//        } else {
//            Log.d("demo47", "Бундай Subcategory топилмади");
//        }
//
//
//
////        versiyaSQL();
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
////        databaseHelper = new DatabaseHelper(this);
//
//        // Керакли кичик категория ID ни киритинг
//        long subcategoryId = 3; // Мисол ID
//        List<ItemModel> items = databaseHelper.getItemsBySubcategory(subcategoryId);
//
//        itemAdapter = new ItemAdapter(categoryList);
//        recyclerView.setAdapter(itemAdapter);

    }

    private List<Object> prepareData(List<Category> categories) {
        List<Object> dataList = new ArrayList<>();
        for (Category category : categories) {
            dataList.add(category);
            for (Subcategory subcategory : category.getSubcategories()) {
                dataList.add(subcategory);
                dataList.addAll(subcategory.getItems());
            }
        }
        return dataList;
    }

    private List<Category> prepareCategoryData() {
        List<Item> itemList1 = Arrays.asList(new Item("Item 1.1"), new Item("Item 1.2"), new Item("Item 1.3"));
        List<Item> itemList2 = Arrays.asList(new Item("Item 2.1"), new Item("Item 2.2"));
        List<Item> itemList3 = Arrays.asList(new Item("Item 3.1"), new Item("Item 3.2"), new Item("Item 3.3"));

        Subcategory subcategory1 = new Subcategory("Subcategory 1", itemList1);
        Subcategory subcategory2 = new Subcategory("Subcategory 2", itemList2);
        Subcategory subcategory3 = new Subcategory("Subcategory 3", itemList3);

        Category category1 = new Category("Category 1", Arrays.asList(subcategory1, subcategory2));
        Category category2 = new Category("Category 2", Arrays.asList(subcategory3));

        return Arrays.asList(category1, category2);
    }



//
//    private void versiyaSQL() {
//        Log.d("demo59", "Found1: ");
//        // `Shorts` коллекциясидан барча ҳужжатларни оламиз
//        db.collection("Test")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
////                        dbHome.deleteAllData();
//                        for (DocumentSnapshot mainDoc : task.getResult()) {
//                            String mainDocId = mainDoc.getId();
//                            long categoryId = databaseHelper.addCategory(mainDocId);
//
//                            Log.d("demo59", "Found1: " + mainDocId);
//                            // Ҳар бир ҳужжат ичидаги коллекцияни оламиз (масалан, "tabiat" ёки "Ustaxona")
//                            db.collection("Test").document(mainDocId).collection(mainDocId)
//                                    .get()
//                                    .addOnCompleteListener(subTask -> {
//                                        if (subTask.isSuccessful()) {
//                                            for (DocumentSnapshot subDoc : subTask.getResult()) {
//                                                String subDocId = subDoc.getId();
//                                                long subcategoryId = databaseHelper.addSubcategory(subDocId, categoryId);
//
//                                                // Ҳар бир ҳужжат ичидаги `key` массивини оламиз
//                                                List<Map<String, Object>> keyArray = (List<Map<String, Object>>) subDoc.get("key");
//                                                SQLiteDatabase db = databaseHelper.getWritableDatabase();
//
//                                                if (keyArray != null) {
//                                                    for (Map<String, Object> mapItem : keyArray) {
//                                                        // `id` ва `img` қийматларни оламиз
//                                                        String id = (String) mapItem.get("id");
//                                                        String url = (String) mapItem.get("url");
////                                                        ItemModel newItem = new ItemModel(1, id, url, subcategoryId);
//
//                                                        databaseHelper.addItem(id,url, subcategoryId);
////                                                        databaseHelper.addItem(db, newItem);
//
//                                                        Log.d("demo59", "Found1: " + mapItem.get("id"));
////                                                        Log.d("demo56", "Menu: " + mainDocId + ", SubMenu: " + subDocId + ", ID: " + id + ", IMG: " + img);
//                                                    }
//                                                }
//                                            }
//
//
//                                        } else {
//                                            Log.d("Firestore", "Ички коллекцияни олишда хатолик", subTask.getException());
//                                        }
//                                    });
//                        }
//                    } else {
//                        Log.d("Firestore", "Асосий ҳужжатларни олишда хатолик", task.getException());
//                    }
//                });
//
//    }
}




