package com.example.libriaprendizado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libriaprendizado.database.SQLHelper;

public class CadastrarEndereco extends AppCompatActivity {

    private EditText txtCep;
    private EditText txtNumero;
    private EditText txtComplemento;
    private Button btnCadastrarEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        this.txtCep = findViewById(R.id.txtCep);
        this.txtNumero = findViewById(R.id.txtNumero);
        this.txtComplemento = findViewById(R.id.txtComplemento);
        this.btnCadastrarEndereco = findViewById(R.id.btnCadastrarEndereco);

        btnCadastrarEndereco.setOnClickListener(view->{

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_cadastro_endereco))
                    .setMessage(getString(R.string.mensagem_cadastro_endreeco))
                    .setPositiveButton(R.string.cadUsuario, (dialog1, wich)->{

                        String sCep = txtCep.getText().toString();
                        String sNumero = txtNumero.getText().toString();
                        String sComplemento = txtComplemento.getText().toString();

                        boolean cadastroLivro = SQLHelper.getInstance(this)
                                .addEndereco(2, sCep, sNumero, sComplemento);

                        if(cadastroLivro){
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

        });//FIM DO SETONCLICKLISTENER

    }
}