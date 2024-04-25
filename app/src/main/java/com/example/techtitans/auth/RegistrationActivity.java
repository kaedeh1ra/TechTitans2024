package com.example.techtitans.auth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.techtitans.R;
import com.example.techtitans.classes.User;
import com.example.techtitans.content_activities.BaseActivity;
import com.example.techtitans.content_activities.ConversationActivity;
import com.example.techtitans.databinding.ActivityRegistrationBinding;
import com.example.techtitans.utilities.Constants;
import com.example.techtitans.utilities.PreferenceManager;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }

    private void setListeners() {
        binding.buttonSignIn.setOnClickListener(v -> onBackPressed());
        binding.buttonSignUp.setOnClickListener(v -> {
            if (isValidSignUpDetails()) {
                signUp();
            }
        });
//        binding.layoutImage.setOnClickListener(v -> {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    pickImage.launch(intent);
//                }
//        );
//        binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    binding.buttonSignUp.setBackgroundColor(Color.BLUE);
//                    binding.buttonSignUp.setOnClickListener(v -> {
//                        if (isValidSignUpDetails()) {
//                            signUp();
//                        }
//                    });
//                }
//                else{
//                    binding.buttonSignUp.setBackgroundColor(Color.GRAY);
//                    binding.buttonSignUp.setOnClickListener(v -> {
//                    });
//                }
//            }
//        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signUp() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, binding.inputName.getText().toString());
        user.put(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString());
        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_NAME, binding.inputName.getText().toString());
                    preferenceManager.putString(Constants.KEY_PHONE, binding.inputPhoneNumber.getMaskString().toString());
                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    Intent intent = new Intent(getApplicationContext(), ConversationActivity.class);
                    startActivity(intent);
                    finish();

                })
                .addOnFailureListener(exception -> {
                    showToast(exception.getMessage());
                });
    }


    private Boolean isValidSignUpDetails() {
        if (binding.inputName.getText().toString().trim().isEmpty()) {
            showToast("Enter name");
            return false;
        } else if (binding.inputPhoneNumber.getMaskString().isEmpty()) {
            showToast("Введите телефон");
            return false;
        } else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Введите пароль");
            return false;
        } else if (binding.inputConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Подтвердите пароль");
            return false;
        } else if (!binding.inputConfirmPassword.getText().toString().equals(binding.inputPassword.getText().toString())) {
            showToast("Пароли не совпадают");
            return false;
        } else {
            return true;
        }
    }



    private void navigate(Class clazz){
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
        finish();
    }

}