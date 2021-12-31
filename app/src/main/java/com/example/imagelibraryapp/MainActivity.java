package com.example.imagelibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mNavigateListView, mNavigateGridView, mBtnShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigateListView = findViewById(R.id.Btn_to_ListView);
        mNavigateGridView = findViewById(R.id.Btn_to_GridView);
        mBtnShareButton = findViewById(R.id.Btn_PhotoShare);

        mNavigateListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PhotoListViewActivity.class);
                startActivity(i);
            }
        });

        mNavigateGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(i);
            }
        });

        mBtnShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SharePhotoActivity.class);
                startActivity(i);
            }
        });
    }
}