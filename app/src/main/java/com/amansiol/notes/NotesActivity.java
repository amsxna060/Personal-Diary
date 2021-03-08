package com.amansiol.notes;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {
    TextView title;
    TextView date;
    TextView week;
    TextView message;
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
         title   = (TextView) findViewById(R.id.Activitytitle);
         date   = (TextView) findViewById(R.id.activitydate);
         week   = (TextView) findViewById(R.id.activityweek);
         message = (TextView) findViewById(R.id.Activitymessage);
         time   = (TextView) findViewById(R.id.activitytime);
        final ImageView smile = (ImageView)findViewById(R.id.Activitysmile);
        final NotesDatabase db=new NotesDatabase(NotesActivity.this);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
            date.setText(bundle.getString("date"));
            week.setText(bundle.getString("week"));
            message.setText(bundle.getString("message"));
            time.setText(bundle.getString("time"));
            smile.setImageResource(bundle.getInt("smile"));
        }
        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] image={R.drawable.ic_cake_black_24dp,R.drawable.ic_mood_bad_black_24dp,R.drawable.ic_mood_black_24dp
                        ,R.drawable.ic_sentiment_dissatisfied_black_24dp,R.drawable.ic_sentiment_neutral_black_24dp,R.drawable.ic_sentiment_satisfied_black_24dp
                        ,R.drawable.ic_sentiment_very_satisfied_black_24dp};
                int max = 6;
                int min = 0;
                int range = max - min + 1;
                int rand = (int)(Math.random() * range) + min;
                smile.setImageResource(image[rand]);
                db.UpdateEmoji(bundle.getInt("_id"),MainActivity.image[rand]);
            }
        });
       title.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               AlertDialog.Builder edit_title_builder=new AlertDialog.Builder(NotesActivity.this);
               LayoutInflater inflater=LayoutInflater.from(NotesActivity.this);
               View view=inflater.inflate(R.layout.edit_title,null);
               edit_title_builder.setCancelable(true);
               edit_title_builder.setView(view);
               final EditText editText=view.findViewById(R.id.edit_title);
               final AlertDialog alertDialog= edit_title_builder.create();
               alertDialog.show();
               view.findViewById(R.id.edit_title_okay).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(editText.getText().toString().length()>50||editText.getText().toString().isEmpty())
                       {
                           Toast.makeText(NotesActivity.this,"Sorry Title Cross Word limit!!",Toast.LENGTH_LONG).show();
                           return;
                       }else {

                           db.UpdateTitle(bundle.getInt("_id"),editText.getText().toString());
                           alertDialog.dismiss();
                           title.setText(editText.getText().toString());
                       }

                   }
               });
               return true;
           }
       });
       message.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               Intent i=new Intent(NotesActivity.this,Edit_message.class);
               i.putExtra("message",bundle.getString("message"));
               i.putExtra("_id",bundle.getInt("_id"));
               startActivity(i);
               finish();
               return true;
           }
       });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NotesActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
