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

    public static class Parkings implements BaseColumns {
        public static final String TABLE_NAME = "parking";
        public static final String COLUMN_NAME_PARKING_NAME = "parking_name";
        public static final String COLUMN_NAME_PARKING_ID = "parking_id";
        public static final String COLUMN_NAME_CITY_NAME = "city_name";
        public static final String COLUMN_NAME_TOTAL_MESTA = "total_mesta";
        public static final String COLUMN_NAME_PARKING_LATITUDE = "parking_lat";
        public static final String COLUMN_NAME_PARKING_LONGITUDE = "parking_long";
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
        public static final String COLUMN_NAME_PARKING_ID = "parking_id";
        public static final String COLUMN_NAME_PARKING_NAME = "parking_name";
        public static final String COLUMN_NAME_RESERVATION_TIMESLOT = "parking_reservation_timeslot";
        public static final String COLUMN_NAME_RESERVATION_ID = "parking_reservation_id";
        public static final String COLUMN_NAME_USER_NAME = "parking_user_name";

    }

}
