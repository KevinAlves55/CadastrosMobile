package com.example.libriaprendizado.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "libriAprendizado";
    private static final int DB_VERSION = 1;
    private static SQLHelper INSTANCE;

    public static SQLHelper getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = new SQLHelper(context);
        }

        return INSTANCE;

    }

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
        "CREATE TABLE tbl_usuario" +
        "(" +
                "cod_usuario INTEGER PRIMARY KEY," +
                "nome TEXT not null," +
                "sobrenome TEXT not null," +
                "login TEXT not null," +
                "senha TEXT not null);" +
        "");

        sqLiteDatabase.execSQL(
        "CREATE TABLE tbl_endereco(" +
            "cod_endereco INTEGER PRIMARY KEY," +
            "cod_usuario INTEGER," +
            "cep VARCHAR(10) not null," +
            "numero VARCHAR(10) not null," +
            "complemento VARCHAR(500)," +
            "FOREIGN KEY (cod_usuario) REFERENCES tbl_usuario(cod_usuario))" +
        "");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }//FIM DO MÉTODO onUpgrade

    public boolean addUser(String nome, String sobrenome, String login, String senha) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("nome", nome);
            values.put("sobrenome", sobrenome);
            values.put("login", login);
            values.put("senha", senha);

            sqLiteDatabase.insertOrThrow("tbl_usuario",
                    null,
                    values);
            sqLiteDatabase.setTransactionSuccessful();

            return true;


        }catch (Exception e){

            Log.d("SQLERRO", e.getMessage());
            return false;

        }finally {

            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }

        }

    }//FIM DO MÉTODO ADDUSER

    public boolean addEndereco(int cod_usuario, String cep, String numero, String complemento){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("cod_usuario", cod_usuario);
            values.put("cep", cep);
            values.put("numero", numero);
            values.put("complemento", complemento);

            sqLiteDatabase.insertOrThrow("tbl_endereco",
                    null,
                    values);
            sqLiteDatabase.setTransactionSuccessful();

            return true;

        }catch (Exception e){

            Log.d("SQLERRO", e.getMessage());
            return false;

        }finally {

            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }

        }

    }//FIM DO MÉTODO addEndereco

    public int login(String login, String senha){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase
                .rawQuery("SELECT * FROM tbl_usuario" +
                                " WHERE login = ? AND senha = ?",
                        new String[]{login, senha});

        try{

            if(cursor.moveToFirst()){

                int cod_usuario = cursor.getInt(cursor.getColumnIndex("cod_usuario"));
                return cod_usuario;

            }

        }catch(Exception e){

            Log.d("SQLiteError", e.getMessage());
            return 0;

        }finally {

            if(cursor != null && !cursor.isClosed()){

                cursor.close();

            }

        }

        return 0;

    }

}//FIM DA CLASSE SQLHelper
