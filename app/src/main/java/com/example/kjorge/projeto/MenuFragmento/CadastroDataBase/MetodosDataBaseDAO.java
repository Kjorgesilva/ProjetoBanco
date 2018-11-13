package com.example.kjorge.projeto.MenuFragmento.CadastroDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kjorge.projeto.DataBaseUsuario.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MetodosDataBaseDAO {

    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;



    public MetodosDataBaseDAO(Context context){
        dataBaseHelper = new DatabaseHelper(context);
    }



    public SQLiteDatabase getDabase(){
        if(sqLiteDatabase == null){
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }


    public long inserir(CadProduto cadastro) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",cadastro.getNome());
        contentValues.put("preco",cadastro.getPreco());
        contentValues.put("marca",cadastro.getMarca());
        contentValues.put("quantidade",cadastro.getQuantidade());

        Log.e("insert","inserido");
        return getDabase().insert("cadproduto",null,contentValues);
    }

    public List<CadProduto> ListarBancoProduto(){
        List<CadProduto> listarTodosOsElementos = new ArrayList<>();
        Cursor cursor = getDabase().rawQuery("SELECT * FROM cadproduto ORDER BY _id",null);
        while(cursor.moveToNext()){
            CadProduto listarCadastro = new CadProduto();
            listarCadastro.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            listarCadastro.setNome((cursor.getString(cursor.getColumnIndex("nome"))));
            listarCadastro.setPreco((cursor.getString(cursor.getColumnIndex("preco"))));
            listarCadastro.setMarca((cursor.getString(cursor.getColumnIndex("marca"))));
            listarCadastro.setQuantidade(cursor.getString(cursor.getColumnIndex("quantidade")));

            listarTodosOsElementos.add(listarCadastro);
        }


        cursor.close();

        Log.e("listar", "foi "+ listarTodosOsElementos.size());
        return listarTodosOsElementos;
    }

    public void excluir(String nome){
        getDabase().delete("cadproduto","nome = ?", new String[]{nome});
    }




    public long alterar(CadProduto usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("preco", usuario.getPreco());
        values.put("marca", usuario.getMarca());
        values.put("quantidade",usuario.getQuantidade());
        return getDabase().update
                ("cadproduto", values, "_id= ?",new String[]{String.valueOf(usuario.getId())});
    }


}
