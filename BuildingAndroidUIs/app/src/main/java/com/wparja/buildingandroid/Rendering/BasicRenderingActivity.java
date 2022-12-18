package com.wparja.buildingandroid.Rendering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wparja.buildingandroid.R;

public class BasicRenderingActivity extends AppCompatActivity {

    CircularActivityIndicator c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_d);
    }
}