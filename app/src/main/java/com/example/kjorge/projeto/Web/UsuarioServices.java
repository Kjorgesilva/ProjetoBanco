package com.example.kjorge.projeto.Web;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.Cadastro;
import com.example.kjorge.projeto.MenuFragmento.MainActivity;
import com.example.kjorge.projeto.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioServices {

    public static List<Cadastro> cadastroUsuario = new ArrayList<>();
    private Context contexto;
    private RequestQueue queue;

    public UsuarioServices(Context context) {
        this.contexto = context;
    }

    public void getUsuario(String path) {

        queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET,
                Connection.getUrl() + path, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                List<Cadastro> login = new Gson().fromJson(response.toString(), new TypeToken<List<Cadastro>>() {
                }.getType());
                cadastroUsuario.clear();
                cadastroUsuario.addAll(login);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(contexto, R.string.network_error, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(contexto, R.string.timeout_error, Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("authorization", "basic");
                return params;
            }
        };


        // Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(getRequest);
        getRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getRequest);

    }

    public void doPost(String name, final Map<String, String> params) {
        queue = Volley.newRequestQueue(contexto);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST,
                Connection.getUrl() + name, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Intent intent = new Intent(contexto, MainActivity.class);
                contexto.startActivity(intent);
                Toast.makeText(contexto,"Login usando WebService",Toast.LENGTH_SHORT).show();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(contexto, R.string.network_error, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(contexto, R.string.timeout_error, Toast.LENGTH_LONG).show();
                }
            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }

}