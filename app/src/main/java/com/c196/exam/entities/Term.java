package com.c196.exam.entities;

import java.util.ArrayList;

public class Term {
    private String title;
    private long start;
    private long end;

    public Term() { }

    public Term(String termTitle, long termStart, long termEnd) {
        this.title = termTitle;
        this.start = termStart;
        this.end = termEnd;
    }

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
