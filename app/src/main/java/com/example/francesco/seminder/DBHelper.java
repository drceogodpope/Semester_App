package com.example.francesco.seminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DataBase.db";

    public static final String COURSES_TABLE_NAME = "courses";
    public static final String COURSES_COLUMN_ID = "_id";
    public static final String COURSES_COLUMN_TITLE = "title";
    public static final String COURSES_COLUMN_COURSECODE = "courseCode";
    public static final String COURSES_COLUMN_STARTDATE = "startDate";
    public static final String COURSES_COLUMN_ENDDATE = "endDate";
    public static final String COURSES_COLUMN_TIME = "time";

    public static final String RESOURCES_TABLE_NAME = "resources";
    public static final String RESOURCES_COLUMN_ID = "id";
    public static final String RESOURCES_COLUMN_TITLE = "title";

    public static final String TESTS_TABLE_NAME = "tests";
    public static final String TESTS_COLUMN_ID = "id";
    public static final String TESTS_COLUMN_PERCENTAGE = "percentage";
    public static final String TESTS_COLUMN_DATE = "date";
    public static final String TESTS_COLUMN_TITLE = "title";



    public DBHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table courses " + "(" + COURSES_COLUMN_ID +" integer primary key, " + COURSES_COLUMN_TITLE + " text, "+COURSES_COLUMN_COURSECODE+" text, "+ COURSES_COLUMN_STARTDATE +" INTEGER, " + COURSES_COLUMN_ENDDATE + " INTEGER, "+ COURSES_COLUMN_TIME+" TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS resources");
        db.execSQL("DROP TABLE IF EXISTS tests");
        onCreate(db);
    }

    public boolean insertCourse  (Course course){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", course.getTitle());
        contentValues.put("courseCode", course.getCourseCode());
        contentValues.put("startDate", course.getStartDateMillis());
        contentValues.put("endDate", course.getEndDateMillis());
        contentValues.put("time", course.getTime());
        db.insert("courses", null, contentValues);
        return true;
    }

    public Course getCourse(SQLiteDatabase db,String title){
        String titleArg;
        String courseCode;
        long startDate;
        long endDate;
        LocalTime time;

        ArrayList<Object> args = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT "+ COURSES_COLUMN_TITLE+", " + COURSES_COLUMN_COURSECODE +", "+ COURSES_COLUMN_STARTDATE+", " +COURSES_COLUMN_ENDDATE+", " + COURSES_COLUMN_TIME +" FROM " + COURSES_TABLE_NAME + " WHERE " +COURSES_COLUMN_TITLE+" = " +title,null);
        if(c.moveToFirst()){
            String[] columnNames = c.getColumnNames();
           titleArg = c.getString(c.getColumnIndex(columnNames[0]));
           courseCode = c.getString(c.getColumnIndex(columnNames[1]));
            startDate = c.getLong(c.getColumnIndex(columnNames[2]));
            endDate = c.getLong(c.getColumnIndex(columnNames[3]));
            time = Course.makeLocalTime(c.getString(c.getColumnIndex(columnNames[4])));
            c.close();
            return  new Course(titleArg,courseCode,new DateTime(startDate),new DateTime(endDate),time);
        }
        System.out.print("getCourse fucked up");
        return null;
    }

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        allRows.close();
        return tableString;
    }


}



