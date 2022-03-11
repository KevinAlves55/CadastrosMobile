package com.example.libriaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libriaprendizado.database.SQLHelper;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtSenhaLogin;
    private Button btnLogin;
    private Button btnTelaCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtLogin = findViewById(R.id.txtLogin);
        this.txtSenhaLogin = findViewById(R.id.txtSenhaLogin);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnTelaCadastrar = findViewById(R.id.btnCadastrarUsuarioTela);

        btnTelaCadastrar.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    CadastroUsuario.class
            );

            startActivity(intent);

        });

        btnLogin.setOnClickListener(view->{

            String login = txtLogin.getText().toString();
            String senha = txtSenhaLogin.getText().toString();

            int cod_ususario = SQLHelper.getInstance(this)
                    .login(login, senha);

            if(cod_ususario > 0){

                //AÇÃO LOGADO
                startActivity(
                        new Intent(MainActivity.this, Feed.class)
                );

            }else{

                //AÇÃO ERRO LOGIN
                Toast.makeText(MainActivity.this,
                        "DADOS DE LOGIN INCORRRETOS",
                        Toast.LENGTH_LONG).show();

            }

        });

    }
}