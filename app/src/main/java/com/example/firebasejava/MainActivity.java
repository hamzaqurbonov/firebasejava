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
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DatabaseHelper databaseHelper;
    private TextView categoryTitle, subcategoryTitle, itemTitle;

    RecyclerView recyclerView;

    ItemAdapter itemAdapter;

    private ArrayList<ItemModel> modalArrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Янги объект яратиш
        ItemModel newItem = new ItemModel(1, "https://example.com", "aaa", 1);

        // Объектни жадвалга қўшиш
        databaseHelper.addItem(db, newItem);

        // Эслатма: маълумотлар базасини ёпишни унутманг
        db.close();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);

        // Керакли кичик категория ID ни киритинг
        long subcategoryId = 1; // Мисол ID
        List<ItemModel> items = databaseHelper.getItemsBySubcategory(subcategoryId);

        itemAdapter = new ItemAdapter(items);
        recyclerView.setAdapter(itemAdapter);


//        recyclerView = findViewById(R.id.rview);
//
//        modalArrayList = new ArrayList<>();
//        databaseHelper = new DatabaseHelper(this);
////
//        modalArrayList = databaseHelper.getItemsBySubcategory();
////
//        adapter = new ItemAdapter(modalArrayList);
////
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
////
//        recyclerView.setAdapter(adapter);

//        recyclerView = findViewById(R.id.rview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        databaseHelper = new DatabaseHelper(this);
//
//        // Керакли кичик категория ID ни киритинг
//        long subcategoryId = 1; // Мисол ID
//        List<ItemModel> items = databaseHelper.getItemsBySubcategory(subcategoryId);
//
//        itemAdapter = new ItemAdapter(items);
//        recyclerView.setAdapter(itemAdapter);


//        categoryTitle = findViewById(R.id.categoryTitle);
//        subcategoryTitle = findViewById(R.id.subcategoryTitle);
//        itemTitle = findViewById(R.id.itemTitle);
////
//        databaseHelper = new DatabaseHelper(this);
////
//        // Категория ва кичик тоифаларни қўшиш
//        long categoryId1 = databaseHelper.addCategory("Жонлиолам");
//        long subcategoryId1 = databaseHelper.addSubcategory("Табиат", categoryId1);
//        databaseHelper.addItem("Дарё", "Сув", subcategoryId1);
//        databaseHelper.addItem("Тоғ", "экотизими", subcategoryId1);
//
//        long subcategoryId2 = databaseHelper.addSubcategory("Хайвонот", categoryId1);
//        databaseHelper.addItem("Ҳайвон", "Қуш", subcategoryId2);
//        databaseHelper.addItem("Балиқ", "Сув", subcategoryId2);
//
//        long categoryId2 = databaseHelper.addCategory("Устахона");
//        long subcategoryId3 = databaseHelper.addSubcategory("Мошина", categoryId2);
//        databaseHelper.addItem("Феррари", "оқ", subcategoryId3);
//        databaseHelper.addItem("БМВ", "қизил", subcategoryId3);
//
//        long subcategoryId4 = databaseHelper.addSubcategory("Трактор", categoryId2);
//        databaseHelper.addItem("Трактор", "кўк", subcategoryId4);
//        databaseHelper.addItem("Экскаватор", "сариқ", subcategoryId4);
//
        versiyaSQL();
////        // Категориялар рўйхатини олиш
//        List<Category> categories = getCategoriesWithData();
////
//        // Маълумотларни TextView га чиқариш
//        displayData(categories);


    }

//    private List<Category> getCategoriesWithData() {
//        List<Category> categories = new ArrayList<>();
//
//        // Маълумотлар базасидан категориялар, кичик тоифа ва элементлар олинади
//        long categoryId1 = databaseHelper.addCategory("Жонлиолам");
//        long subcategoryId1 = databaseHelper.addSubcategory("Табиат", categoryId1);
//        Category category1 = new Category(categoryId1, "Жонлиолам");
//        Subcategory subcategory1 = new Subcategory(subcategoryId1, "Табиат");
//
//        // Тоифалар рўйхатини тўлдириш
//        subcategory1.addItem(new Item(1, "Дарё", "Сув"));
//        subcategory1.addItem(new Item(2, "Тоғ", "экотизими"));
//        category1.addSubcategory(subcategory1);
//
//        Subcategory subcategory2 = new Subcategory(2, "Хайвонот");
//        subcategory2.addItem(new Item(3, "Ҳайвон", "Қуш"));
//        subcategory2.addItem(new Item(4, "Балиқ", "Сув"));
//        category1.addSubcategory(subcategory2);
//
//        categories.add(category1);
//
//        Category category2 = new Category(2, "Устахона");
//        Subcategory subcategory3 = new Subcategory(3, "Мошина");
//        subcategory3.addItem(new Item(5, "Феррари", "оқ"));
//        subcategory3.addItem(new Item(6, "БМВ", "қизил"));
//        category2.addSubcategory(subcategory3);
//
//        Subcategory subcategory4 = new Subcategory(4, "Трактор");
//        subcategory4.addItem(new Item(7, "Трактор", "кўк"));
//        subcategory4.addItem(new Item(8, "Экскаватор", "сариқ"));
//        category2.addSubcategory(subcategory4);
//
//        categories.add(category2);
//
//        return categories;
//    }
//
//    private void displayData(List<Category> categories) {
//        StringBuilder categoryText = new StringBuilder();
//        StringBuilder subcategoryText = new StringBuilder();
//        StringBuilder itemText = new StringBuilder();
//
//        for (Category category : categories) {
//            categoryText.append("Категория: ").append(category.getName()).append("\n");
//            for (Subcategory subcategory : category.getSubcategories()) {
//                subcategoryText.append("  - Кичик тоифа: ").append(subcategory.getName()).append("\n");
//                for (Item item : subcategory.getItems()) {
//                    itemText.append("    -- ").append(item.getName())
//                            .append(": ").append(item.getUrl()).append("\n");
//                }
//            }
//        }
//
//        categoryTitle.setText(categoryText.toString());
//        subcategoryTitle.setText(subcategoryText.toString());
//        itemTitle.setText(itemText.toString());
//    }


    private void versiyaSQL() {
        Log.d("demo59", "Found1: ");
        // `Shorts` коллекциясидан барча ҳужжатларни оламиз
        db.collection("Test")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
//                        dbHome.deleteAllData();
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
                                                long subcategoryId = databaseHelper.addSubcategory(subDocId, categoryId);

                                                // Ҳар бир ҳужжат ичидаги `key` массивини оламиз
                                                List<Map<String, Object>> keyArray = (List<Map<String, Object>>) subDoc.get("key");
                                                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                                                if (keyArray != null) {
                                                    for (Map<String, Object> mapItem : keyArray) {
                                                        // `id` ва `img` қийматларни оламиз
                                                        String id = (String) mapItem.get("id");
                                                        String url = (String) mapItem.get("url");
                                                        ItemModel newItem = new ItemModel(1, id, url, subcategoryId);

                                                        databaseHelper.addItem(db, newItem);

                                                        Log.d("demo59", "Found1: " + mapItem.get("id"));
//                                                        Log.d("demo56", "Menu: " + mainDocId + ", SubMenu: " + subDocId + ", ID: " + id + ", IMG: " + img);
                                                    }
                                                }
                                            }


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




