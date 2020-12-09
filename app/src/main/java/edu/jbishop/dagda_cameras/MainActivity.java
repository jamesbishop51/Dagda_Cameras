package edu.jbishop.dagda_cameras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static edu.jbishop.dagda_cameras.R.*;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        BottomNavigationView bottomNav = findViewById(id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new Camera_fragment()).commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case id.nav_Cam:
                            selectedFragment = new Camera_fragment();
                            break;
                        case id.nav_storage:
                            selectedFragment = new Storage_fragment();
                            break;
                        case id.nav_settings:
                            selectedFragment = new Settings_fragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


}



