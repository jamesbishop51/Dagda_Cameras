package edu.jbishop.dagda_cameras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {
    String email;
    String name;
    String imgURl;
    String verified;
    TextView tvEmail, tvName,tvVerified;
    ImageView ivUrl;
    Button BtnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_user);

        BtnLogOut=findViewById(R.id.logout);
        tvEmail=findViewById(R.id.userEmail);
        tvName=findViewById(R.id.userName);
        tvVerified=findViewById(R.id.userVerified);
        ivUrl=findViewById(R.id.userImage);

        //pulling the user details from the login class
        email = getIntent().getStringExtra("Email");
        verified = getIntent().getStringExtra("Verified");
        name = getIntent().getStringExtra("Name");
        imgURl = getIntent().getStringExtra("ImgURL");

        tvEmail.setText("Email: "+email);
        tvName.setText("Name: " +name);
        tvVerified.setText("verified: "+verified);
        Picasso.with(UserActivity.this).load(imgURl)
                .into(ivUrl);

        //sets up the page change on nav bar selection
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId())
            {
                case R.id.nav_Cam:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Email",email);
                    intent.putExtra("ImgURL",imgURl);
                    intent.putExtra("Name",name);
                    intent.putExtra("Verified",verified);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                //due to limitations with the api the android application isn't able to view the database
                //so the storage page has been removed
                //case id.nav_storage:
                case R.id.nav_user:
                    return true;
            }
            return false;
        });

        BtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout() {
        Intent intent = new Intent(this, logoutActivity.class);
        intent.putExtra(logoutActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }
}