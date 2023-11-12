package com.example.myapplication;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class WifiActivity extends AppCompatActivity {
    TextView networkDetail;
    Button networkDetailButton, wifiStateButton;
    WifiManager wifiMng;
    String service = Context.WIFI_SERVICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_layout);
        networkDetailButton = findViewById(R.id.network_detail_button);
        networkDetail = findViewById(R.id.network_detail);
        wifiStateButton = findViewById(R.id.wifi_state_button);
        wifiMng = (WifiManager) getSystemService(service);


        networkDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiInfo wifiInfo = wifiMng.getConnectionInfo();
                String ssid = wifiInfo.getSSID();
                String bssid = wifiInfo.getBSSID();
                String networkId = String.valueOf(wifiInfo.getNetworkId());
                String ipAddress = String.valueOf(wifiInfo.getIpAddress());
                String networkDetails = "SSID: " + ssid + "\nBSSID: " + bssid + "\nNetwork ID: " + networkId + "\nIP Address: " + ipAddress;
                networkDetail.setText(networkDetails);
            }
        });
        wifiStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWifiState();
            }
        });
    }
    private void showWifiState() {
        int state = this.wifiMng.getWifiState();
        String statusInfo = "Unknown";

        switch (state) {
            case WifiManager.WIFI_STATE_DISABLING:
                statusInfo = "Disabling";
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                statusInfo = "Disabled";
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                statusInfo = "Enabling";
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                statusInfo = "Enabled";
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                statusInfo = "Unknown";
                break;
            default:
                statusInfo = "Unknown";
                break;
        }
        Toast.makeText(this, "Wifi Status: " + statusInfo, Toast.LENGTH_LONG).show();
    }
}

