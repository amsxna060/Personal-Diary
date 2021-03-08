package com.amansiol.notes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTaskAdaptor extends SimpleAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<HashMap<String,String>> hashMapArrayList=new ArrayList<>();
    public MyTaskAdaptor(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context=context;
        hashMapArrayList= (ArrayList<HashMap<String, String>>) data;
        inflater=LayoutInflater.from(context);
    }

  public void remove(int position){
        hashMapArrayList.remove(position);
        notifyDataSetChanged();
  }

    @Override
    public int getCount() {
        return super.getCount();
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view=super.getView(position, convertView, parent);
        final ImageView imageView=view.findViewById(R.id.smile);
        final TextView titleView=view.findViewById(R.id.Title);
        final NotesDatabase db=new NotesDatabase(context);

        imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int[] image={R.drawable.ic_cake_black_24dp,R.drawable.ic_mood_bad_black_24dp,R.drawable.ic_mood_black_24dp
               ,R.drawable.ic_sentiment_dissatisfied_black_24dp,R.drawable.ic_sentiment_neutral_black_24dp,R.drawable.ic_sentiment_satisfied_black_24dp
               ,R.drawable.ic_sentiment_very_satisfied_black_24dp};
               int max = 6;
               int min = 0;
               int range = max - min + 1;
               int rand = (int)(Math.random() * range) + min;
                imageView.setImageResource(image[rand]);
             db.UpdateEmoji(Integer.parseInt(hashMapArrayList.get(position).get("_id")),MainActivity.image[rand]);
           }
       });

        return view;
    }
}
