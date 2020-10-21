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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int REQUEST_CODE_EDITAR_EVENTO = 2;
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EXCLUIR_EVENTO = 12;

    private ListView lvEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Event! | Sua agenda de eventos");

        lvEventos = findViewById(R.id.lvEventos);
        ArrayList<Evento> listaEventos = this.criarListaEvento();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, listaEventos);
        lvEventos.setAdapter(adapterEventos);

        definirOnClickListenerView();
    }

    private void definirOnClickListenerView() {
        lvEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivityForResult(intent, REQUEST_CODE_EDITAR_EVENTO);
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO) {
            Toast.makeText(MainActivity.this, "Evento cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            Evento eventoNovo = (Evento) data.getExtras().getSerializable("novoEvento");
            eventoNovo.setId(++id);
            this.adapterEventos.add(eventoNovo);

        } else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EDITADO) {
            Toast.makeText(MainActivity.this, "Evento editado com sucesso!", Toast.LENGTH_LONG).show();
            Evento eventoEditado = (Evento) data.getExtras().getSerializable("eventoEditado");
            for (int i = 0; i < adapterEventos.getCount(); i++) {
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId() == eventoEditado.getId()) {
                    adapterEventos.remove(evento);
                    adapterEventos.insert(eventoEditado, i);
                    break;
                }
            }
        } else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EXCLUIR_EVENTO) {
            Toast.makeText(MainActivity.this, "Evento excluído com sucesso!", Toast.LENGTH_LONG).show();
            Evento eventoExcluido = (Evento) data.getExtras().getSerializable("eventoExcluido");
            for (int i = 0; i < adapterEventos.getCount(); i++) {
                Evento produto = adapterEventos.getItem(i);
                if (produto.getId() == eventoExcluido.getId()) {
                    adapterEventos.remove(produto);
                    break;
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}