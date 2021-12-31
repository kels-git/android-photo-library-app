package com.example.imagelibraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class PhotoListViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    PhotoLibraryAdapter mPhotoAdapter;

    static  ArrayList<Photo_Model> mPhotoModel= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list_view);

        mRecyclerView = findViewById(R.id.RecyclerView_PhotoLibrary);


        //TODO:Time to populate our recyclerview
        mPhotoModel.clear();
        mPhotoModel.add(new Photo_Model(R.drawable.nasilamek, "Nasi Lemak", "Nasi lemak (Malay fragrant rice dish cooked in coconut milk and pandan leaf) on rustic" +
                " wooden table top along side with recipe ingredient. Flat lay image with negative space." ));
        mPhotoModel.add(new Photo_Model(R.drawable.seafoodcurry, "Seafood Curry Laksa", "Recipe may contain peanuts, tree nuts, milk, fish and shellfish. A laksa is a simple way " +
                "to liven up the week. This one's hearty and full of our favourite seafood" ));
        mPhotoModel.add(new Photo_Model(R.drawable.thaichickensatay, "Thai Chicken Satay", "Thai chicken satay with peanut sauce on marble background" ));
        mPhotoModel.add(new Photo_Model(R.drawable.asiariceporriage, "Asia Rice Porridge ", "Directly Above View of Asian Rice Porridge Congee Bowl " ));
        mPhotoModel.add(new Photo_Model(R.drawable.ayamrandam, "Rendang Ayam", "Malaysian dish Rendang Ayam or dry curry chicken in a white plate" ));



        mPhotoAdapter = new PhotoLibraryAdapter(PhotoListViewActivity.this, mPhotoModel);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mPhotoAdapter);




    }





}