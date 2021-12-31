package com.example.imagelibraryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SharePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtn_Saturation, mBtn_Vignette, mBtn_Color_Overlay, mBtn_Contrast, mBtn_Color_Bright;
    ImageView mPhoto;
    Bitmap OriginalBitmap;


    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_photo);

        //TODO: SORRY FOR THE WRONG NAMING, THIS ACTIVITY IS FOR PHOTO EDITING

        mPhoto = findViewById(R.id.IMG_Photo_Share);
        mBtn_Saturation = findViewById(R.id.Btn_Saturation);
        mBtn_Vignette = findViewById(R.id.Btn_Vignette);
        mBtn_Color_Overlay = findViewById(R.id.Btn_Color_Overlay);
        mBtn_Contrast = findViewById(R.id.Btn_Contrast);
        mBtn_Color_Bright = findViewById(R.id.Btn_Color_Bright);


        mBtn_Saturation.setOnClickListener(this);
        mBtn_Vignette.setOnClickListener(this);
        mBtn_Color_Overlay.setOnClickListener(this);
        mBtn_Contrast.setOnClickListener(this);
        mBtn_Color_Bright.setOnClickListener(this);


        BitmapDrawable drawable = (BitmapDrawable) mPhoto.getDrawable();
        OriginalBitmap = drawable.getBitmap();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Btn_Color_Bright:

            Filter myFilter = new Filter();
            myFilter.addSubFilter(new BrightnessSubFilter(30));

            BitmapDrawable drawable = (BitmapDrawable) mPhoto.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Bitmap outputImage = myFilter.processFilter(image);

            mPhoto.setImageBitmap(outputImage);
            break;

            case R.id.Btn_Saturation:
                Filter fooFilter = new Filter();
                fooFilter.addSubFilter(new SaturationSubFilter(1.3f));

                Bitmap image2 = OriginalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage2 = fooFilter.processFilter(image2);

                mPhoto.setImageBitmap(outputImage2);
                break;

            case R.id.Btn_Vignette:
                Filter myFilter2 = new Filter();
                myFilter2.addSubFilter(new VignetteSubFilter(this, 100));

                Bitmap image3 = OriginalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage3 = myFilter2.processFilter(image3);

                mPhoto.setImageBitmap(outputImage3);
                break;

            case R.id.Btn_Color_Overlay:
                Filter myFilter4 = new Filter();
                myFilter4.addSubFilter(new ColorOverlaySubFilter(100, .2f, .2f, .0f));


                Bitmap image4 = OriginalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage4 = myFilter4.processFilter(image4);
                mPhoto.setImageBitmap(outputImage4);
                break;

            case R.id.Btn_Contrast:
                Filter myFilter5 = new Filter();
                myFilter5.addSubFilter(new ContrastSubFilter(1.2f));

                Bitmap image5 = OriginalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage5 = myFilter5.processFilter(image5);
                mPhoto.setImageBitmap(outputImage5);
                break;



        }


    }


}