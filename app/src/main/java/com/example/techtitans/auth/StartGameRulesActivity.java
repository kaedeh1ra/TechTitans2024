package com.example.techtitans.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.techtitans.content_activities.ConversationActivity;
import com.example.techtitans.databinding.ActivityStartGameRulesBinding;

public class StartGameRulesActivity extends AppCompatActivity {

    private ActivityStartGameRulesBinding binding;

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStartGameRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);


        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            //strat  DataActivity beacuase its your app first run
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
            startActivity(new Intent(getApplicationContext(), GameRulesActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), ConversationActivity.class));
            finish();
        }

    }
}