package com.example.appejercicio2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //activity_main2 --> xml
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 3) actividad que recibe el Intent, se indica el resultado de
        // la operación (RESULT_OK), se manda la data necesaria (putExtra)
        // y se finaliza con finish()
        Button button = findViewById(R.id.buttonGuardar);
        button.setOnClickListener(view -> {
            EditText editText = findViewById(R.id.editText1);
            String nombreUsuario = editText.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("nombreUsuario",nombreUsuario);
            setResult(RESULT_OK,intent);
            finish();
        });


    }
}