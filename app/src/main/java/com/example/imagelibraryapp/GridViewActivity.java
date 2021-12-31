package com.example.imagelibraryapp;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewGrid;
    PhotoLibraryAdapter mPhotoAdapter;
    static ArrayList<Photo_Model> mPhotoModel= new ArrayList<>();

    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mRecyclerViewGrid = findViewById(R.id.RecyclerView_PhotoLibrary_Grid);


        mPhotoAdapter = new PhotoLibraryAdapter(  GridViewActivity.this, mPhotoModel);
        gridLayoutManager = new GridLayoutManager(GridViewActivity.this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerViewGrid.setLayoutManager(gridLayoutManager);
        mRecyclerViewGrid.setAdapter(mPhotoAdapter);

        //TODO:Time to populate our recyclerview
        mPhotoModel.clear();
        mPhotoModel.add(new Photo_Model(R.drawable.nasilamek, "Nasi Lemak", "Nasi lemak (Malay fragrant rice dish cooked in coconut milk and pandan leaf) on rustic" +
                " wooden table " ));
        mPhotoModel.add(new Photo_Model(R.drawable.seafoodcurry, "Seafood Curry Laksa", "Recipe may contain peanuts, tree nuts, milk, fish and shellfish.Seafood Curry Laksa " ));
        mPhotoModel.add(new Photo_Model(R.drawable.thaichickensatay, "Thai Chicken Satay", "Thai chicken satay with peanut sauce on marble background" ));
        mPhotoModel.add(new Photo_Model(R.drawable.asiariceporriage, "Asia Rice Porridge ", "Directly Above View of Asian Rice Porridge Congee Bowl " ));
        mPhotoModel.add(new Photo_Model(R.drawable.ayamrandam, "Rendang Ayam", "Malaysian dish Rendang Ayam or dry curry chicken in a white plate" ));






    }
}