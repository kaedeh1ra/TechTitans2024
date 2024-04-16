package com.example.techtitans.content_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.techtitans.R;
import com.example.techtitans.adapters.UsersAdapter;
import com.example.techtitans.classes.User;
import com.example.techtitans.databinding.ActivityBotsBinding;
import com.example.techtitans.listeners.UserListener;
import com.example.techtitans.utilities.Constants;
import com.example.techtitans.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BotsActivity extends AppCompatActivity implements UserListener {
    private ActivityBotsBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBotsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
        getUsers();
    }
    private void setListeners(){
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void getUsers(){
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    if(task.isSuccessful() && task.getResult() != null){
                        List<User> users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                            if(!queryDocumentSnapshot.getId().equals(currentUserId)){
                                User user = new User();
                                user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                                user.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                                user.id = queryDocumentSnapshot.getId();
                                users.add(user);
                            }

                        }
                        if(users.size()>0){
                            UsersAdapter usersAdapter = new UsersAdapter(users, this);
                            binding.usersRecyclerView.setAdapter(usersAdapter);
                            binding.usersRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else{
                        showErrorMessage();
                    }
                });
    }


    private void showErrorMessage(){
        binding.textErrorMessage.setText(String.format("%s", "Нет доступных ботов"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
    }
}