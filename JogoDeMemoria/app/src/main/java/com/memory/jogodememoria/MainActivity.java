package com.memory.jogodememoria;

import android.graphics.Color;
import android.provider.ContactsContract;
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
    private int vitorias = 0;
    private int tentativas = 0;
    private Memoria memoria = new Memoria();
    Database database = new Database(this);
    boolean ganhei = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView victory = (TextView) findViewById(R.id.vitoria);
        victory.setVisibility(View.INVISIBLE);
        TextView elogio = (TextView) findViewById(R.id.elogio);
        elogio.setVisibility(View.INVISIBLE);

        TextView viewTentativa = (TextView) findViewById(R.id.tentativas);
        viewTentativa.setVisibility(View.INVISIBLE);
        TextView viewVitoria = (TextView) findViewById(R.id.ganhei);
        viewVitoria.setVisibility(View.INVISIBLE);
        TextView viewQtdeTentativa = (TextView) findViewById(R.id.qtdeTentativas);
        viewQtdeTentativa.setVisibility(View.INVISIBLE);
        TextView viewQtdeVitoria = (TextView) findViewById(R.id.qtdeVitoria);
        viewQtdeVitoria.setVisibility(View.INVISIBLE);
        memoria.setVitorias(0);
        memoria.setTentativas(0);
        database.insert(memoria);
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
                    tentativas++;
                    ganhei = false;
                    reiniciar(tela, ganhei);
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
            vitorias++;
            numeros = gerarNumeros();
            TextView victory = (TextView) findViewById(R.id.vitoria);
            victory.setVisibility(View.VISIBLE);
            TextView elogio = (TextView) findViewById(R.id.elogio);
            elogio.setVisibility(View.VISIBLE);
            tela.setBackgroundColor(Color.YELLOW);

            // é ridiculo isso mas ainda não consegui pensar numa ideia melhor!!!
            memoria.setTentativas(tentativas);
            memoria.setVitorias(vitorias);
            database.insert(memoria);
            memoria = database.qtdeVitoriasETentarivas();

            TextView viewTentativa = (TextView) findViewById(R.id.tentativas);
            viewTentativa.setVisibility(View.VISIBLE);

            TextView viewQtdeTentativa = (TextView) findViewById(R.id.qtdeTentativas);
            viewQtdeTentativa.setText(memoria.getTentativas());
            viewQtdeTentativa.setVisibility(View.VISIBLE);

            TextView viewVitoria = (TextView) findViewById(R.id.ganhei);
            viewVitoria.setVisibility(View.VISIBLE);

            TextView viewQtdeVitoria = (TextView) findViewById(R.id.qtdeVitoria);
            viewQtdeVitoria.setText(memoria.getVitorias());
            viewQtdeVitoria.setVisibility(View.VISIBLE);

        }
    }

    public void reiniciar(View view, boolean ganhei) {
        seq = 0;
        if(!ganhei)
            tentativas++;

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
        TextView viewTentativa = (TextView) findViewById(R.id.tentativas);
        viewTentativa.setVisibility(View.INVISIBLE);
        TextView viewVitoria = (TextView) findViewById(R.id.ganhei);
        viewVitoria.setVisibility(View.INVISIBLE);
        TextView viewQtdeTentativa = (TextView) findViewById(R.id.qtdeTentativas);
        viewQtdeTentativa.setVisibility(View.INVISIBLE);
        TextView viewQtdeVitoria = (TextView) findViewById(R.id.qtdeVitoria);
        viewQtdeVitoria.setVisibility(View.INVISIBLE);
    }

    public List<Integer> gerarNumeros(){
        List<Integer> numeros = new ArrayList<>();
        for(int i = 1; i <=6; i++)
            numeros.add(i);
        Collections.shuffle(numeros);
        return numeros;
    }

}
