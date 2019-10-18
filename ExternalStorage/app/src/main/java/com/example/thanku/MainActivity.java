package com.example.thanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton ex, in;
    private EditText nome, idade, telefone;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean mExternalStorageAvailable = false;
    private boolean mExternalStorageWriteable = false;
    private int valorselecionado;
    private String FILENAME = "usuario.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            checarPreferencias();
            checarObjeto();
        }catch (Exception ex){
            nome.setText(ex.toString());
        }
    }

    private void checarPreferencias(){
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        in = findViewById(R.id.positivo);
        ex = findViewById(R.id.negativo);
        valorselecionado = Integer.parseInt(mPreferences.getString(getString(R.string.name), "1"));
        if (valorselecionado == 1)
            in.setChecked(true);
        else
            ex.setChecked(true);
    }

    private void verificaMidia(){
        String state = Environment.getExternalStorageState();
        switch(state){
            case Environment.MEDIA_MOUNTED:
                mExternalStorageAvailable = mExternalStorageWriteable = true;
                break;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                mExternalStorageAvailable = true;
                mExternalStorageWriteable = false;
                break;
            default:
                mExternalStorageAvailable = mExternalStorageWriteable = false;
                break;
        }
    }

    private void salvarInterno(){
        nome = findViewById(R.id.Nome);
        idade = findViewById(R.id.Idade);
        telefone = findViewById(R.id.Telefone);

        try {
            File file = getFileStreamPath(FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Usuario objeto = new Usuario(nome.getText().toString(), Integer.parseInt(idade.getText().toString()), telefone.getText().toString());
            oos.writeObject(objeto);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarExterno(){
        verificaMidia();
        nome = findViewById(R.id.Nome);
        idade = findViewById(R.id.Idade);
        telefone = findViewById(R.id.Telefone);

        if(mExternalStorageAvailable && mExternalStorageWriteable) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Usuario objeto = new Usuario(nome.getText().toString(), Integer.parseInt(idade.getText().toString()), telefone.getText().toString());
                oos.writeObject(objeto);
                oos.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void salvar(View view){
        mEditor = mPreferences.edit();
        mEditor.putString(getString(R.string.name), String.format("%s", valorselecionado));
        mEditor.apply();

        if(valorselecionado == 1)
            salvarInterno();
        else
            salvarExterno();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.positivo:
                if (checked)
                    valorselecionado = 1;
                break;
            case R.id.negativo: //externo
                if (checked)
                    valorselecionado = 2;
                break;
        }
    }

    private void checarObjeto(){
        File file = getFileStreamPath(FILENAME);
        nome = findViewById(R.id.Nome);
        telefone = findViewById(R.id.Telefone);
        idade = findViewById(R.id.Idade);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Usuario retorno = (Usuario) ois.readObject();
            ois.close();
            fis.close();
            nome.setText(retorno.getNome());
            idade.setText(retorno.getIdade());
            telefone.setText(retorno.getTelefone());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
