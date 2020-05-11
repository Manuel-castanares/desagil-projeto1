package br.pro.hashi.ensino.desagil.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;

public class DicChar2Morse extends AppCompatActivity {
    private Translator translator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translator = new Translator();

        setContentView(R.layout.activity_dic_char2_morse);

        Button back_button = findViewById(R.id.tradutor);
        next_button(back_button);

        add_list();
    }

    private void add_list() {
        ListView listView = findViewById(R.id.list);
        char[] letras = translator.getChars();
        LinkedList<String> morse = new LinkedList<>();

        for(char i: letras){
            morse.add(i + "  =  " + "' " + translator.charToMorse(i) + " '");
        }
        //Removento a "root" da arvore
        morse.pop();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, morse);
        listView.setAdapter(arrayAdapter);
    }

    private void next_button(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DicChar2Morse.this, MorseActivity.class));
            }
        });
    }
}
