package com.example.alterar;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Button ler;
    private Button mostrar;
    static TextView titulo;
    static TextView recebe;
    static MyThread server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            getSupportActionBar().hide();

        //< 2 - Politicas de Uso
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //>

        mostrar = findViewById(R.id.mostrar);
        ler = findViewById(R.id.ler);
        titulo = findViewById(R.id.titulo);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrar = new Intent(getApplicationContext(), QRCODE.class);
                startActivity(mostrar);
            }
        });
        server = new MyThread();
        new Thread(server).start();

        ler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ler = new Intent(getApplicationContext(), lerqrcode.class);
                startActivity(ler);
            }
        });

    }

    private class MyThread implements Runnable {

        @Override
        public void run() {
            ServerSocket welcomeSocket = null;
            try {
                welcomeSocket = new ServerSocket(6790);
                com.example.alterar.tcpserver server = new com.example.alterar.tcpserver();
                server.start();
                while (true) {
                    Thread.sleep((long)(Math.random() * 10000));

                    Socket socketConexao = welcomeSocket.accept();
                    titulo.setText("Conexao realizada");
                    BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
                    String dados = doCliente.readLine();
                    recebe.setText(dados);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    }

}