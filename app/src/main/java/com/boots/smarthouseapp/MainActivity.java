package com.boots.smarthouseapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton btnSettings;
    Switch switchLight, switchAC, switchTV;
    TextView tvLightStatus, tvACStatus, tvTVStatus;
    CardView cardLivingRoom, cardBedroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Settings button
        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Switches + status texts
        switchLight = findViewById(R.id.switchLight);
        switchAC = findViewById(R.id.switchAC);
        switchTV = findViewById(R.id.switchTV);

        tvLightStatus = findViewById(R.id.tvLightStatus);
        tvACStatus = findViewById(R.id.tvACStatus);
        tvTVStatus = findViewById(R.id.tvTVStatus);

        switchLight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateStatus(tvLightStatus, isChecked);
            Toast.makeText(this, isChecked ? "Living Room Light ON" : "Living Room Light OFF", Toast.LENGTH_SHORT).show();
        });

        switchAC.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateStatus(tvACStatus, isChecked);
            Toast.makeText(this, isChecked ? "Air Conditioner ON" : "Air Conditioner OFF", Toast.LENGTH_SHORT).show();
        });

        switchTV.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateStatus(tvTVStatus, isChecked);
            Toast.makeText(this, isChecked ? "Smart TV ON" : "Smart TV OFF", Toast.LENGTH_SHORT).show();
        });

        // Room cards
        cardLivingRoom = findViewById(R.id.cardLivingRoom);
        cardBedroom = findViewById(R.id.cardBedroom);

        cardLivingRoom.setOnClickListener(view -> openRoom("Living Room"));
        cardBedroom.setOnClickListener(view -> openRoom("Bedroom"));
    }

    // Status text ko ON/OFF aur color set karta hai
    private void updateStatus(TextView statusView, boolean isOn) {
        statusView.setText(isOn ? "ON" : "OFF");
        statusView.setTextColor(Color.parseColor(isOn ? "#16A34A" : "#777777"));
    }

    private void openRoom(String roomName) {
        Intent intent = new Intent(MainActivity.this, RoomActivity.class);
        intent.putExtra("roomName", roomName);
        startActivity(intent);
    }

}