package com.senai.sc.event;

import android.widget.AdapterView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.database.EventoDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Event! | Sua agenda de eventos");
        lvEventos = findViewById(R.id.lvEventos);
        definirOnClickListenerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.listarEventos());
        lvEventos.setAdapter(adapterEventos);
    }

    private void definirOnClickListenerView() {
        lvEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickCriarEvento(View v) {
        Intent intentCriarEvento = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intentCriarEvento);
    }

    public void onClickLocais(View v) {
        Intent intentLocais = new Intent(MainActivity.this, ListarLocaisActivity.class);
        startActivity(intentLocais);
        finish();
    }
}