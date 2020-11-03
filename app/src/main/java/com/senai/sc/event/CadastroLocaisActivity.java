package com.senai.sc.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.senai.sc.event.classeEvento.Locais;
import com.senai.sc.event.database.LocaisDAO;

public class CadastroLocaisActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_locais);
        setTitle("Cadastro de Locais");
        carregarLocais();
    }

    public void carregarLocais() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {
            Locais locais = (Locais) intent.getExtras().get("localEdicao");
            EditText editTextNome = findViewById(R.id.etNomeLocal);
            EditText editTextBairro = findViewById(R.id.etBairroLocal);
            EditText editTextCidade = findViewById(R.id.etCidadeLocal);
            EditText editTextCapacidade = findViewById(R.id.etCapacidadeLocal);
            editTextNome.setText(locais.getNome());
            editTextBairro.setText(locais.getBairro());
            editTextCidade.setText(locais.getCidade());
            editTextCapacidade.setText(String.valueOf(locais.getCapacidadePublico()));
            id = locais.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.etNomeLocal);
        EditText editTextBairro = findViewById(R.id.etBairroLocal);
        EditText editTextCidade = findViewById(R.id.etCidadeLocal);
        EditText editTextCapacidade = findViewById(R.id.etCapacidadeLocal);

        String nome = editTextNome.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidade = Integer.parseInt(editTextCapacidade.getText().toString());

        Locais locais = new Locais(id, nome, bairro, cidade, capacidade);
        LocaisDAO locaisDAO = new LocaisDAO(getBaseContext());

        boolean salvou = locaisDAO.salvar(locais);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroLocaisActivity.this, "Erro ao salvar.", Toast.LENGTH_LONG).show();
        }
    }


    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.etNomeLocal);
        EditText editTextBairro = findViewById(R.id.etBairroLocal);
        EditText editTextCidade = findViewById(R.id.etCidadeLocal);
        EditText editTextCapacidade = findViewById(R.id.etCapacidadeLocal);

        String nome = editTextNome.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidade = Integer.parseInt(editTextCapacidade.getText().toString());

        Locais locais = new Locais(id, nome, bairro, cidade, capacidade);

        LocaisDAO locaisDAO = new LocaisDAO(getBaseContext());
        boolean excluiu = locaisDAO.excluir(locais);
        if (excluiu) {
            finish();
        } else {
            Toast.makeText(CadastroLocaisActivity.this,"Impossível excluir. Não há nenhum local selecionado.", Toast.LENGTH_LONG).show();
        }
    }
}