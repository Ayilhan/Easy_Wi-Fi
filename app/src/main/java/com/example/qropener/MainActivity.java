package com.example.qropener;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity{

    private ImageView ivQrScan;
    private EditText etWiFiData;
    private ImageView bConnectWiFi;
    ImageView enableButton,disableButton;

    String ssid = "Name Wi-Fi;";
    String key = "Password";
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton=(ImageView)findViewById(R.id.button1);
        disableButton=(ImageView) findViewById(R.id.button2);

        enableButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
            }
        });

        disableButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);
            }
        });

        ivQrScan = findViewById(R.id.iv_qrScan);
        etWiFiData = findViewById(R.id.id_wifi_data);
        bConnectWiFi = findViewById(R.id.id_connect);

        etWiFiData.setText(ssid + " " + key);


        bConnectWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", ssid);
                wifiConfig.preSharedKey = String.format("\"%s\"", key);
                wifiConfig.status = WifiConfiguration.Status.CURRENT;
//                wifiConfig.priority = 40;

                WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);

                WifiInfo wifi_inf = wifiManager.getConnectionInfo();
                wifiManager.disableNetwork(wifi_inf.getNetworkId());
                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();

                


            }
        });


        ivQrScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrReadActivity.class);
                startActivityForResult(intent, 0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 0){
            String s = data.getStringExtra("result");
            etWiFiData.setText(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            String[] word;
            String q = ";";
            word = s.split(q);
            for(int i = 0; i < 2; i++) {
                ssid = word[0];
                key = word[1];
            }

        }


    }



}
