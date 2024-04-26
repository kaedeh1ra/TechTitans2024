package com.example.techtitans.content_activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.techtitans.R;
import com.example.techtitans.adapters.RecentConvarsationsAdapter;
import com.example.techtitans.auth.AuthActivity;
import com.example.techtitans.classes.ChatMessage;
import com.example.techtitans.classes.User;
import com.example.techtitans.databinding.ActivityConversationBinding;
import com.example.techtitans.databinding.ActivityMathBinding;
import com.example.techtitans.listeners.ConversationListener;
import com.example.techtitans.utilities.Constants;
import com.example.techtitans.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MathActivity extends AppCompatActivity implements ConversationListener {

    ActivityMathBinding binding;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;
    private List<ChatMessage> conversations;
    private RecentConvarsationsAdapter conversationsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Button go2 = findViewById(R.id.go2);
        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MathActivity.this, MathgameActivity.class);
                startActivity(intent);
            }
        });
    }
    }
