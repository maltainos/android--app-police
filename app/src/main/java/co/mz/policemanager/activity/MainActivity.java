package co.mz.policemanager.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import co.mz.policemanager.R;

public class MainActivity extends AppCompatActivity {

    private CardView cardView;
    private NavigationView nav;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private int flags = 1;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        flags = intent.getFlags();


        if(flags != 1){
            Intent loginIntent = new Intent(
                    MainActivity.this,
                    LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        /*
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this,
                                "Menu About has been clicked",
                                Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this,
                                "Menu Home has been clicked",
                                Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        */
        showView();
    }

    protected void showView(){
        showViewCars();
        showViewPolice();
        showViewDrive();
        showViewSettings();
        showViewReports();
        showViewProperties();
        showViewMultas();
        showViewPrivacity();
    }

    private void showViewPolice(){
        this.cardView = findViewById(R.id.police);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, PoliceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewCars(){
        this.cardView = findViewById(R.id.cars);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, CarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewDrive(){
        this.cardView = findViewById(R.id.driver);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, DriverActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewProperties(){
        this.cardView = findViewById(R.id.property);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, PropertyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewSettings(){
        this.cardView = findViewById(R.id.settings);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewMultas(){
        this.cardView = findViewById(R.id.multas);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MultaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewPrivacity(){
        this.cardView = findViewById(R.id.privacity);

        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, PrivacityActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showViewReports(){
        this.cardView = findViewById(R.id.reports);
        this.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}