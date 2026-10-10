package com.boots.smarthouseapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    Button btnBack;
    Switch switchNotif;
    TextView tvNotifStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        switchNotif = findViewById(R.id.switchNotif);
        tvNotifStatus = findViewById(R.id.tvNotifStatus);

        switchNotif.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tvNotifStatus.setText(isChecked ? "ON" : "OFF");
            tvNotifStatus.setTextColor(Color.parseColor(isChecked ? "#16A34A" : "#777777"));
            Toast.makeText(this, isChecked ? "Notifications ON" : "Notifications OFF", Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(view -> finish());
    }
}