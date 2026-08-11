package com.example.projetopdm_ii;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela02 extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, Runnable{
    private Toolbar toolbar;
    private MediaPlayer mediaPlayer;

    private SeekBar seekBar;

    private Handler handler;


    //___________________________________________________________________________________________________________________________

    @SuppressLint("MissingInflatedId")
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
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        handler = new Handler();

    }
    //___________________________________________________________________________________________________________________________

    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
        }
        if(id == R.id.id001){   //id001 = dar play
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this, R.raw.forrodofarol_quincasmoreira);
                mediaPlayer.setOnCompletionListener(this);
                seekBar.setMax(mediaPlayer.getDuration());
                handler.post(this);
                mediaPlayer.start();
            }else if (!mediaPlayer.isPlaying()){  //se mediaplayer NÃO estiver tocando
                mediaPlayer.start();
            }
        }
        if(id == R.id.id003) {    //id003 = parar kkk
            if(mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }
        if(id == R.id.id002){
            if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
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
        mediaPlayer.release();
        mediaPlayer = null;
        seekBar.setProgress(0); // a bolinha volta pro início quando damos "pare"

    }
    //____________________________SEEK BAR_______________________________________________________________________________________________
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }
    //___________________________________________________________________________________________________________________________
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //___________________________________________________________________________________________________________________________
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(mediaPlayer!=null) {
            mediaPlayer.seekTo((seekBar.getProgress()));
        }
    }
    //________________________________________RUNNABLE___________________________________________________________________________________
    @Override
    public void run() {
        if(mediaPlayer!= null)
        {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }

    }
}