package com.amansiol.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginByEmail extends AppCompatActivity {
    EditText firstSchool,Nickname,Name;
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_email);
        firstSchool=(EditText)findViewById(R.id.firstSchool);
        Nickname=(EditText)findViewById(R.id.Nicknamelover);
        Name=(EditText)findViewById(R.id.YourFavName);
        Submit=(Button)findViewById(R.id.Submit);
        Nickname.requestFocus();
        Nickname.setShowSoftInputOnFocus(true);
        final SharedPreferences sharedPreferences=getSharedPreferences("AmolPassCode", MODE_PRIVATE);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getString("firstSchool","").compareToIgnoreCase(firstSchool.getText().toString())==0&&
                        sharedPreferences.getString("Nicknamelover","").compareToIgnoreCase(Nickname.getText().toString())==0&&
                        sharedPreferences.getString("FavName","").compareToIgnoreCase(Name.getText().toString())==0){
                       Intent intent = new Intent(getApplicationContext(), NewPassCodeActivity.class);
                       startActivity(intent);
                       finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry But Now It Seems to be impossible to Open Diary",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
