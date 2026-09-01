package com.example.projetopdm_ii;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Tela02 extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, Runnable, View.OnClickListener{
    private Toolbar toolbar;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private Handler handler;
    private int musica, indiceLista;
    private ArrayList<Playlist> lista;
    private CardView card1,card2,card3,card4,card5;
    private TextView textoMusicaSelecionada, textoMusicaTocando;

    private ImageView imgPreview, imgNext;
    //___________________________________________________________________________________________________________________________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); //a depender do tipo de dispositivo, o app ira prenecer a tela toda no maximo posivel
        setContentView(R.layout.activity_tela02);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
        //atribui a toolbar o "poder" de ActionBar
        setSupportActionBar(toolbar);
        //habilita o botão de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //🠔
        seekbar = findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);
        handler = new Handler();

        musica = R.raw.forrodofarol_quincasmoreira;

        lista = new ArrayList<>();
        lista.add(new Playlist("forro da Falarol", R.raw.forrodofarol_quincasmoreira));
        lista.add(new Playlist("sixseven", R.raw.sixseven));
        lista.add(new Playlist("cauntriboi", R.raw.cauntriboi));
        lista.add(new Playlist("lilysong", R.raw.lilysong));
        lista.add(new Playlist("waterlily", R.raw.waterlily));


        card1 = findViewById(R.id.card1);
        card1.setOnClickListener(this);
        card2 = findViewById(R.id.card2);
        card2.setOnClickListener(this);
        card3 = findViewById(R.id.card3);
        card3.setOnClickListener(this);
        card4 = findViewById(R.id.card4);
        card4.setOnClickListener(this);
        card5 = findViewById(R.id.card5);
        card5.setOnClickListener(this);
        textoMusicaSelecionada = findViewById(R.id.textView3);
        textoMusicaTocando = findViewById(R.id.textView2);

        imgPreview = findViewById(R.id.imageView);
        imgPreview.setOnClickListener(this);
        imgNext =  findViewById(R.id.imageView6);
        imgNext.setOnClickListener(this);






    }
    //___________________________________________________________________________________________________________________________

    public  boolean onOptionsItemSelected(MenuItem item) {  //ações dos icones e menus da toolbar
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
        }    //____________________________Runnable__________________________________________________________________________________________

        if (id == R.id.id001){ //se for o id 001 quer dizer que é para dar play
            play();

        }
        if (id == R.id.id003){
          stop();
        }

        if (id == R.id.id002){
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();{


                }
            }
        }
        return false;
    }
    //___________________________________________________________________________________________________________________________
    public boolean onCreateOptionsMenu(Menu menu) {//inflar o menu na tela 2
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }
    //___________________________________________________________________________________________________________________________
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        handler .removeCallbacks(this);
        mediaPlayer.release();
        mediaPlayer = null;
        seekbar.setProgress(0); //a bolinha volta para o inicio quando damos pare
        indiceLista++;
        if(indiceLista >= lista.size()){
            indiceLista = 0;
        }
        textoMusicaSelecionada.setText("Música Selecionada: "+lista.get(indiceLista).getNome());
        stop();
        play();
    }

    //___________________________SeekBar.OnSeekBarChangeListener__________________________________________________________________

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }
    //____________________________________________
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //____________________________________________

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { //metodo do controle da bolinha
        if (mediaPlayer!=null){
            mediaPlayer.seekTo((seekBar.getProgress()));
        }

    }
    //____________________________Runnable__________________________________________________________________________________________
    @Override
    public void run() {
        if (mediaPlayer!=null){
            seekbar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }

    }
    //____________________________View.OnClickListener______________________________________________________________________________

    @Override
    public void onClick(View view) {
        if (view == card1){
            indiceLista = 0;
            textoMusicaSelecionada.setText("musica Selecionada:"+lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }

        if (view == card2){
            indiceLista = 1;
            textoMusicaSelecionada.setText("musica Selecionada:"+lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }

        if (view == card3){
            indiceLista = 2;
            textoMusicaSelecionada.setText("musica Selecionada:"+lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }

        if (view == card4){
            indiceLista = 3;
            textoMusicaSelecionada.setText("musica Selecionada:"+lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }

        if (view == card5){
            indiceLista = 4;
            textoMusicaSelecionada.setText("musica Selecionada:"+lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }
        if(view == imgPreview){
            indiceLista--;
            if(indiceLista<0){
                indiceLista = lista.size()-1;
            }
            textoMusicaSelecionada.setText("Música Selecionada: "+lista.get(indiceLista).getNome());
            stop();
            play();
        }
        if(view == imgNext){
            indiceLista++;
            if(indiceLista >= lista.size()){
                indiceLista = 0;
            }
            textoMusicaSelecionada.setText("Música Selecionada: "+lista.get(indiceLista).getNome());
            stop();
            play();
        }
    }

public void play(){
    if (mediaPlayer == null) {
        mediaPlayer = MediaPlayer.create(this, lista.get(indiceLista).getMusica());
        textoMusicaTocando.setText("Musica Tocando:" + lista.get(indiceLista).getNome());
        mediaPlayer.setOnCompletionListener(this);
        seekbar.setMax(mediaPlayer.getDuration());
        handler.post(this);
        mediaPlayer.start();
    }else if (!mediaPlayer.isPlaying()){//se o mediaPlayer NÃO estiver tocando
        mediaPlayer.start();
        handler.post(this);
    }
}
public void stop(){
    if (mediaPlayer != null){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;

    }
}
}