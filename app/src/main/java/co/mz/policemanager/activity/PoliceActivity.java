package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import co.mz.policemanager.adapter.UserAdapter;
import co.mz.policemanager.entity.Trasito;
import co.mz.policemanager.utils.APIUtils;


public class PoliceActivity extends AppCompatActivity {

    private Button btnAdd;
    private RecyclerView recyclerView;
    private List<Trasito> trasitoList = new ArrayList<>();
    private static final String BASE_URL = APIUtils.getUrlBase()+"users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        getSupportActionBar().hide();

        recyclerView  = findViewById(R.id.recyclerMultas);
        getUsers();
        //Configurar Adapter
        UserAdapter userAdapter = new UserAdapter(trasitoList);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(userAdapter);

        btnAdd = findViewById(R.id.emitir_multa);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoliceActivity.this, NewPoliceActivity.class);
                //intent.addFlags(1);
                startActivity(intent);
            }
        });
    }

    public void getUsers(){
        RequestQueue queue = Volley.newRequestQueue(PoliceActivity.this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String code = object.getString("code");
                                String email = object.getString("email");
                                String firstName = object.getString("firstName");
                                String lastName = object.getString("lastName");
                                String createOn = object.getString("createOn");
                                String updateOn = object.getString("updateOn");
                                System.out.println("CODIGO :"+code);
                                Trasito trasito = new Trasito(lastName,email,firstName,code);
                                trasitoList.add(trasito);
                            }
                        }catch (Exception e){

                        }
                        Toast.makeText(PoliceActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
                Toast.makeText(PoliceActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println("String Request :"+stringRequest);
        queue.add(stringRequest);
        //Volley.newRequestQueue(PoliceActivity.this).add(stringRequest);
    }


}