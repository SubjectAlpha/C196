package com.c196.exam.entities;

import java.time.Instant;
import java.util.ArrayList;

public class Course {
    private String title;
    private long start;
    private long end;
    private String status;
    private String instructorFirstName;
    private String instructorLastName;
    private String instructorEmail;
    private String instructorPhone;
    private ArrayList<Assessment> assessments;

    public enum Statuses {
        UNBEGUN,
        STARTED,
        COMPLETE
    }
}
