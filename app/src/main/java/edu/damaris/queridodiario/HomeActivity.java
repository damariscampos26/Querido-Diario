package edu.damaris.queridodiario;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonCriarDiario = findViewById(R.id.buttonHome);
        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);
        repetirSenha = findViewById(R.id.editTextRepetirSenha);

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
                }else {

                }
            }
        });
    }
}