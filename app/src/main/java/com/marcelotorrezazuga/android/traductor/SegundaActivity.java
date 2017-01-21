package com.marcelotorrezazuga.android.traductor;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SegundaActivity extends ActionBarActivity {
    EditText a,b,c;
    TextView x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        a = (EditText) findViewById(R.id.editText);
        b = (EditText) findViewById(R.id.editText2);
        c = (EditText) findViewById(R.id.editText3);
        x = (TextView) findViewById(R.id.textView5);
    }

    public void listar(View v){
        try{
            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //Leer
                String texto = "", linea;
                while ((linea=br.readLine()) != null){
                    texto = texto + linea +"\n";
                }
                //Cerrar
                br.close();

                //
                x.setText(texto);
                Toast.makeText(getApplicationContext(), "Listado", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertar(View v){
        try{
            String español = a.getText().toString();
            String aymara = b.getText().toString();
            String quechua = c.getText().toString();

            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");
                if(!f.exists()) {
                    f.createNewFile();
                }
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);

                //Grabar
                bw.write(español+" "+aymara+" "+quechua+"\n");

                //Cerrar

                bw.close();

                Toast.makeText(getApplicationContext(),"Insertar",Toast.LENGTH_SHORT).show();
                a.setText("");
                b.setText("");
                c.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modificar(View v){
        try{
            String español = a.getText().toString();
            String aymara = b.getText().toString();
            String quechua = c.getText().toString();

            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");
                File f2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"CopiaT.txt");

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                FileWriter fw = new FileWriter(f2);
                BufferedWriter bw = new BufferedWriter(fw);

                //Leer
                String texto = "", linea;
                while ((linea = br.readLine())!= null){
                    String campos[] = linea.split(" ");
                    //Grabar
                    if(campos[0].equals(español)){
                        bw.write(español +" "+aymara+" "+quechua+"\n");
                    }
                    else{
                        bw.write(linea+"\n");
                    }
                }
                //Cerrar

                br.close();
                bw.close();

                f.delete();

                if(f2.exists()){
                    f2.renameTo(f);
                }


                Toast.makeText(getApplicationContext(),"Modificado",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(View v){
        try{
            String español = a.getText().toString();
            String aymara = b.getText().toString();
            String quechua = c.getText().toString();

            // Estado actual de la memoria externa
            String estado = Environment.getExternalStorageState();

            //Esta disponible la memoria externa
            if(Environment.MEDIA_MOUNTED.equals(estado)|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(estado)){

                //Abrir Archivo
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Traductor.txt");
                File f2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"CopiaT.txt");

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                FileWriter fw = new FileWriter(f2);
                BufferedWriter bw = new BufferedWriter(fw);

                //Leer
                String texto = "", linea;
                while ((linea = br.readLine())!= null){
                    String campos[] = linea.split(" ");
                    //Graba
                    if(!campos[0].equals(español)){
                        bw.write(linea +"\n");
                    }
                }
                //Cerrar

                br.close();
                bw.close();
                //Eliminar archivo 1
                f.delete();

                if(f2.exists()){
                    f2.renameTo(f);
                }


                Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Memoria externa no disponible",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
