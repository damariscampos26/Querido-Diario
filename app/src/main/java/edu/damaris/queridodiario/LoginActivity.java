package edu.damaris.queridodiario;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView criarCadastro;
    private EditText usuario, senha;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);
        login = findViewById(R.id.buttonLogin);
        dataBase = new DataBase(this);
        criarCadastro = findViewById(R.id.TextViewNovoDiario);

        criarCadastro.setPaintFlags(criarCadastro.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); //deixa o texto sublinhado.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = usuario.getText().toString().trim();
                String senha1 = senha.getText().toString().trim();

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha1)) {
                    toast(R.drawable.error__4_, R.string.campos_vazios);
                } else {
                    boolean obterDados = dataBase.obterDados(nome, senha1);
                    if (obterDados) {
                        Intent intent = new Intent(LoginActivity.this, PageActivity.class);
                        startActivity(intent);
                    } else {
                        toast(R.drawable.error__4_, R.string.erro);
                    }
                }
            }
        });
        criarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaTelaHome();
            }
        });
    }
    private void irParaTelaHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void toast(int imageResource, int textResource){
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.containerToast));

        ImageView imageView = layout.findViewById(R.id.toast);
        imageView.setImageResource(imageResource);

        TextView textView = layout.findViewById(R.id.textToast);
        textView.setText(textResource);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
