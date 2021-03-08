package com.amansiol.notes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextPaint;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PassCodeActivity extends Activity {
TextView iary,wrongpasscode,forgetpasscode;
ImageView image1,image2,image3,image4,lock;
Button button1;
Button button2;
Button button3;
Button button4;
Button button5;
Button button6;
Button button7;
Button button8;
Button button9;
Button button0;
Handler handler;
Runnable runnable;
Handler handler1;
Runnable runnable1;
char p='\0',a='\0',s1='\0',s2='\0';
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_pass_code);

        final int status[]={R.drawable.ic_radio_button_unchecked_black_24dp,R.drawable.ic_radio_button_checked_black_24dp};

        handler=new Handler();
         runnable=new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1.setImageResource(status[1]);
                        image2.setImageResource(status[0]);
                    }
                },100);
                image1.setImageResource(status[0]);
                image2.setImageResource(status[1]);

                    handler.postDelayed(this, 200);

            }
        };
        handler1=new Handler();
        runnable1=new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image3.setImageResource(status[1]);
                        image4.setImageResource(status[0]);
                    }
                },100);
                int count=0;
                image3.setImageResource(status[0]);
                image4.setImageResource(status[1]);

                    handler1.postDelayed(this,200);

            }
        };
        iary=(TextView)findViewById(R.id.iary);
        TextPaint paint2 = iary.getPaint();
        float width2 = paint2.measureText("Tiahi");
        Shader textShader2 = new LinearGradient(0, 0, width2, iary.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        iary.getPaint().setShader(textShader2);
        image1=(ImageView)findViewById(R.id.image1);
        image2=(ImageView)findViewById(R.id.image2);
        image3=(ImageView)findViewById(R.id.image3);
        image4=(ImageView)findViewById(R.id.image4);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        lock=(ImageView)findViewById(R.id.lock);
        wrongpasscode=(TextView)findViewById(R.id.wrongpasscode);
        forgetpasscode=(TextView)findViewById(R.id.forgetpasscode);
        forgetpasscode.setText(Html.fromHtml("<u>Forget PassCode?</u>"));
        wrongpasscode.setText("Wrong PassCode. Try Again.");
        forgetpasscode.setAlpha(0);
        wrongpasscode.setAlpha(0);
        image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        forgetpasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginByEmail.class);
                startActivity(intent);
                finish();
            }
        });
        final SharedPreferences passcode = getSharedPreferences("AmolPassCode", MODE_PRIVATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='1';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='1';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='1';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='1';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {   lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                 StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='2';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='2';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='2';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='2';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='3';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='3';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='3';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='3';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='4';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='4';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='4';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='4';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='5';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='5';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='5';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='5';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='6';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='6';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='6';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='6';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='7';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='7';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='7';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='7';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='8';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='8';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='8';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='8';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='9';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='9';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='9';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='9';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p=='\0')
                {
                    p='0';
                    image1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(a=='\0')
                {
                    a='0';
                    image2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s1=='\0')
                {
                    s1='0';
                    image3.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                }else
                if(s2=='\0')
                {
                    s2='0';
                    image4.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    Animate();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(p==passcode.getString("newpassCode","1").charAt(0)&&a==passcode.getString("newpassCode","1").charAt(1)
                                    &&s1==passcode.getString("newpassCode","1").charAt(2)
                                    &&s2==passcode.getString("newpassCode","1").charAt(3))
                            {
                                lock.setImageResource(R.drawable.ic_lock_open_black_24dp);
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i=new Intent(PassCodeActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },1000);
                            }else {
                                p='\0';
                                a='\0';
                                s1='\0';
                                s2='\0';
                                Toast.makeText(PassCodeActivity.this,"wrong passCode",Toast.LENGTH_LONG).show();
                                StopAnimation();
                                image1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                image4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                                forgetpasscode.setAlpha(1);
                                wrongpasscode.setAlpha(1);
                                return;
                            }
                        }
                    },3000);

                }

            }
        });
    }

    void Animate(){

            handler.post(runnable);
            handler1.post(runnable1);
    }
    void StopAnimation()
    {
        handler1.removeCallbacks(runnable1);
        handler.removeCallbacks(runnable);

    }
}
