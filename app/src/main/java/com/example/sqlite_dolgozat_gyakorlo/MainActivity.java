package com.example.sqlite_dolgozat_gyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;

import kotlin.jvm.internal.markers.KMutableMap;

public class MainActivity extends AppCompatActivity {

    private Button buttonFelvetel;
    private Button buttonKereses;
    private Button buttonListazas;
    private ListView listViewLista;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonFelvetel.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AdatFelvetelActivity.class);
            startActivity(intent);
            finish();
        });

        buttonKereses.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AdatKeresesActivity.class);
            startActivity(intent);
            finish();
        });

        buttonListazas.setOnClickListener(view -> {

            adatLekerdezes();
        });

        listViewLista.setOnItemLongClickListener((adapterView, view, i, l) -> {
            //custom Entry or Item class :c
            return true;
        });
    }

    private void adatLekerdezes() {
        Cursor adatok = dbHelper.adatLekerdezes();

        if (adatok == null) {
            Toast.makeText(this, "Hiba történt a lekérdezés során.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount() == 0) {
            Toast.makeText(this, "Még nincs felvéve adat.", Toast.LENGTH_SHORT).show();
            return;
        }

        lista.clear();
        while (adatok.moveToNext()) {
            String adatokString = "ID: " + adatok.getInt(0) + "\n" +
                    "Főző: " + adatok.getString(1) + "\n" +
                    "Gyümölcs: " + adatok.getString(2) + "\n" +
                    "Alkohol: " + adatok.getInt(3) + "\n\n";
            lista.add(adatokString);
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Sikeres lekérdezés.", Toast.LENGTH_SHORT).show();
    }

    private void init(){
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonListazas = findViewById(R.id.buttonListazas);
        listViewLista = findViewById(R.id.listViewLista);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listViewLista.setAdapter(adapter);
        dbHelper = new DBHelper(MainActivity.this);
    }
}