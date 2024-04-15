package com.example.techtitans.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.techtitans.content_activities.ConversationActivity;
import com.example.techtitans.databinding.ActivityGameRulesBinding;

public class GameRulesActivity extends AppCompatActivity {

    private ActivityGameRulesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityGameRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.button.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ConversationActivity.class));
        });
    }
}