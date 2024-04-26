package com.example.techtitans.content_activities;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.techtitans.databinding.ActivityGamemathBinding;
//import com.kastorcode.mathoperationsgame.R;
//import com.kastorcode.mathoperationsgame.helpers.Navigate;

import com.example.techtitans.databinding.ActivityMathBinding;

import java.util.Locale;
import java.util.Random;

public class MathgameActivity extends AppCompatActivity {

    private ActivityGamemathBinding binding;

    private static String operation;
    private static final Random random = new Random();
    private static final int MAX_VALUE = 99;
    private static final float[] operators = new float[2];
    private static int userScore;
    private static int userLife;
    private static final long TIMER_START = 30000;
    private static Boolean isTimerRunning;
    private static CountDownTimer timer;
    private TextView score;
    private TextView life;
    private TextView time;
    private TextView question;
    private EditText answer;
    private Button ok;
    private Button next;

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        //Navigate.mainActivity(this, userScore);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGamemathBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setProps();
        setClickListeners();
        getQuestion();
    }

    private void setProps () {
        operation = getIntent().getStringExtra("operation");
        userScore = 0;

        binding.score.setText(String.valueOf(userScore));
        userLife = 3;
        binding.userlife.setText(String.valueOf(userLife));

    }

    private void setClickListeners () {
        ok.setOnClickListener(view -> checkAnswer());
        next.setOnClickListener(view -> {
            if (userLife > 0) {
                getQuestion();
            }
            else {
                //Navigate.mainActivity(this, userScore);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getQuestion () {
        for (int i = 0; i < operators.length; i++) {
            operators[i] = (float) random.nextInt(MAX_VALUE);
        }
        question.setText(String.format(Locale.US, "%.0f", operators[0]) + " " + operation + " " + String.format(Locale.US, "%.0f", operators[1]));
        answer.setText("");
        ok.setVisibility(View.VISIBLE);
        next.setVisibility(View.INVISIBLE);
        startTimer();
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer () {
        if (isTimerRunning) {
            timer.cancel();
        }
        ok.setVisibility(View.INVISIBLE);
        next.setVisibility(View.VISIBLE);
        float correctAnswer = getCorrectAnswer();
        question.setText(question.getText().toString() + " = " + String.format(Locale.US, "%.02f", correctAnswer));
        String userAnswer = answer.getText().toString();
        if (!userAnswer.isEmpty()) {
            if (correctAnswer == Float.parseFloat(userAnswer)) {
                answerIsCorrect();
                return;
            }
        }
        answerIsWrong();
    }

    @SuppressWarnings("all")
    private float getCorrectAnswer () {
        switch (operation) {
            case "+":
                return operators[0] + operators[1];
            case "-":
                return operators[0] - operators[1];
            case "*":
                return operators[0] * operators[1];
            case "/":
                return operators[0] / operators[1];
            default:
                throw new Error("Invalid Operation " + operation);
        }
    }

    private void answerIsCorrect () {
        userScore++;
        score.setText(String.valueOf(userScore));
    }

    private void answerIsWrong () {
        userLife--;
        life.setText(String.valueOf(userLife));
    }

    private void startTimer () {
        isTimerRunning = true;
        timer = new CountDownTimer(TIMER_START, 1000) {
            @Override
            public void onTick (long millisUntilFinish) {
                time.setText(String.valueOf(millisUntilFinish / 1000));
            }

            @Override
            public void onFinish () {
                isTimerRunning = false;
                checkAnswer();
            }
        }
                .start();
    }
    
    private void Navigate(){
        
    }

}