package edu.damaris.queridodiario;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
public class DataBase extends SQLiteOpenHelper {
    public static final String NOME = "dataBase";
    public DataBase(Context context, String dataBase, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dataBase, factory, 1);
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
            db.execSQL("drop TABLE if exists usuario");
            onCreate(db);
        }catch (Exception e){
            Log.e("Erro no banco: ", e.getMessage());
        }
    }
    public Boolean inserirDados(String nome, String senha){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("senha", senha);
        long inserir = database.insert("usuario", null, contentValues);
        return inserir != -1;
    }
}
