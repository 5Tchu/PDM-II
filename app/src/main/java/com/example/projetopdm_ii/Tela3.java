package com.example.projetopdm_ii;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Tela3 extends AppCompatActivity {

    private ViewPager2 viewPager;

    private ArrayList<Slide> lista;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        texto = findViewById(R.id.textView7);
        viewPager = findViewById(R.id.viewPager);
       lista = new ArrayList<Slide>();
       lista.add(new Slide("Slide 1", R.drawable.zeni,"forró muito loko pra festa junina com os crias"));
       lista.add(new Slide("Slide 2", R.drawable.uzi,"escute se quer farmar aura+ego"));
       lista.add(new Slide("Slide 3", R.drawable.casa,"pelo jeito ela tá querendo ãanhm, fazendeirooow...elas se amarra no cowboy pq playboy não tem dinheiro, fazendeirooo"));
       lista.add(new Slide("Slide 4", R.drawable.toki,"lyli was a litlle girl...follow everywhere i go"));
       lista.add(new Slide("Slide 5", R.drawable.muza,"its time to go to the Erudito...for some funnn! yeap, its mee: LYLIAA litlle which"));
       SlideAdapter adapter = new SlideAdapter(lista, texto);
       viewPager.setAdapter(adapter);
    }
}
