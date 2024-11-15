package com.example.firebasejava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "example.db";
    private static final int DATABASE_VERSION = 1;

    // Category table
    private static final String TABLE_CATEGORY = "Category";
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_CATEGORY_NAME = "category_name";

    // Subcategory table
    private static final String TABLE_SUBCATEGORY = "Subcategory";
    private static final String COLUMN_SUBCATEGORY_ID = "subcategory_id";
    private static final String COLUMN_SUBCATEGORY_NAME = "subcategory_name";
    private static final String COLUMN_CATEGORY_ID_FK = "category_id";

    // Item table
    private static final String TABLE_ITEM = "Item";
    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_SUBCATEGORY_ID_FK = "subcategory_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // items жадвалини яратиш
        String CREATE_ITEMS_TABLE = "CREATE TABLE items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "url TEXT, " +
                "subcategoryId INTEGER)";
        db.execSQL(CREATE_ITEMS_TABLE);

        // Category жадвалини яратиш
        String CREATE_CATEGORY_TABLE = "CREATE TABLE categories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        // Subcategory жадвалини яратиш
        String CREATE_SUBCATEGORY_TABLE = "CREATE TABLE subcategories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "categoryId INTEGER)";
        db.execSQL(CREATE_SUBCATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Жадваллар мавжуд бўлса, уларни ўчириш
        db.execSQL("DROP TABLE IF EXISTS items");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS subcategories");
        onCreate(db);
    }

    // Insert category
    public long addCategory(String categoryName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, categoryName);
        return db.insert(TABLE_CATEGORY, null, values);
    }

    // Insert subcategory
    public long addSubcategory(String subcategoryName, long categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBCATEGORY_NAME, subcategoryName);
        values.put(COLUMN_CATEGORY_ID_FK, categoryId);
        return db.insert(TABLE_SUBCATEGORY, null, values);
    }

    // Insert item
    public void addItem(SQLiteDatabase db, ItemModel item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("url", item.getUrl());
        values.put("subcategoryId", item.getSubcategoryId());
        db.insert("items", null, values);
    }

    // Get all items in a subcategory
//    public List<String> getItemsBySubcategory(long subcategoryId) {
//        List<String> items = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT " + COLUMN_ID + ", " + COLUMN_URL + " FROM " + TABLE_ITEM
//                + " WHERE " + COLUMN_SUBCATEGORY_ID_FK + "=?";
//        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(subcategoryId)});
//        if (cursor.moveToFirst()) {
//            do {
//                items.add("ID: " + cursor.getString(0) + ", URL: " + cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return items;
//    }

    public List<ItemModel> getItemsBySubcategory(long subcategoryId) {
        List<ItemModel> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM items WHERE subcategoryId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(subcategoryId)});

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                itemList.add(new ItemModel(id, name, url, subcategoryId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }






    // Update item
    public int updateItem(long itemId, String newId, String newUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, newId);
        values.put(COLUMN_URL, newUrl);
        return db.update(TABLE_ITEM, values, COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
    }

    // Delete item
    public int deleteItem(long itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ITEM, COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
    }

    // Delete subcategory
    public int deleteSubcategory(long subcategoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SUBCATEGORY, COLUMN_SUBCATEGORY_ID + "=?", new String[]{String.valueOf(subcategoryId)});
    }

    // Delete category
    public int deleteCategory(long categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CATEGORY, COLUMN_CATEGORY_ID + "=?", new String[]{String.valueOf(categoryId)});
    }
}