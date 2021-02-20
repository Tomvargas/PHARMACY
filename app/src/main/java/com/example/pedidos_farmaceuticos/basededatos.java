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
import java.util.ArrayList;
import java.util.List;

public class basededatos extends SQLiteOpenHelper {
    private static final String query = "CREATE TABLE IF NOT EXISTS pedido(medicina TEXT, tipo TEXT, cantidad TEXT, dist TEXT, suc TEXT);";
    ArrayList<String> medicinas = new ArrayList<>();
    ArrayList<String> tipos = new ArrayList<>();
    ArrayList<String> cants = new ArrayList<>();
    ArrayList<String> dists = new ArrayList<>();
    ArrayList<String> sucs = new ArrayList<>();

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

    public ArrayList<ArrayList<String>> cursor(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        medicinas.clear();
        tipos.clear();
        cants.clear();
        dists.clear();
        sucs.clear();

        Cursor cur = MyDB.rawQuery("Select * from pedido", null);
        if (cur != null){
            cur.moveToFirst();
            while (cur.isAfterLast() == false) {

                medicinas.add(cur.getString(cur.getColumnIndex("medicina")));
                tipos.add(cur.getString(cur.getColumnIndex("tipo")));
                cants.add(cur.getString(cur.getColumnIndex("cantidad")));
                dists.add(cur.getString(cur.getColumnIndex("dist")));
                sucs.add(cur.getString(cur.getColumnIndex("suc")));

                cur.moveToNext();
            }
        }
        data.add(medicinas);
        data.add(tipos);
        data.add(cants);
        data.add(dists);
        data.add(sucs);

        return data;
    }

    class Data{
        String medicina, tipo, cant, dist, suc;
    }

}


