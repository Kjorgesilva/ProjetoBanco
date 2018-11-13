package com.example.kjorge.projeto.DataBaseUsuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CadastroDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public CadastroDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDabase(){
        if(sqLiteDatabase == null){
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public long inserir(Cadastro cadastro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("login",cadastro.getUsuario());
        contentValues.put("senha",cadastro.getSenha());
        Log.e("insert","inserido");
        return getDabase().insert("usuario",null,contentValues);
    }

    public List<Cadastro> ListarBanco(){
        List<Cadastro> listarTodosOsElementos = new ArrayList<Cadastro>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM usuario ORDER BY _id",null);
        while(cursor.moveToNext()){
            Cadastro listarCadastro = new Cadastro();
            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listarCadastro.setUsuario((cursor.getString(cursor.getColumnIndex("login"))));
            listarCadastro.setSenha((cursor.getString(cursor.getColumnIndex("senha"))));

            listarTodosOsElementos.add(listarCadastro);
        }


        cursor.close();

        Log.e("listar", "foi "+ listarTodosOsElementos.size());
        return listarTodosOsElementos;
    }

}
