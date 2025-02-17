package com.example.ejemplo_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Signo_Zodiacal extends AppCompatActivity {
    private Spinner daySpinner, monthSpinner;
    private Button calculateButton;
    private ImageView zodiacImageView;
    private TextView resultTextView, descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signo_zodiacal);

        initializeViews();
        setupSpinners();
        setupCalculateButton();
    }

    private void initializeViews() {
        daySpinner = findViewById(R.id.daySpinner);
        monthSpinner = findViewById(R.id.monthSpinner);
        calculateButton = findViewById(R.id.calculateButton);
        zodiacImageView = findViewById(R.id.zodiacImageView);
        resultTextView = findViewById(R.id.resultTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        // Ocultar el ImageView ya que no tenemos imágenes por ahora
        zodiacImageView.setVisibility(View.GONE);
    }

    private void setupSpinners() {
        // Configurar spinner de días
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // Configurar spinner de meses
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
    }

    private void setupCalculateButton() {
        calculateButton.setOnClickListener(v -> calculateAndDisplayZodiacSign());
    }

    private void calculateAndDisplayZodiacSign() {
        int day = (int) daySpinner.getSelectedItem();
        int month = monthSpinner.getSelectedItemPosition() + 1;

        String sign = getZodiacSign(day, month);
        String description = getZodiacDescription(sign);

        resultTextView.setText("Tu signo es: " + sign);
        descriptionTextView.setText(description);
    }

    private String getZodiacSign(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return "Aries";
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            return "Tauro";
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) {
            return "Géminis";
        } else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) {
            return "Cáncer";
        } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
            return "Leo";
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            return "Virgo";
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) {
            return "Libra";
        } else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) {
            return "Escorpio";
        } else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) {
            return "Sagitario";
        } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
            return "Capricornio";
        } else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            return "Acuario";
        } else {
            return "Piscis";
        }
    }

    private String getZodiacDescription(String sign) {
        switch (sign) {
            case "Aries":
                return "Aries es el primer signo del zodíaco. Su elemento es el fuego y está regido por Marte. Las personas de Aries son valientes, decididas y confiadas.";
            case "Tauro":
                return "Tauro es el segundo signo del zodíaco. Su elemento es la tierra y está regido por Venus. Los Tauro son pacientes, prácticos y determinados.";
            case "Géminis":
                return "Géminis es el tercer signo del zodíaco. Su elemento es el aire y está regido por Mercurio. Los Géminis son versátiles, intelectuales y comunicativos.";
            case "Cáncer":
                return "Cáncer es el cuarto signo del zodíaco. Su elemento es el agua y está regido por la Luna. Los Cancer son emocionales, protectores e intuitivos.";
            case "Leo":
                return "Leo es el quinto signo del zodíaco. Su elemento es el fuego y está regido por el Sol. Los Leo son creativos, apasionados y generosos.";
            case "Virgo":
                return "Virgo es el sexto signo del zodíaco. Su elemento es la tierra y está regido por Mercurio. Los Virgo son metódicos, prácticos y perfeccionistas.";
            case "Libra":
                return "Libra es el séptimo signo del zodíaco. Su elemento es el aire y está regido por Venus. Los Libra son diplomáticos, justos y sociables.";
            case "Escorpio":
                return "Escorpio es el octavo signo del zodíaco. Su elemento es el agua y está regido por Plutón. Los Escorpio son intensos, poderosos y pasionales.";
            case "Sagitario":
                return "Sagitario es el noveno signo del zodíaco. Su elemento es el fuego y está regido por Júpiter. Los Sagitario son optimistas, aventureros y filosóficos.";
            case "Capricornio":
                return "Capricornio es el décimo signo del zodíaco. Su elemento es la tierra y está regido por Saturno. Los Capricornio son ambiciosos, responsables y disciplinados.";
            case "Acuario":
                return "Acuario es el undécimo signo del zodíaco. Su elemento es el aire y está regido por Urano. Los Acuario son originales, independientes y humanitarios.";
            default: // Piscis
                return "Piscis es el duodécimo signo del zodíaco. Su elemento es el agua y está regido por Neptuno. Los Piscis son intuitivos, artísticos y compasivos.";
        }
    }
}