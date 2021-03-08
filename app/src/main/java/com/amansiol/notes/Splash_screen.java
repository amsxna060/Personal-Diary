package com.amansiol.notes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Splash_screen extends Activity {
  VideoView splashvideo;
  TextView createdby;
  int count=5;
  int count1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_splash_screen);
        final SharedPreferences sharedPreferences = getSharedPreferences("AmolPassCode", MODE_PRIVATE);
        splashvideo = (VideoView) findViewById(R.id.vedio);
        splashvideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.splashdiary));
        splashvideo.start();
        createdby = (TextView) findViewById(R.id.createdBy);
        final Handler splash = new Handler();
        final Runnable run=new Runnable() {
            @Override
            public void run() {
                if (!sharedPreferences.getBoolean("NewPassActivity", true)) {
                    Intent i = new Intent(Splash_screen.this, PassCodeActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(Splash_screen.this, NewPassCodeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        createdby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    Toast.makeText(getApplicationContext(), "Welcome to Developer Information..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Splash_screen.this, About_Me.class);
                    startActivity(i);
                    finish();
                    splash.removeCallbacks(run);
                    return;
                } else if(count>=0){
                    Toast.makeText(getApplicationContext(), "you are " + count + " steps away from Developer Information", Toast.LENGTH_SHORT).show();
                    --count;
                    return;
                }
            }
        });

            splash.postDelayed(run,5000);


    }


}
