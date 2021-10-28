package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.mz.policemanager.R;
import co.mz.policemanager.adapter.MultasAdapter;
import co.mz.policemanager.entity.Categoria;
import co.mz.policemanager.entity.Distrito;
import co.mz.policemanager.entity.Driver;
import co.mz.policemanager.entity.Fabricante;
import co.mz.policemanager.entity.Inflacao;
import co.mz.policemanager.entity.LocalEmissao;
import co.mz.policemanager.entity.Localidade;
import co.mz.policemanager.entity.Modelo;
import co.mz.policemanager.entity.Multa;
import co.mz.policemanager.entity.Provincia;
import co.mz.policemanager.entity.StatusMulta;
import co.mz.policemanager.entity.TipoInflacao;
import co.mz.policemanager.entity.Trasito;
import co.mz.policemanager.entity.Veiculo;
import co.mz.policemanager.events.RecyclerItemClickListener;
import co.mz.policemanager.utils.APIUtils;

public class MultaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Multa> multaList;
    private static final String BASE_URL = APIUtils.getUrlBase()+"multas";
    private Button emitirMulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R. layout.activity_multa);
        getSupportActionBar().hide();

        recyclerView  = findViewById(R.id.recyclerMultas);
        multaList = new ArrayList<>();
        getMultas();
        MultasAdapter multasAdapter = new MultasAdapter(multaList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(MultaActivity.this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(multasAdapter);

        //Eventos do RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Multa multa = multaList.get(position);
                                Intent multaDetalhesIntent = new Intent(MultaActivity.this, MultaDetalhesActivity.class);
                                multaDetalhesIntent.putExtra("multa", multa);
                                startActivity(multaDetalhesIntent);
                            }
                            @Override
                            public void onLongItemClick(View view, int position) {
                                Multa multa = multaList.get(position);
                                System.out.println(multa);
                                Toast.makeText(getApplicationContext(),"Longo click",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        emitirMulta = findViewById(R.id.emitir_multa);

        emitirMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emitirMultaIntent = new Intent(MultaActivity.this, EmitirMultaActivity.class);
                emitirMultaIntent.putExtra("accao",1);
                startActivity(emitirMultaIntent);
                finish();
            }
        });

    }

    public void getMultas(){
        RequestQueue queue = Volley.newRequestQueue(MultaActivity.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){

                                JSONObject multaJson = array.getJSONObject(i);

                                JSONObject transitoJson = multaJson.getJSONObject("user");
                                String transitoCode = transitoJson.getString("code");
                                String transitoEmail = transitoJson.getString("email");
                                String transitoFirstName = transitoJson.getString("firstName");
                                String transitoLastName = transitoJson.getString("lastName");
                                Trasito transito = new Trasito(transitoLastName,transitoEmail,transitoFirstName,transitoCode);

                                //Veiculo Properties
                                JSONObject veiculoJson = multaJson.getJSONObject("veiculo");

                                //Drivers Properties
                                JSONObject driverJson = multaJson.getJSONObject("driver");
                                Long driverId = veiculoJson.getLong("id");
                                String nomeDriver = driverJson.getString("nome");
                                String apelidoDriver = driverJson.getString("apelido");
                                String documentoIdDriver = driverJson.getString("documentoId");
                                String cartaConducaoDriver = driverJson.getString("cartaConducao");
                                String driverCode = driverJson.getString("driverCode");
                                Driver driver = new Driver(driverId,driverCode,nomeDriver,apelidoDriver,documentoIdDriver,cartaConducaoDriver);

                                //Fabricante Properties
                                JSONObject fabricanteJson = veiculoJson.getJSONObject("fabricante");
                                Long fabricanteId = fabricanteJson.getLong("id");
                                String fabricanteCode = fabricanteJson.getString("fabricanteCode");
                                String fabricanteNome = fabricanteJson.getString("nome");
                                Fabricante fabricante = new Fabricante(fabricanteId,fabricanteCode, fabricanteNome);

                                //Modelo Properties
                                JSONObject modeloJson = veiculoJson.getJSONObject("modelo");
                                Long modeloId = veiculoJson.getLong("id");
                                String modeloCode = modeloJson.getString("modeloCode");
                                String modeloDescricao = modeloJson.getString("descricao");
                                Categoria categoria = Categoria.valueOf(modeloJson.getString("categoria"));
                                Modelo modelo = new Modelo(modeloId,modeloCode,modeloDescricao,categoria);

                                Long veiculoId = veiculoJson.getLong("id");
                                String veiculoCode = veiculoJson.getString("veiculoCode");
                                String marcaVeiculo = veiculoJson.getString("mark");
                                String colorVeiculo = veiculoJson.getString("color");
                                String placaVeiculo = veiculoJson.getString("placCar");
                                Veiculo veiculo = new Veiculo(veiculoId,veiculoCode,marcaVeiculo,placaVeiculo,colorVeiculo,modelo,fabricante);

                                JSONObject localEmissaoJson = multaJson.getJSONObject("localEmissao");
                                JSONObject localidadeJson = localEmissaoJson.getJSONObject("localidade");
                                JSONObject distritoObject = localidadeJson.getJSONObject("distrito");
                                JSONObject provinciaObject = distritoObject.getJSONObject("provincia");

                                Long provinciaId = provinciaObject.getLong("id");
                                String provinciaCode = provinciaObject.getString("provinciaCode");
                                String provinciaNome = provinciaObject.getString("nome");
                                String provinciaSigla = provinciaObject.getString("sigla");
                                Provincia provincia = new Provincia(provinciaId,provinciaCode,provinciaSigla,provinciaNome);

                                Long distritoId = distritoObject.getLong("id");
                                String distritoCode = distritoObject.getString("distritoCode");
                                String distritoNome = distritoObject.getString("nome");
                                Distrito distrito = new Distrito(distritoId,distritoCode,distritoNome,provincia);

                                Long localidadeId = localidadeJson.getLong("id");
                                String localidadeCode = localidadeJson.getString("localidadeCode");
                                String localidadeNome = localidadeJson.getString("nome");
                                Localidade localidade = new Localidade(localidadeId,localidadeCode,localidadeNome,distrito);

                                Long localEmissaoId = localEmissaoJson.getLong("id");
                                String localEmissaoCode = localEmissaoJson.getString("localEmissaoCode");
                                LocalEmissao localEmissao = new LocalEmissao(localEmissaoId,localEmissaoCode,localidade);

                                JSONObject inflacaOJson = multaJson.getJSONObject("inflacao");
                                Long inflacaoId = inflacaOJson.getLong("id");
                                String inflacaoCode = inflacaOJson.getString("inflacaoCode");
                                TipoInflacao tipoInflacao = TipoInflacao.valueOf(inflacaOJson.getString("tipoInflacao"));
                                String inflacaoDescricao = inflacaOJson.getString("descricao");
                                Inflacao inflacao = new Inflacao(inflacaoId, inflacaoCode, tipoInflacao, inflacaoDescricao);

                                Long id = multaJson.getLong("id");
                                String multaCode = multaJson.getString("multaCode");
                                BigDecimal valorMultado = BigDecimal.valueOf(multaJson.getDouble("valorMultado"));
                                String descricao = multaJson.getString("descricao");
                                String statusMultaStr = multaJson.getString("statusMulta");
                                StatusMulta statusMulta = StatusMulta.valueOf(statusMultaStr);
                                Multa multa = new Multa(id,multaCode,driver,veiculo,transito,inflacao,valorMultado,descricao,statusMulta,localEmissao);

                                multaList.add(multa);
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
                Toast.makeText(MultaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}