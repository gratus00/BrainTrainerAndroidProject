package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfAnswer;
    int score;
    int numberOfQuestions;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;


    public void start(View view){
        goButton.setVisibility(View.GONE);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");

        newQuestion();
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.format(l/1000 + "s"));
            }

            @Override
            public void onFinish() {
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                goButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int correctAnswer = a+b;
        sumTextView.setText(Integer.toString(a) +" + "+Integer.toString(b));

        locationOfAnswer = rand.nextInt(4);

        answers.clear();
        for(int i=0; i<4; i++){
            if(i==locationOfAnswer){
                answers.add(correctAnswer);
            }else{
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == correctAnswer){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        } else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(String.format(score+ "/"+ numberOfQuestions));
        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        button0.setClickable(false);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);

        newQuestion();
    }
}