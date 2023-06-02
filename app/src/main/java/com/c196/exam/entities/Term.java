package com.c196.exam.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Term {
    private String title;
    private long start;
    private long end;

    private ArrayList<Course> courses;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
