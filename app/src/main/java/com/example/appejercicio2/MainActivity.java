package com.example.appejercicio2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import org.jetbrains.annotations.NotNull;

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

        //Registro de Context Menu
        registerForContextMenu((TextView) findViewById(R.id.menucontext));


    }
    //Para vincular el menú con la actividad se debe sobreescribir el método
    //onCreateOptionsMenu() de la actividad. Aquí se debe “inflar” el menú definido
    //en la carpeta “menu”.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4main,menu);
        return true;
    }

   //Existen dos formas de gestionar los eventos de los iconos del App Bar:

   //1. onClick para cada elemento del menú.
    public void accionMenuIcono(MenuItem item) {
        Log.d("msgAppBar", "App bar icon clic");
    }

    //2) Sobreescribir el método onOptionsItemSelected para todos los
    //elementos del App Bar y validar uno por uno.

    /*@Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.icon1:
                Log.d("msgAppBar", "App bar icon clic");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    //ContextoMenu:
    //Sobreescribir el método en la actividad: onCreateContextMenu() y registrar
    //el menú creado en el paso 1.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.menu_context,menu);
    }

    //Para gestionar los eventos del menú, sobreescribir el método de la actividad:
    @Override
    public boolean onContextItemSelected(@NotNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.context_Edit:
                Log.d("MsgconMenu","Edit");
                return true;
            case R.id.context_Delete:
                Log.d("MsgconMenu","Delete");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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