package com.grupo1.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Principal extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private WebView webVideo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        webVideo = findViewById(R.id.webVideo);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        selectBottomNavItem(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home){
                    Intent intent = new Intent(Principal.this, Principal.class);
                    startActivity(intent);
                }else if(id == R.id.articulos) {
                    Intent intent = new Intent(Principal.this, Articulos.class);
                    startActivity(intent);
                }else if(id == R.id.setting){

                }
                return true;
            }
        });


        //video de youtube
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sXCIb7hhzDA\" title=\"COP16: Qué es, por qué es importante y qué le deja a Colombia | El Tiempo\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webVideo.loadData(video,"text/html","utf-8");
        webVideo.getSettings().setJavaScriptEnabled(true);
        webVideo.setWebChromeClient(new WebChromeClient());

    }
    private void selectBottomNavItem(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId); }

}