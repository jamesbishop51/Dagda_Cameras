package edu.jbishop.dagda_cameras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import static android.content.ContentValues.TAG;
import static edu.jbishop.dagda_cameras.R.*;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*this pulls the device token for firebase which is used to create the endpoint on aws
        for the notifications
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        Toast.makeText(MainActivity.this,token, Toast.LENGTH_SHORT).show();*/

        BottomNavigationView bottomNavigationView = findViewById(id.bottom_nav);
        bottomNavigationView.setSelectedItemId(id.nav_Cam);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId())
            {
                case id.nav_Cam:
                    return true;
                //due to limitations with the api the android application isn't able to view the database
                //so the storage page has been removed
                 //case id.nav_storage:
                case id.nav_user:
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);

                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });



        Button startButton = (Button)findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });





    }

}



