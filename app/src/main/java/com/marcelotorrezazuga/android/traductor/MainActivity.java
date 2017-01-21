package com.marcelotorrezazuga.android.traductor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    EditText a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (EditText)findViewById(R.id.editText);
        b = (EditText)findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.acercaDe:
                Intent i = new Intent(this,TerceraActivity.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void administrar(View v){
        Intent i = new Intent(this,SegundaActivity.class);
        startActivity(i);
    }

    public void aymara(View v){
        try{
            String español = a.getText().toString();
            String aymara="";

            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //Leer
                String texto = "", linea;
                while ((linea = br.readLine())!= null){
                    String campos[] = linea.split(" ");
                    //Grabar
                    if(campos[0].equals(español)){
                        aymara = campos[1];
                    }
                }
                //Cerrar

                br.close();
                b.setText(aymara);
                Toast.makeText(getApplicationContext(), "Aymara", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quechua(View v){
        try{
            String español = a.getText().toString();
            String quechua="";

            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //Leer
                String texto = "", linea;
                while ((linea = br.readLine())!= null){
                    String campos[] = linea.split(" ");
                    //Grabar
                    if(campos[0].equals(español)){
                        quechua = campos[2];
                    }
                }
                //Cerrar

                br.close();
                b.setText(quechua);
                Toast.makeText(getApplicationContext(), "Quechua", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
