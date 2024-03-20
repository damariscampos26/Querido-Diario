package edu.damaris.queridodiario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class HomeActivity extends AppCompatActivity {
    private Button buttonCriarDiario;
    private EditText usuario, senha, repetirSenha;
    private TextView login;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonCriarDiario = findViewById(R.id.buttonHome);
        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);
        repetirSenha = findViewById(R.id.editTextRepetirSenha);
        login = findViewById(R.id.TextViewLogin);
        dataBase = new DataBase(this);

        buttonCriarDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = usuario.getText().toString().trim();
                String senha1 = senha.getText().toString().trim();
                String senha2 = repetirSenha.getText().toString().trim();

                boolean usuarioExiste = dataBase.verificarUsuario(nome);
                boolean inseridoComSucesso = dataBase.inserirDadosUsuario(nome, senha1);

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha1) || TextUtils.isEmpty(senha2)) {
                    toast(R.drawable.error__4_, R.string.campos_vazios);
                } else if (!senha1.equals(senha2)) {
                    toast(R.drawable.error__4_, R.string.senhas_diferentes);
                }else if (usuarioExiste){
                    toast(R.drawable.error__4_, R.string.usuario_existente);
                }else if (inseridoComSucesso){
                    toast(R.drawable.check, R.string.sucesso);

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    toast(R.drawable.error__4_, R.string.erro_ao_inserir);
                }
            };});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaTelaLogin();{
                }
            }
        });
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
    private void irParaTelaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}