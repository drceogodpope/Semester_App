package com.example.francesco.seminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textshit);

        Course test1 = new Course("shit","fys101",new DateTime(),new DateTime().plusWeeks(2),new LocalTime(5,30));

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),1,2);
//        dbHelper.insertCourse(test2);
        dbHelper.insertCourse(test1);

        Course dbTest1 = dbHelper.getCourse(dbHelper.getWritableDatabase(),"shit");
//        Course dbTest2 = dbHelper.getCourse(dbHelper.getWritableDatabase(),"ass");


        System.out.print("HERE"  + dbTest1.toString());
//        System.out.print(dbTest2.toString());

        System.out.print(dbHelper.getTableAsString(dbHelper.getWritableDatabase(),"courses"));


    }



}