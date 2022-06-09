package com.example.alterar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.ImageView;
import android.widget.Toast;

import net.glxn.qrgen.android.QRCode;

public class QRCODE extends AppCompatActivity {
    private ImageView qrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getSupportActionBar().hide();

        Bitmap myBitmap = QRCode.from(descobreIP()).bitmap();
        qrcode = findViewById(R.id.qrcode);
        qrcode.setImageBitmap(myBitmap);
    }

    public String descobreIP(){
        //< 3 - Pegando IP
        String ip = "";
        try {
            WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
            ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Nao da pra por msotrar nenhum ip na tela recebe.setText("192.168.199.12");
        Toast.makeText(getApplicationContext(), ip, Toast.LENGTH_LONG).show();

        return ip;
    }
}