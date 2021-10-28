package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.mz.policemanager.R;
import co.mz.policemanager.adapter.VeiculoAdapter;
import co.mz.policemanager.entity.Fabricante;
import co.mz.policemanager.entity.Veiculo;
import co.mz.policemanager.entity.Categoria;
import co.mz.policemanager.entity.Modelo;
import co.mz.policemanager.events.RecyclerItemClickListener;
import co.mz.policemanager.utils.APIUtils;

public class CarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Veiculo> veiculoList = new ArrayList<>();
    private static final String BASE_URL = APIUtils.getUrlBase()+"cars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        getSupportActionBar().hide();

        recyclerView  = findViewById(R.id.recyclerCarros);
        this.veiculoList = getVeiculos();
        VeiculoAdapter veiculoAdapter = new VeiculoAdapter(this.veiculoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(veiculoAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener(){

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onItemClick(View view, int position) {
                                Veiculo veiculo = veiculoList.get(position);
                                Intent emitirMultaIntent = new Intent(CarActivity.this,EmitirMultaActivity.class);
                                emitirMultaIntent.putExtra("veiculo",veiculo);
                                System.out.println(veiculo);
                                startActivity(emitirMultaIntent);
                                finish();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        })
        );
    }


    public List<Veiculo> getVeiculos(){
        List<Veiculo> veiculoList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(CarActivity.this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String code = object.getString("veiculoCode");
                                String marca = object.getString("mark");
                                String color = object.getString("color");
                                String placa = object.getString("placCar");
                                //Modelo Proprieties
                                JSONObject modeloJson = object.getJSONObject("modeloRest");
                                String modeloCode = modeloJson.getString("modeloCode");
                                String descricao = modeloJson.getString("descricao");
                                //SetCategoria
                                Categoria categoria = Categoria.valueOf(modeloJson.getString("categoria"));
                                //Fabricante Proprieties
                                JSONObject fabricanteObject = object.getJSONObject("fabricanteRest");
                                String fabricanteCode = fabricanteObject.getString("fabricanteCode");
                                String fabricanteNome = fabricanteObject.getString("nome");

                                Fabricante fabricante = new Fabricante(fabricanteCode, fabricanteNome);
                                Modelo modelo = new Modelo(modeloCode,descricao,categoria);
                                Veiculo veiculo = new Veiculo(code,marca,placa,color);

                                veiculo.setModelo(modelo);
                                veiculo.setFabricante(fabricante);

                                veiculoList.add(veiculo);
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
                Toast.makeText(CarActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
        return veiculoList;
    }
}