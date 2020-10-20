package com.senai.sc.event;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.senai.sc.event.classeEvento.Evento;

import java.time.LocalDate;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EXCLUIR_EVENTO = 12;

    private boolean edicao = false;
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

            edicao = true;
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

        int id = 0;
        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextData.getText().toString();
        String localEvento = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nomeEvento,dataEvento,localEvento);
        finish();
    }
}