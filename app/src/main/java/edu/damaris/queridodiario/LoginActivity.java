package edu.damaris.queridodiario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    Button login;
    private TextView criarCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.buttonLogin);
        criarCadastro = findViewById(R.id.TextViewNovoDiario);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaTelaPage();{
                }
            }
        });
        criarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaTelaHome();{
                }
            }
        });
    }
    public void irParaTelaPage(){
        Intent intent = new Intent(this, PageActivity.class);
        startActivity(intent);
    }
    private void irParaTelaHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}