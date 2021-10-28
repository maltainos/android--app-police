package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

import co.mz.policemanager.entity.Trasito;
import co.mz.policemanager.R;
import co.mz.policemanager.utils.APIUtils;

public class NewPoliceActivity extends AppCompatActivity {

    private ImageView imageBack;
    private Button btnSavePolice;
    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private  EditText confirmPassword;
    private TextView messagePolice;
    private Trasito trasito;
    private static final String BASE_URL = APIUtils.getUrlBase()+"users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_police);
        getSupportActionBar().hide();

        imageBack = (ImageView) findViewById(R.id.police_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPoliceActivity.this,
                        PoliceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSavePolice = (Button) findViewById(R.id.btn_seve_police);
        btnSavePolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = findViewById(R.id.username);
                firstName = findViewById(R.id.firstname);
                lastName = findViewById(R.id.lastname);
                password = findViewById(R.id.password);
                confirmPassword = findViewById(R.id.confirm);

                if(confirmPassword(password.getText().toString(),
                        confirmPassword.getText().toString())){
                    trasito = new Trasito(null, username.getText().toString(),
                            lastName.getText().toString(), firstName.getText().toString(),password.getText().toString());
                    saveUser(trasito);
                }
            }
        });
    }


    private boolean confirmPassword(String mainPassword, String secundPassword){
        messagePolice = findViewById(R.id.message_police);
        if(mainPassword.isEmpty() || secundPassword.isEmpty()){
            Toast.makeText(NewPoliceActivity.this,
                    "Campo senha nao pode ser vazia",
                    Toast.LENGTH_LONG);
            messagePolice.setText("Campo senha nao pode ser vazia");
            return false;
        }
        if(mainPassword.length() < 8 || secundPassword.length() < 8){
            Toast.makeText(NewPoliceActivity.this,
                    "Senha deve variar entre 8-12 caracteres",
                    Toast.LENGTH_LONG);
            messagePolice.setText("Senha deve variar entre 8-12 caracteres");
            return false;
        }
        if(!mainPassword.equals(secundPassword)){
            Toast.makeText(NewPoliceActivity.this,
                    "Senhas nao podem ser diferentes",
                    Toast.LENGTH_SHORT);
            messagePolice.setText("Senhas nao podem ser diferentes");
            return false;
        }
        return true;
    }

    public void saveUser(Trasito trasito){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(NewPoliceActivity.this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", trasito.getEmail());
            jsonBody.put("lastName", trasito.getLastName());
            jsonBody.put("firstName", trasito.getFirstName());
            jsonBody.put("password", trasito.getPassword());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
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
                public String getBodyContentType() {return "application/json; charset=utf-8";
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
                        System.out.println(String.valueOf(response.data));
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);
            Toast.makeText(NewPoliceActivity.this,
                    "Transito cadastrado com sucesso..!",
                    Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}