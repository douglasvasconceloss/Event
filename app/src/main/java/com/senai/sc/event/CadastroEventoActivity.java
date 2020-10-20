package com.senai.sc.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.senai.sc.event.classeEvento.Evento;

import java.time.LocalDate;

public class CadastroEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro e Edição de Eventos");
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