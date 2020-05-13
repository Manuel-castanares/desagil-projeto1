package br.pro.hashi.ensino.desagil.projeto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import br.pro.hashi.ensino.desagil.projeto1.Translator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Vector;

public class MorseActivity extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS = 0;
    private String frase;
    private Translator translator;
    private String final_frase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);

        translator = new Translator();

        Button chatToMorse = findViewById(R.id.charToMorse);

        Button morseToChar = findViewById(R.id.morseToChar);

        Button back_button = findViewById(R.id.back_button);

        Button sms = findViewById(R.id.sms);

        nextButton(back_button);
        nextButton(morseToChar);
        nextButton(chatToMorse);
        nextButton(sms);


        Button dot_button = findViewById(R.id.dot_button);
        morseButton(dot_button);


        Button backspace_button = findViewById(R.id.backspace_button);
        backspaceButton(backspace_button);

        Button space_button = findViewById(R.id.space_button);
        spaceButton(space_button);

        Button enter_button = findViewById(R.id.enter_button);
        enterButton(enter_button);

        Button clear_button = findViewById(R.id.clear_button);
        clearButton(clear_button);

        //Baseado no codigo ExemploSMS
        sms.setOnClickListener((view) -> {

            // Verifica se o aplicativo tem a permissão desejada.
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                // Se tem, podemos iniciar a SMSActivity direto.
                nextButton(sms);
            } else {

                // Senão, precisamos pedir essa permissão.

                // Cria um vetor de permissões a pedir. Como queremos
                // uma só, parece um pouco feio, mas é bem conveniente
                // quando queremos pedir várias permissões de uma vez.
                String[] permissions = new String[]{
                        Manifest.permission.SEND_SMS,
                };

                ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
            }
        });
    }

    public void nextButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button == findViewById(R.id.back_button)){
                    startActivity(new Intent(MorseActivity.this, MainActivity.class));
                } else if (button == findViewById(R.id.morseToChar)){
                    startActivity(new Intent(MorseActivity.this, DicMorse2Char.class));
                } else if (button == findViewById(R.id.charToMorse)){
                    startActivity(new Intent(MorseActivity.this, DicChar2Morse.class));
                } else if (button == findViewById(R.id.sms)){
                    Intent intent = new Intent(MorseActivity.this, smsActivity.class);
                    intent.putExtra("MSG",final_frase);
                    startActivity(intent);
                }
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
        TextView translation = findViewById(R.id.texto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frase != null){
                    String letra;
                    text.setText("");

                    if (frase.length() <= 5){

                        try {
                            letra = String.valueOf(translator.morseToChar(frase));
                            if (final_frase != null) {
                                final_frase += letra;
                            } else {
                                final_frase = letra;
                            }
                            translation.setText(final_frase);
                        } catch (IllegalArgumentException exception) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Falha na Traducao", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, -100);
                            toast.show();
                        }

                        frase = null;
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Char Invalida", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, -300);
                        toast.show();

                        frase = null;
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Insira Codigo para Traducao", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, -300);
                    toast.show();
                }
            }
        });
    }
    public void backspaceButton(Button button){
        TextView translation = findViewById(R.id.texto);
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
        TextView translation = findViewById(R.id.texto);
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
    public void spaceButton(Button button){
        TextView translation = findViewById(R.id.texto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_frase != null){
                    final_frase += " ";
                }
                translation.setText(final_frase);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Verifica se de fato é uma resposta ao pedido acima e se a
        // resposta foi positiva. As respostas estão armazenadas no
        // vetor grantResults, que pode estar vazio se o usuário
        // escolheu simplesmente ignorar o pedido e não responder nada.
        if (requestCode == REQUEST_SEND_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // Se foi positiva, podemos iniciar a SMSActivity.
            nextButton(findViewById(R.id.sms));
        }
    }
}
