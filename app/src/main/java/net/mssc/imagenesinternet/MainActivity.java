package net.mssc.imagenesinternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //ATRIBUTOS NECESARIOS
    String URL_HEROES = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    List<Super> listSuper;
    private RecyclerView rvSuper;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIALIZAR LISTA Y REQUEST QUEUE
        listSuper = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //PETICION
        peticionJson();
    }

    private void peticionJson() {

        //STRING REQUEST PARA OBTENER LA LISTA DE IMAGENES
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_HEROES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //OBTENER EL OBJETO DEL JSON
                            JSONObject obj = new JSONObject(response);
                            //OBTENER EL ARREGLO DE OBJETOS JSON
                            JSONArray listaHeroes = obj.getJSONArray("heroes");

                            //UTILIZAR LA LIBRERIA GSON NO ESTA COMPPLETO
                            Gson gson = new Gson();

                            //OJETO DE TIPO TYPE PARA DEFINIR EL TIPO
                            Type listType = new TypeToken<List<Super>>(){}.getType();
                            List<Super> list = gson.fromJson(listaHeroes.toString(), listType);

                            //ITERAR LISTA DE JSON SERIALIZADOS
                            for (Super item : list) {
                                listSuper.add(new Super(item.getName(), item.getUrl()));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //LLAMAR EL METODO DE PONER LA INFO
                        llenarCards();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //MOSTRAR ERROR SI OCURRE
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //AGREGAR PETICION A LA COLA
        requestQueue.add(stringRequest);
    }

    //PONER INFO EN LAS VIEW CARDS
    private void llenarCards() {
        rvSuper = findViewById(R.id.rvSuper);
        layoutManager = new LinearLayoutManager(this);
        rvSuper.setLayoutManager(layoutManager);
        adapter = new AdaptadorSuper(getApplicationContext(), listSuper);
        rvSuper.setAdapter(adapter);
    }

    //CLASE PARA SERIALIZAR OBJETOS JSON, LOS ATRIBUTOS DEBEN LLAMARSE IGUALEEES
    class Super{
        private String name;
        private String imageurl;

        public Super(String name, String url) {
            this.name = name;
            this.imageurl = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return imageurl;
        }
    }
}