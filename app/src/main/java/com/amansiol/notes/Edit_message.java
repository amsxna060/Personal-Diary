package com.amansiol.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Edit_message extends AppCompatActivity {
  public  int id;
    EditText activity_edit_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);

        Intent intent=getIntent();
        String message=intent.getStringExtra("message");
        id=intent.getIntExtra("_id",0);
        activity_edit_message= (EditText) findViewById(R.id.edit_message);
        activity_edit_message.setText(message);
        activity_edit_message.requestFocus();
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder backfromedit=new AlertDialog.Builder(Edit_message.this);
        backfromedit.setTitle("Save!!");
        backfromedit.setMessage("Are you Want to Save ?");
        backfromedit.setIcon(R.drawable.ic_error_outline_black_24dp);
        backfromedit.setCancelable(false);

        backfromedit.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               dialog.dismiss();
            }
        });
        backfromedit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Edit_message.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backfromedit.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NotesDatabase db=new NotesDatabase(Edit_message.this);
                db.UpdateMessage(id,activity_edit_message.getText().toString());
                Intent intent=new Intent(Edit_message.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        final AlertDialog alertDialogbackedit=backfromedit.create();
        alertDialogbackedit.show();
    }
}
