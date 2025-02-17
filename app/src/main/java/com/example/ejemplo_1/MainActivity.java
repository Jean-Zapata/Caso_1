package com.example.ejemplo_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnCalculadora;
    private Button btnSignoZodiacal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        btnCalculadora = findViewById(R.id.btnCalculadora);
        btnSignoZodiacal = findViewById(R.id.btnSignoZodiacal);

        // Configurar listeners para los botones
        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Operaciones_Basicas.class);
                startActivity(intent);
            }
        });

        btnSignoZodiacal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Signo_Zodiacal.class);
                startActivity(intent);
            }
        });
    }
}