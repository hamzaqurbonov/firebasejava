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
    private static final String COLUMN_SUBCATEGORY_IMG = "subcategory_img";
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

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY_NAME + " TEXT NOT NULL)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        // Category жадвалини яратиш
        String CREATE_SUBCATEGORY_TABLE = "CREATE TABLE " + TABLE_SUBCATEGORY + "("
                + COLUMN_SUBCATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SUBCATEGORY_NAME + " TEXT NOT NULL,"
                + COLUMN_SUBCATEGORY_IMG + " TEXT NOT NULL,"
                + COLUMN_CATEGORY_ID_FK + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_CATEGORY_ID_FK + ") REFERENCES " + TABLE_CATEGORY + "(" + COLUMN_CATEGORY_ID + "))";
        db.execSQL(CREATE_SUBCATEGORY_TABLE);

        // Subcategory жадвалини яратиш
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ID + " TEXT NOT NULL,"
                + COLUMN_URL + " TEXT NOT NULL,"
                + COLUMN_SUBCATEGORY_ID_FK + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_SUBCATEGORY_ID_FK + ") REFERENCES " + TABLE_SUBCATEGORY + "(" + COLUMN_SUBCATEGORY_ID + "))";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBCATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
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
    public long addSubcategory(String subcategoryName, String subcategoryImg, long categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBCATEGORY_NAME, subcategoryName);
        values.put(COLUMN_SUBCATEGORY_IMG, subcategoryImg);
        values.put(COLUMN_CATEGORY_ID_FK, categoryId);
        return db.insert(TABLE_SUBCATEGORY, null, values);
    }

    // Insert item
    public long addItem(String id, String url, long subcategoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_URL, url);
        values.put(COLUMN_SUBCATEGORY_ID_FK, subcategoryId);
        return db.insert(TABLE_ITEM, null, values);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME));
                Category category = new Category(id, name);

                // Subcategoriesни қўшиш
                List<Subcategory> subcategories = getSubcategoriesByCategoryId(id);
                for (Subcategory subcategory : subcategories) {
                    category.addSubcategory(subcategory);
                }
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

    public List<Subcategory> getSubcategoriesByCategoryId(int categoryId) {
        List<Subcategory> subcategoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SUBCATEGORY + " WHERE " + COLUMN_CATEGORY_ID_FK + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(categoryId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUBCATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBCATEGORY_NAME));
                String img = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBCATEGORY_IMG));
                subcategoryList.add(new Subcategory(id, name, img, categoryId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subcategoryList;
    }


//    public List<Category> getAllCategories() {
//        List<Category> categoryList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_CATEGORY;
//
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ID));
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME));
//                categoryList.add(new Category(id, name));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return categoryList;
//    }

//
//
//
//    public String getSubcategoryName(long subcategoryId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String subcategoryName = null;
//
//        String query = "SELECT " + COLUMN_SUBCATEGORY_NAME + " FROM " + TABLE_SUBCATEGORY + " WHERE " + COLUMN_SUBCATEGORY_ID + " = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(subcategoryId)});
//
//        if (cursor.moveToFirst()) {
//            subcategoryName = cursor.getString(cursor.getColumnIndex(COLUMN_SUBCATEGORY_NAME));
//        }
//
//        cursor.close();
//        db.close();
//        return subcategoryName;
//    }
//
//    public List<ItemModel> getItemsBySubcategory(long subcategoryId) {
//        List<ItemModel> itemList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT * FROM " + TABLE_ITEM + " WHERE " + COLUMN_SUBCATEGORY_ID_FK + " = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(subcategoryId)});
//
//        if (cursor.moveToFirst()) {
//            do {
//                long itemId = cursor.getLong(cursor.getColumnIndex(COLUMN_ITEM_ID));
//                String name = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
//                String url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
//                itemList.add(new ItemModel(itemId, name, url, subcategoryId));
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return itemList;
//    }
//
//
//    // Update item
//    public int updateItem(long itemId, String newId, String newUrl) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_ID, newId);
//        values.put(COLUMN_URL, newUrl);
//        return db.update(TABLE_ITEM, values, COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
//    }

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