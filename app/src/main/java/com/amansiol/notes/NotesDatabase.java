package com.amansiol.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NotesDatabase extends SQLiteOpenHelper {
   private String DATABASE_NAME="com.amansiol.notes.NOTES.db";
   private int DATABASE_VERSION=1;
   final private String DATABASE_TABLE_NAME="NOTES";
   final private String ID="_ID";
   final private String TITLE_NAME="TITLE_NAME";
   final private String WORK_NAME= "WORK_NAME" ;
   final private String DATE=      "DATE"      ;
   final private String WEEK=      "WEEK"      ;
   final private String TIME=    "TIME";
   final private String SMILE= "SMILE";
   Context context;

    public NotesDatabase(Context context) {
        super(context,"com.amansiol.notes.NOTES.db",null,1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+DATABASE_TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE_NAME+" TEXT, "+WORK_NAME+" TEXT, "+DATE+" TEXT, "+WEEK+" TEXT, "+
                        TIME+" TEXT, "+SMILE+" INT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE_NAME);
             onCreate(db);
    }
    public Boolean insertTask(Task task)
    {   SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("TITLE_NAME",task.getTitle());
        contentValues.put("WORK_NAME",task.getMessage());
        contentValues.put("DATE",task.getDate());
        contentValues.put("WEEK",task.getWeek());
        contentValues.put("TIME",task.getTime());
        contentValues.put("SMILE",task.getSmile());
        db.insert(DATABASE_TABLE_NAME,null,contentValues);
        return true;
    }
    Cursor getAll(){
           SQLiteDatabase db=this.getReadableDatabase();
           Cursor res=db.rawQuery("SELECT * FROM "+DATABASE_TABLE_NAME,null);
           return res;
    }

    void Delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DATABASE_TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});

    }
    void UpdateTitle(int id,String title)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLE_NAME,title);
        db.update(DATABASE_TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(id)});
    }
    void UpdateMessage(int id,String Message)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(WORK_NAME,Message);
        db.update(DATABASE_TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(id)});
    }
    void UpdateEmoji(int id,int emoji_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SMILE,emoji_id);
        db.update(DATABASE_TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(id)});
    }
    public Task getRowbyId(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = null;
        Task temp=new Task();
        try {
             res= db.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME + " WHERE " + ID + "=?", new String[]{String.valueOf(id)});
        }catch (Exception e)
        {
            Toast.makeText(context,e+"",Toast.LENGTH_SHORT).show();
        }
        if(res!=null)
        {   res.moveToFirst();
            temp.setId(res.getInt(0));
            temp.setTitle(res.getString(1));
            temp.setMessage(res.getString(2));
            temp.setDate(res.getString(3));
            temp.setWeek(res.getString(4));
            temp.setTime(res.getString(5));
            temp.setSmile(res.getInt(6));
        }
        return temp;
    }

}
