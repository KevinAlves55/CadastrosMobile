package com.example.libriaprendizado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libriaprendizado.database.SQLHelper;

public class CadastroUsuario extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.txtNome = findViewById(R.id.txtNome);
        this.txtSobrenome = findViewById(R.id.txtSobrenome);
        this.txtLogin = findViewById(R.id.txtLogin);
        this.txtSenha = findViewById(R.id.txtSenha);
        this.btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        btnCadastrar.setOnClickListener(view -> {

            if(!validate()){

                Toast.makeText(this,"TODOS OS CAMPOS DEVEM SER PREENCHIDOS!",Toast.LENGTH_LONG).show();
                return;
            }

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_cadastro_usuario))
                    .setMessage(getString(R.string.mensagem_cadastro_usuario))
                    .setPositiveButton(R.string.cadUsuario, (dialog1, wich)->{

                        /** GRAVAÇÃO DOS DADOS NO SQLITE **/
                        String sNome = txtNome.getText().toString();
                        String sSobreNome = txtSobrenome.getText().toString();
                        String sLogin = txtLogin.getText().toString();
                        String sSenha = txtSenha.getText().toString();

                        boolean cadastroUsuario = SQLHelper.getInstance(this)
                                .addUser(sNome, sSobreNome, sLogin, sSenha);

                        if(cadastroUsuario){
                            Toast.makeText(this,
                                    R.string.cadastro_ok,
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this,
                                    R.string.cadastro_erro,
                                    Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, wich)->{})
                    .create();
            dialog.show();

        });

    }

    public boolean validate() {

        return (
                !txtNome.getText().toString().isEmpty() &&
                        !txtSobrenome.getText().toString().isEmpty() &&
                        !txtLogin.getText().toString().isEmpty() &&
                        !txtSenha.getText().toString().isEmpty()
        );

    }
}