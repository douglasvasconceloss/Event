package com.senai.sc.event;

import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.database.EventoDAO;

public class MainActivity extends AppCompatActivity {

    private ListView lvEventos;
    private ArrayAdapter<Evento> adapterEventos;
    public static String textoPesquisaEvento;
    public static String textoPesquisaCidade;

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

    public void onClickPesquisarEvento(View v) {
        EditText editTextPesquisaEvento = findViewById(R.id.etBuscaEvento);

        if (editTextPesquisaEvento.getText().length() != 0) {
            textoPesquisaEvento = editTextPesquisaEvento.getText().toString();
            EventoDAO eventoDao = new EventoDAO(getBaseContext());
            adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.buscarEvento());
            lvEventos.setAdapter(adapterEventos);
        } else {
            Toast.makeText(MainActivity.this, "Insira dados no campo ao lado para pesquisar.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickPesquisarCidade(View v) {
        EditText editTextPesquisaCidade = findViewById(R.id.etPesquisaMunicipio);

        if (editTextPesquisaCidade.getText().length() != 0) {
            textoPesquisaCidade = editTextPesquisaCidade.getText().toString();
            EventoDAO eventoDao = new EventoDAO(getBaseContext());
            adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.buscarCidade());
            lvEventos.setAdapter(adapterEventos);
        } else {
            Toast.makeText(MainActivity.this, "Insira dados no campo ao lado para pesquisar.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickLimparPesquisa(View v) {
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.listarEventos());
        lvEventos.setAdapter(adapterEventos);
        EditText editTextPesquisaEvento = findViewById(R.id.etBuscaEvento);
        EditText editTextPesquisaCidade = findViewById(R.id.etPesquisaMunicipio);
        editTextPesquisaEvento.setText("");
        editTextPesquisaCidade.setText("");
    }

    public void onClickOrdenarCrescente(View v) {
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.ordenarEventoCrescente());
        lvEventos.setAdapter(adapterEventos);
    }

    public void onClickOrdenarDecrescente(View v) {
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDao.ordenarEventoDecrescente());
        lvEventos.setAdapter(adapterEventos);
    }

}