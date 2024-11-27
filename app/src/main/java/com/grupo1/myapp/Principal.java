package com.grupo1.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private WebView webVideo;
    RecyclerView recyclerView;
    List<Preguntas> preguntasList;

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
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }else if(id == R.id.articulos) {
                    Intent intent = new Intent(Principal.this, List_Articulos.class);
                    startActivity(intent);
                    return true;
                }else if(id == R.id.setting){
                    Intent intent = new Intent(Principal.this, SettingsActivity.class);
                    startActivity(intent);
                    return true;
                }
                return true;
            }
        });


        //video de youtube
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sXCIb7hhzDA\" title=\"COP16: Qué es, por qué es importante y qué le deja a Colombia | El Tiempo\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webVideo.loadData(video,"text/html","utf-8");
        webVideo.getSettings().setJavaScriptEnabled(true);
        webVideo.setWebChromeClient(new WebChromeClient());

        recyclerView = findViewById(R.id.faqComponent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        setRecyclerView();



    }

    private void setRecyclerView() {
        PreguntasAdapter preguntasAdapter = new PreguntasAdapter(preguntasList);
        recyclerView.setAdapter(preguntasAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        preguntasList = new ArrayList<>();
        preguntasList.add(new Preguntas("Es la Conferencia de las Partes, el órgano supremo que toma las decisiones del Convenio sobre la Diversidad Biológica.\n" +
                "\n","¿Qué es la COP de biodiversidad"));
        preguntasList.add(new Preguntas("El Convenio sobre la diversidad Biológica (CDB) fue firmado por líderes de 150 países en la Cumbre de la Tierra de Río de Jaineiro en 1992. Este convenio promueve el Desarrollo sostenible a través de una visión que involucre a los ecosistemas y a las personas.","¿Qué es el Convenio sobre la Diversidad Biológica?"));
        preguntasList.add(new Preguntas("Se llevará a cabo en Cali, Colombia entre el 21 de octubre y el 1 de noviembre de 2024.\n" +
                "\n","¿Dónde y cuándo será la COP16?"));
        preguntasList.add(new Preguntas("La Zona Azul es el recinto donde se desarrollará la conferencia de las partes, es gestionada por el Secretariado de la Convención de Diversidad Biológica y administrada por el Programa de las Naciones Unidas para el Medio Ambiente (PNUMA). La Zona Verde será el epicentro de la estrategia de movilización y participación y donde se materializará la COP de la gente. En este espacio, las empresas, organizaciones y la sociedad civil compartirán su visión y compromisos para la protección de la biodiversidad.\n" +
                "\n","¿Qué son la Zona Azul y la Zona Verde?"));
        preguntasList.add(new Preguntas("Implementación del Marco Global de Biodiversidad Kunming-Montreal. Se espera que los países presenten la actualización de sus Estrategias y Planes de Acción Nacionales sobre Biodiversidad (NBSAPs).\n" +
                "Revisión del estado de implementación del nuevo marco. La COP16 será un momento clave para que los gobiernos revisen el estado de implementación del nuevo marco, a través de los NBSAPs.\n" +
                "Desarrollo del marco de seguimiento. Se espera que durante la COP16 se siga desarrollando el marco de seguimiento y se continúe avanzando en la movilización de recursos para la implementación del Marco Global.\n" +
                "Mecanismo multilateral sobre el reparto justo y equitativo de los beneficios. La COP16 deberá finalizar y poner en funcionamiento un mecanismo multilateral sobre el reparto justo y equitativo de los beneficios derivados del uso de la información de secuencias digitales sobre recursos genéticos.","¿Qué acuerdos se esperan en esta conferencia?"));
    }

    private void selectBottomNavItem(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId); }

}