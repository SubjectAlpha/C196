package com.c196.exam.database;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {}

    public static class TermTable implements BaseColumns {
        public static final String NAME = "Terms";
        public static final String TITLE_COLUMN = "title";
        public static final String START_COLUMN = "start";
        public static final String END_COLUMN = "end";
    }
}
