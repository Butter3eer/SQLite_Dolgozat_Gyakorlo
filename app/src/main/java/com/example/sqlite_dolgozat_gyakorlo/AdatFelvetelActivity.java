package com.example.sqlite_dolgozat_gyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {

    private EditText editTextFozoRogzit;
    private EditText editTextGyumolcsRogzit;
    private EditText editTextAlkoholRogzit;
    private Button buttonRogzit;
    private Button buttonVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();

        buttonRogzit.setOnClickListener(view -> {
            if(editTextFozoRogzit.getText().toString().isEmpty()){
                Toast.makeText(AdatFelvetelActivity.this, "A vezetéknév nem lehet üres!", Toast.LENGTH_SHORT).show();
            }
            else if(editTextGyumolcsRogzit.getText().toString().isEmpty()){
                Toast.makeText(this, "A keresztnév nem lehet üres", Toast.LENGTH_SHORT).show();
            }
            else if(editTextAlkoholRogzit.getText().toString().isEmpty()){
                Toast.makeText(this, "A jegy nem lehet üres", Toast.LENGTH_SHORT).show();
            }
            else if(Integer.parseInt(editTextAlkoholRogzit.getText().toString()) <=0 || Integer.parseInt(editTextAlkoholRogzit.getText().toString())>100){
                Toast.makeText(this, "A jegy értéke érvénytelen", Toast.LENGTH_SHORT).show();
            }
            else {
                if (dbHelper.rogzites(editTextFozoRogzit.getText().toString(), editTextGyumolcsRogzit.getText().toString(), Integer.parseInt(editTextAlkoholRogzit.getText().toString()))) {
                    Toast.makeText(this, "Sikeres adat felvétel!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Sikertelen.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonVissza.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init(){
        editTextFozoRogzit = findViewById(R.id.editTextFozoRogzit);
        editTextGyumolcsRogzit = findViewById(R.id.editTextGyumolcsRogzit);
        editTextAlkoholRogzit = findViewById(R.id.editTextAlkoholRogzit);
        buttonRogzit = findViewById(R.id.buttonRogzit);
        buttonVissza = findViewById(R.id.buttonVissza);
        dbHelper = new DBHelper(AdatFelvetelActivity.this);
    }
}