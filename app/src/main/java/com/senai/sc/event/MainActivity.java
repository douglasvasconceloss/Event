package com.senai.sc.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.senai.sc.event.classeEvento.Evento;

import java.util.ArrayList;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private ListView lvEventos;
    private ArrayAdapter<Evento> adapterEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Event! | Sua agenda de eventos");

        lvEventos = findViewById(R.id.lvEventos);
        ArrayList<Evento> listaEventos = this.criarListaEvento();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, listaEventos);
        lvEventos.setAdapter(adapterEventos);
    }

    private ArrayList<Evento> criarListaEvento() {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        eventos.add(new Evento(1, "Palestra POO", "12/11/2020", "SENAI/SC - Florianópolis"));
        eventos.add(new Evento(2,"Palestra SQL", "13/11/2020", "SENAI/SC - São José"));
        eventos.add(new Evento(3,"Mesa Redonda: Mulheres na TI", "14/11/2020", "SENAI/SC - Lages"));
        return eventos;
    }

    public void onClickCriarEvento(View v) {
        Intent intentCriarEvento = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intentCriarEvento);
    }
}