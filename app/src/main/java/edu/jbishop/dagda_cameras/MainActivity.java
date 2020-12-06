package edu.jbishop.dagda_cameras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.jbishop.dagda_cameras.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.testing.setText("initial commit");

    }
}