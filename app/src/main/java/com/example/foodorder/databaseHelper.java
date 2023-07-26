package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorder.Models.OrderListModel;

import java.util.ArrayList;

public class databaseHelper extends SQLiteOpenHelper {
    final static String dbName = "myDataBase.db";
    final static int dbVersion = 1;

    public databaseHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "description text," +
                        "foodName text," +
                        "quantity int) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    //insert data into table
    public boolean insertOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        }
        return true;
    }

    //read order in orderList
    public ArrayList<OrderListModel> getOrders() {
        ArrayList<OrderListModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodName,image,price from orders", null);
        if (cursor.moveToFirst()) {
           do {

               OrderListModel model = new OrderListModel();
               model.setOrderNumber(cursor.getInt(0) + "");
               model.setName(cursor.getString(1));
               model.setImage(cursor.getInt(2));
               model.setPrice(cursor.getInt(3) + "");
               orders.add(model);
           }while (cursor.moveToNext());

//            while (cursor.moveToNext()) {
//                OrderListModel model = new OrderListModel();
//                model.setOrderNumber(cursor.getInt(0) + "");
//                model.setName(cursor.getString(1));
//                model.setImage(cursor.getInt(2));
//                model.setPrice(cursor.getInt(3) + "");
//                orders.add(model);
//            }
        }

        return orders;
    }

    //update data into table:-
    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }



    //update data into table
    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity,int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        long row = database.update("orders",values,"id="+id,null);
        if (row <= 0) {
            return false;
        }
        return true;
    }

    //delete order
    public int deleteOrder(String id){
SQLiteDatabase database=this.getWritableDatabase();
return database.delete("orders","id="+id,null);
    }

}
