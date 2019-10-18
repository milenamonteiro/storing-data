package com.example.escreverobjeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gravarTexto(View view){
        String FILENAME = "arquivotexto";
        EditText editText = findViewById(R.id.editText2);
        String str = editText.getText().toString();

        try {
            File file = getFileStreamPath(FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ObjTeste objeto = new ObjTeste(1, str);
            oos.writeObject(objeto);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerTexto(View view){
        Intent sendIntent = new Intent(MainActivity.this, LeituraActivity.class);
        startActivity(sendIntent);
    }
}
