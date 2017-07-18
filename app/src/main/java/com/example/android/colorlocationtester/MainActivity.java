package com.example.android.colorlocationtester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorCoordinates test = new colorCoordinates(this);
        ImageView img = (ImageView) findViewById(R.id.towerMapping);
    }

}
