package com.example.sqlite_dolgozat_gyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {

    private EditText editTextFozoKereses;
    private EditText editTextGyumolcsKereses;
    private Button buttonKereses;
    private Button buttonVissza;
    private TextView textViewAlkoholTartalom;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);

        init();

        buttonKereses.setOnClickListener(view -> {
            adatLekerdezes();
        });

        buttonVissza.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void adatLekerdezes() {
        textViewAlkoholTartalom.setText("");
        Cursor adatok = dbHelper.adatLekerdezes();
        if (adatok == null) {
            Toast.makeText(this, "Hiba a keresés során.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount() == 0) {
            Toast.makeText(this, "Még nincs felvéve adat.", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder builder = new StringBuilder();

        while (adatok.moveToNext()) {
            builder.append("Alkohol: ").append(adatok.getInt(3)).append("\n");
        }
        textViewAlkoholTartalom.setText(builder);
        Toast.makeText(this, "Sikeres lekérdezés.", Toast.LENGTH_SHORT).show();
    }

    public void init() {
        editTextFozoKereses = findViewById(R.id.editTextFozoKereses);
        editTextGyumolcsKereses = findViewById(R.id.editTextGyumolcsKereses);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonVissza = findViewById(R.id.buttonVissza);
        textViewAlkoholTartalom = findViewById(R.id.textViewAlkoholTartalom);
        dbHelper = new DBHelper(this);
    }
}