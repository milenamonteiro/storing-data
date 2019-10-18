package com.example.sharedpreferencesjoao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private TextView nome;
    private EditText etet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.tvnome);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();
    }

    public void metodo(View view){
        etet = findViewById(R.id.etnome);
        String name = etet.getText().toString();
        mEditor.putString(getString(R.string.name), name);
        mEditor.commit();
        nome.setText(name);
    }

    private void checkSharedPreferences(){

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        nome = findViewById(R.id.tvnome);

        String name = mPreferences.getString(getString(R.string.name), "Nome");
        if(name != null){
            nome.setText(name);
        }
    }
}
