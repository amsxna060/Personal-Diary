package com.amansiol.notes;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ListView helplist=(ListView)findViewById(R.id.helplist);
        ArrayList<String> helplistarray=new ArrayList<>();
        helplistarray.add("You Can add pages to write Messages or thoughts by tapping on Button on Home Screen.");
        helplistarray.add("Then You Have to Type Title of your page and then Start writing Message.");
        helplistarray.add("Then You Can Add your Page to Diary or Cancel if you are not intrested to save.");
        helplistarray.add("You can Slide the Card (with Title and date and time) to delete page from diary.");
        helplistarray.add("You Can Tap Any page to See your page in full Screen with Message(Full Screen page).");
        helplistarray.add("You Can long Press on Title on (Full Screen page) to Change the Title.");
        helplistarray.add("You Can long Press on Message on (Full Screen page) to Change the Message.");
        helplistarray.add("You Can Change your Passcode from menu.");
        helplistarray.add("Please Choose security answer very memorable they will help you in case you forget passcode.");
        helplistarray.add("you can only give one answer or more and left vacant other fields. and also leave those fields vacant in case you" +
                " forget passcode.");
        helplistarray.add("In Case You forget your passcode then click on (forget passcode) then answer the security question then " +
                "and you will allow to change your passcode.");
        helplistarray.add("*Trick* You Can Tap on emoji to change them and set them as your mood According to Day or your Message");
        helplistarray.add("You can See Developer information by clicking about me 5 times quickly on front screen.");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(Help.this,R.layout.cartofhelp,helplistarray);
        helplist.setAdapter(arrayAdapter);

    }
}
