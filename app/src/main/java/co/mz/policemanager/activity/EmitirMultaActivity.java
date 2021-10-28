package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.mz.policemanager.R;
import co.mz.policemanager.entity.Distrito;
import co.mz.policemanager.entity.Driver;
import co.mz.policemanager.entity.Inflacao;
import co.mz.policemanager.entity.LocalEmissao;
import co.mz.policemanager.entity.Localidade;
import co.mz.policemanager.entity.Multa;
import co.mz.policemanager.entity.Provincia;
import co.mz.policemanager.entity.StatusMulta;
import co.mz.policemanager.entity.TipoInflacao;
import co.mz.policemanager.entity.Trasito;
import co.mz.policemanager.entity.Veiculo;
import co.mz.policemanager.utils.APIUtils;

public class EmitirMultaActivity extends AppCompatActivity {

    private static final String BASE_URL = APIUtils.getUrlBase()+"cars/";

    private Veiculo veiculo;
    private Driver motorista;
    private Trasito trasito;
    private LocalEmissao localEmissao;
    private Localidade localidade;
    private Distrito distrito;
    private Provincia provincia;
    private Multa multa;

    private EditText placaCarroEmitirMulta;
    private EditText cartaConducaoEmitirMulta;
    private ImageView imaSalvarMulta;
    private EditText localidadeMulta;
    private EditText distritoMulta;
    private EditText provinciaMulta;
    private EditText valorMulta;
    private EditText descricaoMulta;
    private RadioButton radioButtonLeve;
    private RadioButton radioButtonMedia;
    private RadioButton radioButtonGrave;
    Inflacao inflacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emitir_multa);
        getSupportActionBar().hide();

        placaCarroEmitirMulta = findViewById(R.id.placaEmitirMulta);
        cartaConducaoEmitirMulta = findViewById(R.id.cartaEMitirMulta);
        imaSalvarMulta = findViewById(R.id.imgSalvar);
        localidadeMulta = findViewById(R.id.localidadeEmitirMulta);
        distritoMulta = findViewById(R.id.distritoEmitirMulta);
        provinciaMulta = findViewById(R.id.provinciaEmitirMulta);
        radioButtonLeve = findViewById(R.id.leve);
        radioButtonMedia = findViewById(R.id.media);
        radioButtonGrave = findViewById(R.id.grave);
        valorMulta = findViewById(R.id.valorEmitirMulta);
        descricaoMulta = findViewById(R.id.descricaoEmitirMulta);

        placaCarroEmitirMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veiculoListaIntent = new Intent(EmitirMultaActivity.this, CarActivity.class);
                Toast.makeText(EmitirMultaActivity.this, "Escolha um veiculo", Toast.LENGTH_SHORT).show();
                startActivity(veiculoListaIntent);
                finish();
            }
        });

        veiculo = (Veiculo) getIntent().getSerializableExtra("veiculo");
        if(veiculo != null)
            placaCarroEmitirMulta.setText(veiculo.getPlaca());

        imaSalvarMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motorista = new Driver();
                motorista.setCartaConducao(cartaConducaoEmitirMulta.getText().toString());
                veiculo.setPlaca(placaCarroEmitirMulta.getText().toString());

                localidade = new Localidade();
                localidade.setNome(localidadeMulta.getText().toString());

                distrito = new Distrito();
                distrito.setNome(distritoMulta.getText().toString());

                provincia = new Provincia();
                provincia.setNome(provinciaMulta.getText().toString());
                distrito.setProvincia(provincia);

                localidade.setDistrito(distrito);
                localEmissao = new LocalEmissao(localidade);
                System.out.println("localemissao :"+localEmissao);

                if(radioButtonLeve.isChecked())
                    inflacao = new Inflacao(1L,TipoInflacao.LEVE);
                if(radioButtonMedia.isChecked())
                    inflacao = new Inflacao(2L,TipoInflacao.MEDIA);
                if(radioButtonGrave.isChecked())
                    inflacao = new Inflacao(3L,TipoInflacao.GRAVE);

                String descricao = descricaoMulta.getText().toString();
                trasito = new Trasito();
                BigDecimal valorMultado = BigDecimal.valueOf(Double.valueOf(valorMulta.getText().toString()));
                multa = new Multa(motorista,veiculo,trasito,inflacao,valorMultado,descricao, StatusMulta.EMITIDA,localEmissao);
                saveMulta(multa);

            }
        });

    }

    private void saveMulta(Multa multa){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(EmitirMultaActivity.this);
            JSONObject jsonBody = new JSONObject();

            JSONObject jsonObjectVeiculo = new JSONObject();
            jsonObjectVeiculo.put("placCar", multa.getVeiculo().getPlaca());
            jsonBody.put("veiculo", jsonObjectVeiculo);
            JSONObject jsonObjectDriver = new JSONObject();
            jsonObjectDriver.put("cartaConducao", multa.getDriver().getCartaConducao());
            jsonBody.put("driver", jsonObjectDriver);
            JSONObject jsonObjectUser = new JSONObject();
            jsonObjectUser.put("id", 1);
            jsonBody.put("user", jsonObjectUser);
            jsonBody.put("valorMultado", multa.getValorMultado());
            jsonBody.put("descricao", multa.getDescricao());
            jsonBody.put("statusMulta", "EMITIDA");

            JSONObject jsonObjectProvincia = new JSONObject();
            jsonObjectProvincia.put("id", multa.getLocalEmissao().getLocalidade().getDistrito().getProvincia().getId());
            jsonObjectProvincia.put("nome", multa.getLocalEmissao().getLocalidade().getDistrito().getProvincia().getNome());

            JSONObject jsonObjectDistrito = new JSONObject();
            jsonObjectDistrito.put("id", multa.getLocalEmissao().getLocalidade().getDistrito().getId());
            jsonObjectDistrito.put("nome", multa.getLocalEmissao().getLocalidade().getDistrito().getNome());
            jsonObjectDistrito.put("provincia", jsonObjectProvincia);

            JSONObject jsonObjectLocalicalidade = new JSONObject();
            jsonObjectLocalicalidade.put("id", multa.getLocalEmissao().getLocalidade().getId());
            jsonObjectLocalicalidade.put("nome", multa.getLocalEmissao().getLocalidade().getNome());
            jsonObjectLocalicalidade.put("distrito", jsonObjectDistrito);

            JSONObject jsonObjectLocalEmissao = new JSONObject();
            jsonObjectLocalEmissao.put("localidade",jsonObjectLocalicalidade);
            jsonBody.put("localEmissao",jsonObjectLocalEmissao);

            JSONObject jsonObjectInflacao = new JSONObject();
            jsonObjectInflacao.put("id", multa.getInflacao().getId());
            jsonBody.put("inflacao",jsonObjectInflacao);

            System.out.println("JSON:\n"+jsonBody.toString());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL+multa.getVeiculo().getPlaca()+"/emitir-multa", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            System.out.println("STRING REQUEST :"+stringRequest);
            requestQueue.add(stringRequest);
            Toast.makeText(EmitirMultaActivity.this,
                    "Multa Emitida com sucesso..!",
                    Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}