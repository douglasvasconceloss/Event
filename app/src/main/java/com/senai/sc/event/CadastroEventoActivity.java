package com.senai.sc.event;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.database.EventoDAO;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro e Edição de Eventos");

        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            EditText editTextNome = findViewById(R.id.etNome);
            EditText editTextData = findViewById(R.id.etData);
            EditText editTextLocal = findViewById(R.id.etLocal);

            editTextNome.setText(evento.getNomeEvento());
            editTextData.setText(evento.getDataEvento());
            editTextLocal.setText(evento.getLocalEvento());
            id = evento.getId();
        }
    }


    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.etNome);
        EditText editTextData = findViewById(R.id.etData);
        EditText editTextLocal = findViewById(R.id.etLocal);

        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextData.getText().toString();
        String localEvento = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nomeEvento, dataEvento, localEvento);
        EventoDAO eventoDao = new EventoDAO(getBaseContext());

        if (nomeEvento.isEmpty() || dataEvento.isEmpty() || localEvento.isEmpty()) {
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
        EditText editTextLocal = findViewById(R.id.etLocal);

        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextData.getText().toString();
        String localEvento = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nomeEvento,dataEvento,localEvento);

        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        boolean excluiu = eventoDao.excluirEvento(evento);
        if (excluiu) {
            finish();
        } else {
            Toast.makeText(CadastroEventoActivity.this,"Impossível excluir. Não há nenhum evento selecionado.", Toast.LENGTH_LONG).show();
        }
    }
}