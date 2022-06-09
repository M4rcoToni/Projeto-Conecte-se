package com.example.alterar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class lerqrcode extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 161;

    private CodeScanner Scanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lerqrcode);
        getSupportActionBar().hide();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        Scanner = new CodeScanner(this, scannerView);
        Scanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(lerqrcode.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Scanner.startPreview();
    }

    @Override
    protected void onPause() {
        Scanner.releaseResources();
        super.onPause();
    }
}