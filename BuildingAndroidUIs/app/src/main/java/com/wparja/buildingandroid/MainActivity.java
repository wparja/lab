package com.wparja.buildingandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wparja.buildingandroid.Rendering.BasicRenderingActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.rendering).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BasicRenderingActivity.class)));

    }
}