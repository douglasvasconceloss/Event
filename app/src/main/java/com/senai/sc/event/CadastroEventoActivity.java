package com.senai.sc.event;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.classeEvento.Locais;
import com.senai.sc.event.database.EventoDAO;
import com.senai.sc.event.database.LocaisDAO;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Locais> locaisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro e Edição de Eventos");
        spinnerLocais = findViewById(R.id.spinnerLocais);
        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais() {
        LocaisDAO locaisDao = new LocaisDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Locais>(CadastroEventoActivity.this, android.R.layout.simple_spinner_item, locaisDao.listar());
        spinnerLocais.setAdapter(locaisAdapter);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.etNome);
            EditText editTextData = findViewById(R.id.etData);
            editTextNome.setText(evento.getNomeEvento());
            editTextData.setText(evento.getDataEvento());
            int posicaoLocais = obterPosicaoLocal(evento.getLocais());
            spinnerLocais.setSelection(posicaoLocais);
            id = evento.getId();
        }
    }

    private int obterPosicaoLocal(Locais locais) {
        for (int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if (locaisAdapter.getItem(posicao).getId() == locais.getId()) {
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.etNome);
        EditText editTextData = findViewById(R.id.etData);
        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextData.getText().toString();
        int posicaoLocais = spinnerLocais.getSelectedItemPosition();
        Locais locais = (Locais) locaisAdapter.getItem(posicaoLocais);  
        Evento evento = new Evento(id, nomeEvento, dataEvento, locais);
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        if (nomeEvento.isEmpty() || dataEvento.isEmpty()) {
            Toast.makeText(CadastroEventoActivity.this, "Os campos de nome, data e local são obrigatórios", Toast.LENGTH_LONG).show();
        } else {
            boolean salvou = eventoDao.salvarEvento(evento);
            if (salvou) {
                finish();
            } else {
                Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.etNome);
        EditText editTextData = findViewById(R.id.etData);
        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextData.getText().toString();
        Locais locais = (Locais) spinnerLocais.getSelectedItem();
        Evento evento = new Evento(id, nomeEvento,dataEvento,locais);
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        boolean excluiu = eventoDao.excluirEvento(evento);
        if (excluiu) {
            finish();
        } else {
            Toast.makeText(CadastroEventoActivity.this,"Impossível excluir. Não há nenhum evento selecionado.", Toast.LENGTH_LONG).show();
        }
    }
}