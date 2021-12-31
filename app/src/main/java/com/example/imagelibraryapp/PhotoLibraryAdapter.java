package com.example.imagelibraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoLibraryAdapter extends RecyclerView.Adapter<PhotoLibraryAdapter.PhotoViewHolder>{


    Activity mActivity;
    ArrayList<Photo_Model> mPhotoModel;

    public PhotoLibraryAdapter(Activity mActivity, ArrayList<Photo_Model> mPhotoModel) {
        this.mActivity = mActivity;
        this.mPhotoModel = mPhotoModel;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_photo, parent, false);
            return new PhotoViewHolder(v, mActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {

        Photo_Model photoModel = mPhotoModel.get(position);
        holder.mMainImageLibrary.setImageResource(photoModel.getmPhotoLibrary());
        holder.mPhotoLibraryTitle.setText(photoModel.getmPhotoTitle());
        holder.mPhotoDesc.setText(photoModel.getmPhotoDescription());

        SharedPreferences preferences = mActivity.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE);
        preferences.getBoolean("ON ACTIVITIES", true);

        holder.mHandImageLike.setTag("addLike");

        holder.mHeartLike.setTag("addHeart");

        setLikeHandClick(holder.mHandImageLike );

        setHeartLikeClick(holder.mHeartLike);

        _sharePhotoNow(holder.mSharePhoto, photoModel, holder.mMainImageLibrary);


    }

    //TODO: For Photo Share and text in recyclerview
    private void _sharePhotoNow(ImageView mSharePhoto, Photo_Model photoModel, ImageView mMainImageLibrary) {

        mSharePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = getBitMapFromView(mMainImageLibrary);
                try {
                    File file = new File(Generic.context.getExternalCacheDir(), "ayamrandam.jpg");
                    FileOutputStream fileOutputStream = null;

                    fileOutputStream = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    file.setReadable(true, false);


                    //TODO:share
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, photoModel.getmPhotoDescription());
                    Uri PhotoUrl = FileProvider.getUriForFile(Generic.context,
                            Generic.context.getApplicationContext().getPackageName() +
                                    ".provider", file);

                    shareIntent.putExtra(Intent.EXTRA_STREAM, PhotoUrl);
                    shareIntent.setType("image/jpeg");
                    shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mActivity.startActivity(Intent.createChooser(shareIntent, null));


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }



    @Override
    public int getItemCount() {
        return mPhotoModel.size();
    }

    public static class PhotoViewHolder extends  RecyclerView.ViewHolder{

      TextView mPhotoLibraryTitle, mPhotoDesc;
      ImageView mMainImageLibrary, mHandImageLike, mHeartLike, mSharePhoto;
      RatingBar mRateStar;
      LinearLayout mLinearLikeHeart;


        public PhotoViewHolder(@NonNull View itemView, Activity mActivity) {
            super(itemView);

            mPhotoLibraryTitle = itemView.findViewById(R.id.Tv_Photo_Title);
            mMainImageLibrary = itemView.findViewById(R.id.IMG_Photo_Library);
            mPhotoDesc = itemView.findViewById(R.id.TV_description_photoLibrary);
            mHandImageLike = itemView.findViewById(R.id.IMG_handLike);
            mHeartLike = itemView.findViewById(R.id.IMG_LoveLike);
            mRateStar = itemView.findViewById(R.id.Rating_bar);
            mLinearLikeHeart = itemView.findViewById(R.id.LinearLikeAndHeart);
            mSharePhoto = itemView.findViewById(R.id.IMG_Share);

        }
    }

    //TODO:for hand like
    private void setLikeHandClick(ImageView mHandImageLike) {

        mHandImageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHandImageLike.getTag().equals("addLike")){
                    mHandImageLike.setImageResource(R.drawable.postliked);
                    mHandImageLike.setTag("RemoveLike");
                }else if(mHandImageLike.getTag().equals("RemoveLike")){
                    mHandImageLike.setImageResource(R.drawable.handlike);
                    mHandImageLike.setTag("addLike");
                }
            }
        });
    }


    //TODO:for like heart
    private void setHeartLikeClick(ImageView mHeartLike) {

        mHeartLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeartLike.getTag().equals("addHeart")){
                    mHeartLike.setImageResource(R.drawable.redheart);
                    mHeartLike.setTag("RemoveHeartLike");
                }else if(mHeartLike.getTag().equals("RemoveHeartLike")){
                    mHeartLike.setImageResource(R.drawable.heartlike);
                    mHeartLike.setTag("addHeart");
                }
            }
        });
    }



    //TODO:To convert image to bitmap
    public static Bitmap getBitMapFromView(View view){
        Bitmap returnbitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas(returnbitmap);
        Drawable bgdrawable = view.getBackground();

        if(bgdrawable != null){
            bgdrawable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);
        return returnbitmap;
    }

}
