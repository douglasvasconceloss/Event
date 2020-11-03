package com.senai.sc.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.senai.sc.event.classeEvento.Locais;
import com.senai.sc.event.database.LocaisDAO;

public class ListarLocaisActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Locais> adapterLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locais);
        listViewLocais = findViewById(R.id.lvLocais);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocaisDAO locaisDao = new LocaisDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Locais>(ListarLocaisActivity.this, android.R.layout.simple_list_item_1, locaisDao.listar());
        listViewLocais.setAdapter(adapterLocais);
    }

    private void definirOnClickListenerListView() {
        listViewLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Locais localClicado = adapterLocais.getItem(position);
                Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocaisActivity.class);
                intent.putExtra("localEdicao", localClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocaisActivity.class);
        startActivity(intent);
    }

    public void onClickVoltar(View v) {
        Intent intentVoltar = new Intent(ListarLocaisActivity.this, MainActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}