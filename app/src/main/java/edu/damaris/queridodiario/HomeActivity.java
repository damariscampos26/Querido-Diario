package edu.damaris.queridodiario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    Button buttonCriarDiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonCriarDiario = findViewById(R.id.buttonHome);
//        buttonCriarDiario.setBackgroundColor(getResources().getColor(R.color.contrast));

        buttonCriarDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irParaTelaLogin();}
        });
    }
    public void irParaTelaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}