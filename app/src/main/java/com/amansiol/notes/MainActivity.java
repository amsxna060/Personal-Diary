package com.amansiol.notes;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.amansiol.notes.colors.Colors;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
   FloatingActionButton fab;
   SwipeMenuListView listViewOfTask;
   EditText dilogtitle;
   EditText dilogmessage;
   MyTaskAdaptor myTaskAdaptor;
    public static int[] image={R.drawable.ic_cake_black_24dp,R.drawable.ic_mood_bad_black_24dp,R.drawable.ic_mood_black_24dp
            ,R.drawable.ic_sentiment_dissatisfied_black_24dp,R.drawable.ic_sentiment_neutral_black_24dp,R.drawable.ic_sentiment_satisfied_black_24dp
            ,R.drawable.ic_sentiment_very_satisfied_black_24dp};
   ArrayList<HashMap<String,String>> arrayListoflistview=new ArrayList<>();
//   final SharedPreferences Colors=getSharedPreferences("com.amansiol.notes.Colors",MODE_PRIVATE);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.help:
                Intent help=new Intent(MainActivity.this,Help.class);
                startActivity(help);
                break;
            case R.id.changepass:
                Intent intent = new Intent(getApplicationContext(), NewPassCodeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewOfTask=(SwipeMenuListView) findViewById(R.id.listoftasks);
        loadContent();
        listViewOfTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NotesDatabase db=new NotesDatabase(MainActivity.this);
                Bundle bundle = new Bundle();
                Task task=db.getRowbyId(Integer.parseInt(arrayListoflistview.get(position).get("_id")));
                    bundle.putInt("_id",task.getId());
                    bundle.putString("title", task.getTitle());
                    bundle.putString("message",task.getMessage() );
                    bundle.putString("date",task.getDate() );
                    bundle.putString("week",task.getWeek() );
                    bundle.putString("time", task.getTime());
                    bundle.putInt("smile", task.getSmile());


                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        fab= (FloatingActionButton) findViewById(R.id.fabaddnotes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addnotes=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                View view=inflater.inflate(R.layout.takingnotesdialog,null);
                addnotes.setCancelable(false);
                addnotes.setView(view);
                dilogtitle=view.findViewById(R.id.NotesTitle);
                dilogmessage= view.findViewById(R.id.noteshere);
                final AlertDialog alertDialog= addnotes.create();
                alertDialog.show();
                dilogtitle.requestFocus();
                view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                view.findViewById(R.id.Addnotes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dilogtitle.getText().toString().length()>50||dilogtitle.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this,"Sorry Title Cross Word limit!!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        DateFormat df=new SimpleDateFormat("EEE");
                        Calendar calobj=Calendar.getInstance();
                        String week=df.format(calobj.getTime());
                        df=new SimpleDateFormat("MMM dd , yyyy");
                        String date=df.format(calobj.getTime());
                        df=new SimpleDateFormat("hh:mm aa");
                        String diary_time=df.format(calobj.getTime());


                        Task temptask;
                        temptask=new Task(dilogtitle.getText().toString(),dilogmessage.getText().toString(),date,week,diary_time,image[getSmilerandom()]);
                        NotesDatabase notesDatabase=new NotesDatabase(MainActivity.this);
                        notesDatabase.insertTask(temptask);
                        loadContent();
                        alertDialog.dismiss();
                    }
                });
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(R.color.colorMainbackground);
                deleteItem.setWidth(170);
                deleteItem.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(deleteItem);
                SwipeMenuItem shareItem=new SwipeMenuItem(getApplicationContext());
                shareItem.setBackground(R.color.colorMainbackground);
                shareItem.setWidth(170);
                shareItem.setIcon(R.drawable.ic_share_black_24dp);
                menu.addMenuItem(shareItem);

            }
        };

        listViewOfTask.setMenuCreator(creator);

        listViewOfTask.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        NotesDatabase db=new NotesDatabase(MainActivity.this);
                        db.Delete(Integer.parseInt(arrayListoflistview.get(position).get("_id")));
                        loadContent();
                        break;
                    case 1:
                        Intent share=new Intent(Intent.ACTION_SEND);
                        share.putExtra(Intent.EXTRA_TEXT,arrayListoflistview.get(position).get("message"));
                        share.setType("text/plain");
                        if (share.resolveActivity(getPackageManager()) != null) {
                            startActivity(share);
                        }
                }
                return false;
            }
        });

    }

    public void loadContent()
    {
        arrayListoflistview.clear();
        NotesDatabase notesDatabase=new NotesDatabase(MainActivity.this);
        Cursor cursor=notesDatabase.getAll();
        while(cursor.moveToNext()){
            HashMap<String,String> temphashmap=new HashMap<>();
            temphashmap.put("title",cursor.getString(1));
            temphashmap.put("message",cursor.getString(2));
            temphashmap.put("date",cursor.getString(3));
            temphashmap.put("week",cursor.getString(4));
            temphashmap.put("time",cursor.getString(5));
            temphashmap.put("smile",String.valueOf(cursor.getInt(6)));
            temphashmap.put("_id",String.valueOf(cursor.getInt(0)));
            arrayListoflistview.add(temphashmap);
        }
        String from[]={"title","date","week","time","smile"};
        int [] to={R.id.Title,R.id.date,R.id.week,R.id.cardTime,R.id.smile};
        myTaskAdaptor=new MyTaskAdaptor(getApplicationContext(),arrayListoflistview,R.layout.cardoflist,from,to);
        listViewOfTask.setAdapter(myTaskAdaptor);
    }
    public int getSmilerandom(){
        int max = 6;
        int min = 0;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        return rand;
    }
//    public void enableDarkMode()
//    {
//        com.amansiol.notes.colors.Colors colors=new Colors();
//        colors.setMainBackground(R.color.DarkActBG);
//        colors.setDateWeekTime(R.color.DarkWDT);
//        colors.setTitleColor(R.color.DarkTitle);
//        colors.setFabButton(R.color.DarkFabButton);
//        colors.setActivityBackground(R.color.DarkActBG);
//        colors.setActivityMessage(R.color.DarkActMsg);
//        colors.setActivityDateWeekTime(R.color.DarkActMsg);
//        colors.setActivityMessageBackground(R.color.DarkActBG);
//        colors.setActivityTitleColor(R.color.DarkActTitle);
//        SharedPreferences.Editor Darkmode=Colors.edit();
//        Darkmode.putInt("DarkmainBG"    ,colors.getMainBackground());
//        Darkmode.putInt("DarkWDT"       ,colors.getDateWeekTime());
//        Darkmode.putInt("DarkTitle"     ,colors.getTitleColor());
//        Darkmode.putInt("DarkFabButton" ,colors.getFabButton());
//        Darkmode.putInt("DarkActBG"     ,colors.getActivityBackground());
//        Darkmode.putInt("DarkActMsg"    ,colors.getActivityMessage());
//        Darkmode.putInt("DarkActBGmsg"  ,colors.getActivityMessageBackground());
//        Darkmode.putInt("DarkActTitle"  ,colors.getActivityTitleColor());
//        Darkmode.putInt("DarkActWDT"    ,colors.getActivityDateWeekTime());
//        Darkmode.commit();
//    }
//    public void enableDefaultMode()
//    {
//        com.amansiol.notes.colors.Colors colors=new Colors();
//        colors.setMainBackground(R.color.colorMainbackground);
//        colors.setDateWeekTime(R.color.WDTcolors);
//        colors.setTitleColor(R.color.colorTitle);
//        colors.setFabButton(R.color.WDTcolors);
//        colors.setActivityBackground(R.color.colorMainbackground);
//        colors.setActivityMessage(Color.BLACK);
//        colors.setActivityDateWeekTime(R.color.WDTcolors);
//        colors.setActivityMessageBackground(R.color.colorMainbackground);
//        colors.setActivityTitleColor(R.color.colorTitle);
//        SharedPreferences.Editor defaultmode=Colors.edit();
//        defaultmode.putInt("defaultmainBG",colors.getMainBackground());
//        defaultmode.putInt("defaultWDT",colors.getDateWeekTime());
//        defaultmode.putInt("defaultTitle",colors.getTitleColor());
//        defaultmode.putInt("defaultFabButton",colors.getFabButton());
//        defaultmode.putInt("defaultActBG",colors.getActivityBackground());
//        defaultmode.putInt("defaultActMsg",colors.getActivityMessage());
//        defaultmode.putInt("defaultActBGmsg",colors.getActivityMessageBackground());
//        defaultmode.putInt("defaultActTitle",colors.getActivityTitleColor());
//        defaultmode.putInt("defaultActWDT",colors.getActivityDateWeekTime());
//        defaultmode.commit();
//    }


}
