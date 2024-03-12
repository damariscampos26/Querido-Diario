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
    Button buttonCriarDiario;
    EditText usuario, senha, repetirSenha;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonCriarDiario = findViewById(R.id.buttonHome);
        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);
        repetirSenha = findViewById(R.id.editTextRepetirSenha);
        dataBase = new DataBase(this);

        buttonCriarDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = usuario.getText().toString().trim();
                String senha1 = senha.getText().toString().trim();
                String senha2 = repetirSenha.getText().toString().trim();

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha1) || TextUtils.isEmpty(senha2)) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View layout = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.containerToast));

                    ImageView imageView = layout.findViewById(R.id.toastError);
                    imageView.setImageResource(R.drawable.error__4_);

                    TextView textView = layout.findViewById(R.id.textToast);
                    textView.setText(R.string.campos_vazios);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                } else if (!senha1.equals(senha2)){
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View layout = layoutInflater.inflate(R.layout.custom_toast2, findViewById(R.id.containerToast2));

                    ImageView imageView = layout.findViewById(R.id.toastError);
                    imageView.setImageResource(R.drawable.error__4_);

                    TextView textView = layout.findViewById(R.id.textToast2);
                    textView.setText(R.string.senhas_diferentes);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }else  {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("nome", nome);
                    contentValues.put("senha", senha1);

                    SQLiteDatabase database = dataBase.getWritableDatabase();
                    database.insert("usuario", null, contentValues);
                    database.close();

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}