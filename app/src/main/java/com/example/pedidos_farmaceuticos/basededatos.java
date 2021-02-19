package com.example.pedidos_farmaceuticos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class basededatos extends SQLiteOpenHelper {
    private static final String query = "CREATE TABLE IF NOT EXISTS pedido(medicina TEXT, tipo TEXT, cantidad TEXT, dist TEXT, suc TEXT);";


    public basededatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ejecuta las consultas declaradas al inicio de la clase
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists pedido");
    }

    //Ingresa un pedido a la base de datos
    public boolean insertar(String medicina, String tipo, String cant, String dis, String suc){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicina", medicina);
        contentValues.put("tipo", tipo);
        contentValues.put("cantidad", cant);
        contentValues.put("dist", dis);
        contentValues.put("suc", suc);
        long ins = MyDB.insert("pedido", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }


}
