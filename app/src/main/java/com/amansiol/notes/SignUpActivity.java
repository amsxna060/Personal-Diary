package com.amansiol.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
EditText firstSchool,Nickname,Name;
Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstSchool=(EditText)findViewById(R.id.firstSchool);
        Nickname=(EditText)findViewById(R.id.Nicknamelover);
        Name=(EditText)findViewById(R.id.YourFavName);
        Submit=(Button)findViewById(R.id.Submit);
        final SharedPreferences sharedPreferences=getSharedPreferences("AmolPassCode", MODE_PRIVATE);
        Nickname.requestFocus();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor secureedit=sharedPreferences.edit();
                secureedit.putString("firstSchool",firstSchool.getText().toString());
                secureedit.putString("Nicknamelover",Nickname.getText().toString());
                secureedit.putString("FavName",Name.getText().toString());
                secureedit.commit();
                Intent intent = new Intent(getApplicationContext(), PassCodeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
