package edu.damaris.queridodiario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    public static final String NOME = "database";
    public DataBase(Context context) {
        super(context, "dataBase", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create Table usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, senha VARCHAR(256))");
        }catch (Exception e) {
            Log.e("Erro no banco: ", e.getMessage());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop Table if exists usuario");
            onCreate(db);
        }catch (Exception e){
            Log.e("Erro no banco: ", e.getMessage());
        }
    }
    public Boolean inserirDadosUsuario(String nome, String senha){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("senha", senha);
        long inserir = database.insert("usuario", null, contentValues);
        return inserir != -1;
    }

    public Boolean verificarUsuario(String nomeUsuario){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from usuario where nome =?", new String[]{nomeUsuario});
        return (cursor.getCount() > 0);
    }
}
