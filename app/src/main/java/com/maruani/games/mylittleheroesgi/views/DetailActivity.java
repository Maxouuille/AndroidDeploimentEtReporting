package com.maruani.games.mylittleheroesgi.views;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView weaponTextViewSP = findViewById(R.id.activity_detail_weapon_SharedPreferences_textView);
        TextView pseudoTextViewSp = findViewById(R.id.activity_detail_pseudo_SharedPreferences_textView);
        TextView genderTextViewSp = findViewById(R.id.activity_detail_gender_SharedPreferences_textView);
        TextView birthdateTextViewSp = findViewById(R.id.activity_detail_birthdate_SharedPreferences_textView);
        TextView WeaponTextView = findViewById(R.id.activity_detail_weapon_textView);

        pseudoTextViewSp.setText(getSharedPreferences("shared_pref",MODE_PRIVATE).getString("pseudo", "error"));
        genderTextViewSp.setText(getSharedPreferences("shared_pref",MODE_PRIVATE).getString("gender", "error"));
        birthdateTextViewSp.setText(getSharedPreferences("shared_pref",MODE_PRIVATE).getString("birthdate", "error"));
        WeaponTextView.setText("Your weapon : " + getSharedPreferences("shared_pref",MODE_PRIVATE).getString("weaponName", "error"));
        Glide.with(weaponTextViewSP).load(getSharedPreferences("shared_pref",MODE_PRIVATE).getString("weaponImage", "error")).into(weaponTextViewSP);



    }
}
