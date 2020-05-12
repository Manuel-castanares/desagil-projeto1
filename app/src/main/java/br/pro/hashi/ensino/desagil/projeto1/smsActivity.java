package br.pro.hashi.ensino.desagil.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class smsActivity extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS = 0;
    private String frase;
    private Translator translator;
    private String final_frase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sms);

        Button back = findViewById(R.id.volta);
        back_button(back);

        translator = new Translator();

        Button chatToMorse = findViewById(R.id.charToMorse);

        Button morseToChar = findViewById(R.id.morseToChar);

        Button back_button = findViewById(R.id.back_button);

        Button sms = findViewById(R.id.sms);

        Button dot_button = findViewById(R.id.dot_button);
        morseButton(dot_button);


        Button backspace_button = findViewById(R.id.backspace_button);
        backspaceButton(backspace_button);

        Button enter_button = findViewById(R.id.enter_button);
        enterButton(enter_button);

        Button clear_button = findViewById(R.id.clear_button);
        clearButton(clear_button);
    }
    public void back_button(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(smsActivity.this, MorseActivity.class));
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
            button.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View view) {
                    if (frase == null) {
                        frase = "-";
                    } else {
                        frase = frase + "-";
                    }
                    text.setText(frase);
                    return true;
                }
            });
        }
    }
    public void enterButton(Button button){
        TextView text = findViewById(R.id.morse);
        TextView translation = findViewById(R.id.numero);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frase != null){
                    String letra;
                    text.setText("");

                    if (frase.length() == 5){

                        letra = String.valueOf(translator.morseToChar(frase));

                        if (final_frase != null){
                            final_frase += letra;
                        } else {
                            final_frase = letra;
                        }
                        translation.setText(final_frase);

                        frase = null;
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Numero Invalido", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, -100);
                        toast.show();

                        frase = null;
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Insira Codigo para Traducao", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, -100);
                    toast.show();
                }
            }
        });
    }
    public void backspaceButton(Button button){
        TextView translation = findViewById(R.id.numero);
        TextView text = findViewById(R.id.morse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frase != null){
                    frase = frase.substring(0, frase.length() - 1);
                }
                text.setText(frase);
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (final_frase != null){
                    final_frase = final_frase.substring(0, final_frase.length() - 1);
                }
                translation.setText(final_frase);
                return true;
            }
        });
    }
    public void clearButton(Button button ){
        TextView translation = findViewById(R.id.numero);
        TextView morse = findViewById(R.id.morse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final_frase = null;
                frase = null;
                translation.setText("");
                morse.setText("");
            }
        });
    }
}
