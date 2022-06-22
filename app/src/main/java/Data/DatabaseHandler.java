package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import Model.Menu;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler( Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_PIZZA + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_TITLE + " TEXT,"
                + Util.KEY_PRICE + " TEXT,"
                + Util.KEY_WEIGHT + " TEXT" + ")";
        String CREATE_RECIPES_TABLE_SALAT = "CREATE TABLE "+ Util.TABLE_NAME_SALAT + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_TITLE + " TEXT,"
                + Util.KEY_PRICE + " TEXT,"
                + Util.KEY_WEIGHT + " TEXT" + ")";
        String CREATE_RECIPES_TABLE_SOUP = "CREATE TABLE "+ Util.TABLE_NAME_SOUP + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_TITLE + " TEXT,"
                + Util.KEY_PRICE + " TEXT,"
                + Util.KEY_WEIGHT + " TEXT" + ")";
        String CREATE_RECIPES_TABLE_MEAT = "CREATE TABLE "+ Util.TABLE_NAME_MEAT + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_TITLE + " TEXT,"
                + Util.KEY_PRICE + " TEXT,"
                + Util.KEY_WEIGHT + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_RECIPES_TABLE);
        sqLiteDatabase.execSQL(CREATE_RECIPES_TABLE_SALAT);
        sqLiteDatabase.execSQL(CREATE_RECIPES_TABLE_SOUP);
        sqLiteDatabase.execSQL(CREATE_RECIPES_TABLE_MEAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +Util.TABLE_NAME_PIZZA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME_SALAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME_SOUP);
        onCreate(sqLiteDatabase);

    }

    public void addRecipe(Menu menu, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TITLE, menu.getTitle());
        contentValues.put(Util.KEY_PRICE, menu.getPrice());
        contentValues.put(Util.KEY_WEIGHT, menu.getWeight());
        db.insert(tableName, null, contentValues);
        db.close();
    }

    public Menu getRecipes(int id, String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, new String[] {Util.KEY_ID, Util.KEY_TITLE, Util.KEY_PRICE, Util.KEY_WEIGHT},
           Util.KEY_ID + "=?", new String[] {String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Menu menu = new Menu(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
        return menu;

    }

    public List<Menu> getAllRecipes(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Menu> menuList = new ArrayList<>();
        String selectAllRecipes = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectAllRecipes, null);
        if (cursor.moveToFirst()){
            do {
                Menu menu = new Menu();
                menu.setId(Integer.parseInt(cursor.getString(0)));
                menu.setTitle(cursor.getString(1));
                menu.setPrice(Integer.parseInt(cursor.getString(2)));
                menu.setWeight(Integer.parseInt(cursor.getString(3)));
                menuList.add(menu);

            } while (cursor.moveToNext());

        }
        return menuList;
    }

    public int updateRecipe(Menu menu, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TITLE, menu.getTitle());
        contentValues.put(Util.KEY_PRICE, menu.getPrice());
        contentValues.put(Util.KEY_WEIGHT, menu.getWeight());
        return db.update(tableName, contentValues, Util.KEY_ID + "=?",
                new String[] {String.valueOf(menu.getId())});
    }

    public void deleteRecipe(Menu menu, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, Util.KEY_ID + "=?",
                new String[] {String.valueOf(menu.getId())});
        db.close();
    }
    public int getRecipesCount (String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(countQuery, null);
        return  cursor.getCount();
    }

}
