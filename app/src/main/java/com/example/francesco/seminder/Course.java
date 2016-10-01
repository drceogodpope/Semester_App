package com.example.francesco.seminder;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

// A CLASS TO REPRESENT AN ABSTRACT COURSE INSTANCE

public class Course {
    private String teacher;
    private String title;
    private String courseCode;
    private String textbook;
    private DateTime startDate;
    private DateTime endDate;
    private DateTime currentDate;
    private LocalTime time;
    private int numberOfDays;


    public Course(String teacher, String title, String courseCode,DateTime startDate, DateTime endDate,LocalTime time){
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.teacher = teacher;
        this.title = title;
        this.courseCode = courseCode;
        currentDate = new DateTime();
        numberOfDays = parseDays(new Duration(currentDate,endDate).toStandardDays() );
    }

    public Course(String title, String courseCode, DateTime startDate, DateTime endDate, LocalTime time){
        this.title = title;
        this.courseCode = courseCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        currentDate = new DateTime();
        numberOfDays = parseDays(new Duration(currentDate,endDate).toStandardDays() );
        this.teacher = "";

    }

    public String getTeacher(){
        return teacher;
    }
    public String getTitle(){
        return title;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getTextbook(){
        return textbook;
    }
    public String getTime(){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
        return time.toString(fmt) ;
    }

    public static LocalTime makeLocalTime(String time){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
        return fmt.parseLocalTime(time);
    }

    public long getCurrentDateMillis() {
        return currentDate.getMillis();
    }
    public long getEndDateMillis() {
        return endDate.getMillis();
    }
    public long getStartDateMillis() {
        return startDate.getMillis();
    }

    public static int parseDays(Days days) {
        return Integer.parseInt(days.toString().substring(1, days.toString().length() - 1));
    }

    public void addTextbook(String textbook){
        this.textbook = textbook;
    }


    @Override
    public String toString() {
        return (
                "\n"+
                "Teacher: " + teacher +"\n" +
                "Title: " + title +"\n" +
                "Course Code: " + courseCode +"\n" +
                "Start Date: " + startDate +"\n" +
                "End Date: " + endDate +"\n" +
                "Number of Days: " + numberOfDays +"\n" +
                "Time: " + getTime() +"\n");
    }
}
