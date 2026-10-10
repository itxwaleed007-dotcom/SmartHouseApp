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

public class RoomActivity extends AppCompatActivity {

    TextView tvRoomName, tvRoomLightStatus;
    Switch switchRoomLight;
    Button btnRoomBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRoomName = findViewById(R.id.tvRoomName);
        tvRoomLightStatus = findViewById(R.id.tvRoomLightStatus);
        switchRoomLight = findViewById(R.id.switchRoomLight);
        btnRoomBack = findViewById(R.id.btnRoomBack);

        // Pichli screen se room ka naam lo
        String roomName = getIntent().getStringExtra("roomName");
        tvRoomName.setText(roomName);

        switchRoomLight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tvRoomLightStatus.setText(isChecked ? "ON" : "OFF");
            tvRoomLightStatus.setTextColor(Color.parseColor(isChecked ? "#16A34A" : "#777777"));
            Toast.makeText(this, roomName + " Light " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
        });

        btnRoomBack.setOnClickListener(view -> finish());
    }
}