package com.example.appejercicio2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("MainActivity");
        setContentView(R.layout.activity_main); //Layout XML

        //1) Lanzar Activity
        // Primero, se define el launcher, el cual contiene 2 parámetros:
        // El contrato (lo que hará)
        // El callback (lo que ejecutará al regreso).

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                //4) la actividad que lanzó originalmente el Intent, la
                //respuesta será capturada en el callback.
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        String nombreUsuario = data.getStringExtra("nombreUsuario");

                        TextView textView = findViewById(R.id.textView9);
                        textView.setText("Texto de la otra aplicación: "+ nombreUsuario);
                    }
                }
        );


        //2) Luego, se utiliza el launcher con la actividad deseada
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            launcher.launch(intent);
        });


    }

    //Por ejemplo. guardar el estado de un textView si es que la pantalla gira
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        TextView textView = findViewById(R.id.textView9);
        String texto = textView.getText().toString();

        //En este ejemplo, se valida si el textView tiene el valor original o ha cambiado su
        //valor. De ser así, se guarda su estado en outState.

        /*if(!texto.equals(getString(R.string.textoView10))){
            outState.putString("nombreUsuario",texto);
        }*/
    }
}