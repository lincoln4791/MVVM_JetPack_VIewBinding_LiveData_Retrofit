package com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.R;
import com.google.gson.annotations.SerializedName;

public class Model_MainActivity {


    private String name;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("url")
    private String image;

    private String counter;

    public Model_MainActivity(String name, String lifeSpan, String image,String counter) {
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.image = image;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageVIew, String imageUrl ){
        Glide.with(imageVIew).load(imageUrl).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(imageVIew);

    }

}
