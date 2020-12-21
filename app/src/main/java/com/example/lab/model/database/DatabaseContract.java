package com.example.lab.model.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static class Parking implements BaseColumns {
        public static final String TABLE_NAME = "parking";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_PARKING_ID = "parking_id";
        public static final String COLUMN_NAME_CITY_ID = "city_id";
        public static final String COLUMN_NAME_CITY_IMAGE = "image";
    }

    public static class Cities implements BaseColumns {
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_NAME_CITY="city";
        public static final String COLUMN_NAME_CITY_ID="city_id";
        public static final String COLUMN_NAME_CITY_IMAGE="image";
    }
    public static class Reservations implements BaseColumns {
        public static final String TABLE_NAME = "reservations";
        public static final String COLUMN_NAME_CITY="city";
        public static final String COLUMN_NAME_CITY_ID="city_id";
        public static final String COLUMN_NAME_PARKING_ID = "parking_id";
        public static final String COLUMN_NAME_RES_ID="reservation_id";
    }

    //TODO Da se dopisat tabeli za drugite baranja.
}
