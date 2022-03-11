package com.example.libriaprendizado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

                        int cod_usuario = 0 ;
                        if(getIntent().hasExtra("COD_USUARIO")){
                            Bundle extras = getIntent().getExtras();
                            cod_usuario = extras.getInt("COD_USUARIO");
                        }

                        boolean cadastroLivro = SQLHelper.getInstance(this)
                                .addEndereco(cod_usuario, sCep, sNumero, sComplemento);

                        if(cadastroLivro){
                            Toast.makeText(this,
                                    R.string.cadastro_ok,
                                    Toast.LENGTH_LONG).show();

                            Intent intent  = new Intent(CadastrarEndereco.this, Feed.class);
                            startActivity(intent);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }//FIM DO MÉTODO ONCREATEOPTIONSMENU

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("IDITEM", String.valueOf(item.getItemId()));

        switch (item.getItemId()){

            case R.id.menu_cadastrar_endereco:
                startActivity(
                        new Intent(
                                this,
                                CadastrarEndereco.class
                        )
                );
                break;

            case R.id.menu_feed_livro:
                startActivity(
                        new Intent(
                                this,
                                Feed.class
                        )
                );
                break;

            case R.id.menu_sair:
                startActivity(
                        new Intent(
                                this,
                                MainActivity.class
                        )
                );
                break;

        }

        return super.onOptionsItemSelected(item);

    }//FIM DO MENU

}