package com.c196.exam.entities;

import java.time.Instant;
import java.util.ArrayList;

public class Course {
    private String title;
    private Instant start;
    private Instant end;
    private String instructorName;
    private String instructorEmail;
    private String instructorFName;
    private String instructorLName;
    private String instructorPhone;
    private ArrayList<Assessment> assessments;
}
