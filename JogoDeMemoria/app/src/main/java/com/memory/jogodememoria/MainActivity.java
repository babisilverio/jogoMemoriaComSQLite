package com.memory.jogodememoria;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> numeros = gerarNumeros();
    private int seq = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView victory = (TextView) findViewById(R.id.vitoria);
        victory.setVisibility(View.INVISIBLE);
        TextView elogio = (TextView) findViewById(R.id.elogio);
        elogio.setVisibility(View.INVISIBLE);
    }


    public void clickBotao(View view){
        acertaSequencia(view, numeros);
    }

    public void acertaSequencia(View view, List<Integer> num){
        Button botaoClicado = (Button) findViewById(view.getId());
        View tela = findViewById(R.id.fundo_tela);
        if(seq < 6) {
            try {
                int numero = num.get(seq);
                int numBotao = Integer.parseInt(botaoClicado.getText().toString());
                if (numBotao != numero) {
                    seq = 0;
                    reiniciar(tela);
                } else {
                    tela.setBackground(botaoClicado.getBackground());
                    botaoClicado.setVisibility(View.INVISIBLE);
                    seq++;
                }
            } catch (NumberFormatException e) {
                Log.e("ZICA", "Deu merda");
            }
        } if(seq == 6){
            seq = 0;
            numeros = gerarNumeros();
            TextView victory = (TextView) findViewById(R.id.vitoria);
            victory.setVisibility(View.VISIBLE);
            TextView elogio = (TextView) findViewById(R.id.elogio);
            elogio.setVisibility(View.VISIBLE);
            tela.setBackgroundColor(Color.YELLOW);
        }
    }

    public void reiniciar(View view) {
        seq = 0;
        Button show = (Button) findViewById(R.id.button1);
        show.setVisibility(View.VISIBLE);

        Button show2 = (Button) findViewById(R.id.button2);
        show2.setVisibility(View.VISIBLE);

        Button show3 = (Button) findViewById(R.id.button3);
        show3.setVisibility(View.VISIBLE);

        Button show4 = (Button) findViewById(R.id.button4);
        show4.setVisibility(View.VISIBLE);

        Button show5 = (Button) findViewById(R.id.button5);
        show5.setVisibility(View.VISIBLE);

        Button show6 = (Button) findViewById(R.id.button6);
        show6.setVisibility(View.VISIBLE);

        View tela = findViewById(R.id.fundo_tela);
        tela.setBackgroundColor(Color.WHITE);

        TextView victory = (TextView) findViewById(R.id.vitoria);
        victory.setVisibility(View.INVISIBLE);
        TextView elogio = (TextView) findViewById(R.id.elogio);
        elogio.setVisibility(View.INVISIBLE);
    }

    public List<Integer> gerarNumeros(){
        List<Integer> numeros = new ArrayList<>();
        for(int i = 1; i <=6; i++)
            numeros.add(i);
        Collections.shuffle(numeros);
        return numeros;
    }

}
