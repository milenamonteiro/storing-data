package com.example.escreverobjeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeituraActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        et = findViewById(R.id.editText);
        lerTexto();
    }

    public void lerTexto(){
        String FILENAME = "arquivotexto";
        File file = getFileStreamPath(FILENAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ObjTeste retorno = (ObjTeste) ois.readObject();
            ois.close();
            fis.close();
            et.setText(retorno.descricao);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void voltar(View view){
        Intent sendIntent = new Intent(LeituraActivity.this, MainActivity.class);
        startActivity(sendIntent);
    }
}
