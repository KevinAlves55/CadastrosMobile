package com.example.libriaprendizado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
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