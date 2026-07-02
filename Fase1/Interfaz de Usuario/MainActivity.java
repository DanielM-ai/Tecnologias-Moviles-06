package com.ejemplo.practica01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Tasas de cambio de referencia respecto al Dólar (1 USD)
    private final double[] tasasDeCambio = {
            1.0,      // Dólar (USD)
            3.75,     // Nuevo Sol (PEN)
            0.92,     // Euro (EUR)
            0.79,     // Libra (GBP)
            83.3,     // Rupia (INR)
            5.05,     // Real (BRL)
            16.7,     // Peso Mexicano (MXN)
            7.24,     // Yuan (CNY)
            154.0     // Yen (JPY)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        final EditText etCantidad = findViewById(R.id.etCantidad);
        final Spinner spinnerOrigen = findViewById(R.id.spinnerOrigen);
        final Spinner spinnerDestino = findViewById(R.id.spinnerDestino);
        Button btnConvertir = findViewById(R.id.btnConvertir);
        final TextView tvResultado = findViewById(R.id.tvResultado);

        // Evento click implementado con una lambda
        btnConvertir.setOnClickListener(v -> {
            String cantidadStr = etCantidad.getText().toString().trim();

            // Validaciones iniciales
            if (cantidadStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show();
                return;
            }

            double cantidad;
            try {
                cantidad = Double.parseDouble(cantidadStr);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Ingresa un número válido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cantidad <= 0) {
                Toast.makeText(MainActivity.this, "Ingresa un número mayor a 0", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtener el índice seleccionado en las listas (coincide con nuestro arreglo de tasas)
            int indexOrigen = spinnerOrigen.getSelectedItemPosition();
            int indexDestino = spinnerDestino.getSelectedItemPosition();

            // Lógica: Convertir primero a dólares (moneda base común) y luego a la moneda destino
            double tasaOrigen = tasasDeCambio[indexOrigen];
            double tasaDestino = tasasDeCambio[indexDestino];

            double cantidadEnDolares = cantidad / tasaOrigen;
            double resultadoFinal = cantidadEnDolares * tasaDestino;

            // Mostrar resultado en el TextView
            tvResultado.setText(String.format(Locale.getDefault(), "Resultado: %.2f", resultadoFinal));
        });
    }
}
