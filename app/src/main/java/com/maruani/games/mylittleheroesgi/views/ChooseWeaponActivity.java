package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseWeaponActivity extends AppCompatActivity {

    @BindView(R.id.activity_choose_weapon_rcv) RecyclerView weaponChoiceRcv;
    private WeaponAdapter weaponAdapter;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_weapon);
      ButterKnife.bind(this);
      initRecyclerView();
      loadData();

  }
    private void initRecyclerView(){
      weaponChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
      weaponAdapter = new WeaponAdapter();
      weaponChoiceRcv.setAdapter(weaponAdapter);
      weaponAdapter.setItemClickListener(new WeaponAdapter.ItemClickListener() {
      ImageView imageView = findViewById(R.id.activity_choosed_weapon_imageview);
          @Override
          public void onClick(Weapon weapon) {
              Toast.makeText(ChooseWeaponActivity.this, weapon.getName(), Toast.LENGTH_SHORT).show();
              Glide.with(imageView).load(weapon.getPictureUrl()).into(imageView);
              SharedPreferences preferences = getSharedPreferences("shared_pref",MODE_PRIVATE);
              preferences.edit().putString("weaponName", weapon.getName()).apply();
              preferences.edit().putString("weaponImage", weapon.getPictureUrl()).apply();
          }
      });
    }
    private void loadData(){
        NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
            @Override
            public void onSuccess(List<Weapon> data) {
                weaponAdapter.setWeaponList(data);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
    @OnClick(R.id.activity_choose_weapon_next_btn) void onNextButtonClick() {

        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);

    }
}
