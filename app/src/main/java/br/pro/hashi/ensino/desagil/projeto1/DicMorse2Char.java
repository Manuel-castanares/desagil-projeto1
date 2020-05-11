package br.pro.hashi.ensino.desagil.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;

public class DicMorse2Char extends AppCompatActivity {
    private Translator translator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translator = new Translator();

        setContentView(R.layout.activity_dic_morse2_char);

        Button back_button = findViewById(R.id.tradutor);
        next_button(back_button);
        add_list();
    }

    private void next_button(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DicMorse2Char.this, MorseActivity.class));
            }
        });
    }

    private void add_list() {
        ListView listView = findViewById(R.id.list);
        LinkedList<String> codes = translator.getCodes();
        LinkedList<String> morse = new LinkedList<>();

        for(String code: codes) {
            morse.add(code + "  =  " + "' " + translator.morseToChar(code) + " '");
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, morse);
        listView.setAdapter(arrayAdapter);
    }
}
