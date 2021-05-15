package com.example.soundpool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Initialize the button
    private Button button1,button2,button3;
    //Initialize SoundPool
    private SoundPool soundPool;
    int sound1,sound2,sound3,sound4;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        //Setting up Audio Attributes
        AudioAttributes audioAttributes=new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).build();
        //Setting up Sound Pool
        soundPool=new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();
        //Loading up the Sounds
        sound1=soundPool.load(this,R.raw.complete,1);
        sound2=soundPool.load(this,R.raw.defeat_one,1);
        sound3=soundPool.load(this,R.raw.defeat_two,1);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                soundPool.play(sound1,1,1,0,0,1);
                break;
            case R.id.button2:
                soundPool.play(sound2,1,1,0,0,1);
                break;
            case R.id.button3:
                soundPool.play(sound3,1,1,0,0,1);
                break;
        }
    }
    //Over ride OnDestroy to remove the soundpool to save memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(soundPool!=null){
            soundPool.release();
            soundPool=null;
        }
    }
}