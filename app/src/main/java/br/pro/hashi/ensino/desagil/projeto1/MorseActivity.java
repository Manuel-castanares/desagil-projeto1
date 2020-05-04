package br.pro.hashi.ensino.desagil.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Vector;

public class MorseActivity extends AppCompatActivity {
    private String frase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);

        Button back_button = findViewById(R.id.back_button);

        nextButton(back_button);

        Button dot_button = findViewById(R.id.dot_button);
        Button dash_button = findViewById(R.id.dash_button);
        morseButton(dot_button);
        morseButton(dash_button);

        Button backspace_button = findViewById(R.id.backspace_button);
        Button space_button = findViewById(R.id.space_button);
        Button enter_button = findViewById(R.id.enter_button);
        Button clear_button = findViewById(R.id.clear_button);
        enterButton(enter_button);
    }

    public void nextButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MorseActivity.this, MainActivity.class));
            }
        });
    }
    public void morseButton(Button button){
        TextView text = findViewById(R.id.morse);
        if (button == findViewById(R.id.dot_button)){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (frase == null) {
                        frase = ".";
                    } else {
                        frase = frase + ".";
                    }
                    text.setText(frase);
                }
            });
        }
        else if (button == findViewById(R.id.dash_button)){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (frase == null) {
                        frase = "-";
                    } else {
                        frase = frase + "-";
                    }
                    text.setText(frase);
                }
            });
        }
    }
    public void enterButton(Button button){
        TextView text = findViewById(R.id.morse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
                frase = null;
            }
        });
    }
}
