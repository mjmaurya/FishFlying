package com.fishflying.org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    Button button;
    TextView displayScore;
    String Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        button=findViewById(R.id.button);
        displayScore=findViewById(R.id.textView3);
        Score=getIntent().getExtras().get("score").toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverActivity.this,MainActivity.class));
            }
        });
        displayScore.setText("Score :"+Score);
    }
}
