package com.example.lab .model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.lab.R;
import com.example.lab.model.database.DatabaseContract.Parkings;
import com.example.lab.model.database.DatabaseContract.Reservations;
import com.example.lab.model.pojo.City;
import com.example.lab.model.pojo.Parking;
import com.example.lab.model.pojo.Rezervacija;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Database {

    private Context mContext;
    private ParkingDbHelper dbHelper;
    private SQLiteDatabase database;

    public Database(Context context) {
        this.mContext = context;
        dbHelper = new ParkingDbHelper(context);
    }


    public void insertUser(String username, String password) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.COLUMN_NAME_USERNAME, username);
        values.put(DatabaseContract.User.COLUMN_NAME_PASSWORD, password);

// Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(DatabaseContract.User.TABLE_NAME, null, values);
        database.close();
    }

    public boolean checkUser(String username, String password) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.User.COLUMN_NAME_USERNAME,
                DatabaseContract.User.COLUMN_NAME_PASSWORD
        };

        String selection = DatabaseContract.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = database.query(
                DatabaseContract.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.COLUMN_NAME_PASSWORD));
            if (pass.equals(password))
                return true;
            else
                return false;
        }
        return false;
    }

    public void insertCities() {
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Skopje");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.skopje);
        long newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Ohrid");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.ohrid);
        newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Bitola");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.bitola);
        newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Prilep");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.prilep);
        newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Tetovo");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.tetovo);
        newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Strumica");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.strumica);
        newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);

        // Insert the new row, returning the primary key value of the new row
        database.close();
        // return Integer.parseInt(DatabaseContract.Cities.COLUMN_NAME_CITY_ID);
    }

    public ArrayList<City> getCities() {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from CITIES", null);

        ArrayList<City> cities = new ArrayList<>();
        while (cursor.moveToNext()){
            City city = new City();
            city.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Cities.COLUMN_NAME_CITY)));
            city.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE)));
            cities.add(city);
        }
        cursor.close();
        database.close();
        return cities;
    }

    public void insertParkings() {
        database = dbHelper.getWritableDatabase();
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Mavrovka', 'Skopje', '41.9994427', '21.4371476' ,'5')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('City Mall', 'Skopje','42.0055005', '21.3925705', '2')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Shirok Sokak', 'Bitola','41.5212283', '20.8325921', '3')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Biljanini Izvori', 'Ohrid','41.1106272', '20.8053991', '4')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Tinex', 'Prilep','41.3429904', '21.5505254', '2')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Vero', 'Tetovo','41.9994842', '20.9564608', '4')");
        database.execSQL("insert into " + Parkings.TABLE_NAME +"(parking_name, city_name, parking_lat, parking_long, total_mesta)" + "VALUES " + "('Global', 'Strumica','41.4392177', '22.6370162','2')");
    }

    public ArrayList<Parking> getParkings(String cityName, String mTimeslot) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                Parkings.COLUMN_NAME_PARKING_NAME,
                Parkings.COLUMN_NAME_PARKING_ID,
                Parkings.COLUMN_NAME_CITY_NAME,
                Parkings.COLUMN_NAME_TOTAL_MESTA,
                Parkings.COLUMN_NAME_PARKING_LATITUDE,
                Parkings.COLUMN_NAME_PARKING_LONGITUDE
        };

        String selection = Parkings.COLUMN_NAME_CITY_NAME + " = ?";
        String[] selectionArgs = {cityName};

        Cursor cursor = database.query(
                Parkings.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<Parking> parkings = new ArrayList<>();
        while (cursor.moveToNext()){
            Parking parking = new Parking();
            parking.setName(cursor.getString(cursor.getColumnIndexOrThrow(Parkings.COLUMN_NAME_PARKING_NAME)));
            parking.setCity(cursor.getString(cursor.getColumnIndexOrThrow(Parkings.COLUMN_NAME_CITY_NAME)));
            parking.setKapacitet(cursor.getInt(cursor.getColumnIndexOrThrow(Parkings.COLUMN_NAME_TOTAL_MESTA)));
            parking.setLatitude(cursor.getString(cursor.getColumnIndexOrThrow(Parkings.COLUMN_NAME_PARKING_LATITUDE)));
            parking.setLongitude(cursor.getString(cursor.getColumnIndexOrThrow(Parkings.COLUMN_NAME_PARKING_LONGITUDE)));
            int reservedSlots = getReservedSlots(parking.getName(), mTimeslot);

            parking.setSlobodni(parking.getKapacitet() - reservedSlots);
            parking.setZafateni(reservedSlots);

            parkings.add(parking);
        }
        cursor.close();
        database.close();
        return parkings;
    }

    private int getReservedSlots(String name, String mTimeslot) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                Reservations.COLUMN_NAME_RESERVATION_ID
        };

        String selection = Reservations.COLUMN_NAME_PARKING_NAME + " = ? AND " + Reservations.COLUMN_NAME_RESERVATION_TIMESLOT + " = ?";
        String[] selectionArgs = {name, mTimeslot};

        Cursor cursor = database.query(
                Reservations.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor.getCount();
    }

    public long insertReservation(Rezervacija rezervacija) {
        database = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Reservations.COLUMN_NAME_USER_NAME, rezervacija.getImeKorisnik());
        cv.put(Reservations.COLUMN_NAME_PARKING_NAME, rezervacija.getImeParking());
        cv.put(Reservations.COLUMN_NAME_CITY, rezervacija.getImeGrad());
        cv.put(Reservations.COLUMN_NAME_RESERVATION_TIMESLOT, rezervacija.getTimeslot());


        long newRowId = database.insert(Reservations.TABLE_NAME, null, cv);
        Log.i("TAG", "" + newRowId);
        database.close();
        return newRowId;
    }

    public ArrayList<Rezervacija> getMyReservations () {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from RESERVATIONS", null);

        ArrayList<Rezervacija> myreservations = new ArrayList<>();
        while (cursor.moveToNext()){
            Rezervacija myres = new Rezervacija();
            myres.setImeKorisnik(cursor.getString(cursor.getColumnIndexOrThrow(Reservations.COLUMN_NAME_USER_NAME)));
            myres.setImeParking(cursor.getString(cursor.getColumnIndexOrThrow(Reservations.COLUMN_NAME_PARKING_NAME)));
            myres.setImeGrad(cursor.getString(cursor.getColumnIndexOrThrow(Reservations.COLUMN_NAME_CITY)));
            myres.setTimeslot(cursor.getString(cursor.getColumnIndexOrThrow(Reservations.COLUMN_NAME_RESERVATION_TIMESLOT)));
            myreservations.add(myres);
        }
        cursor.close();
        database.close();
        return myreservations;
        };

    public Location getParkingLocation(String imeParking) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                Parkings.COLUMN_NAME_PARKING_LATITUDE,
                Parkings.COLUMN_NAME_PARKING_LONGITUDE
        };

        String selection = Parkings.COLUMN_NAME_PARKING_NAME + " = ?";
        String[] selectionArgs = {imeParking};

        Cursor cursor = database.query(
                Parkings.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToFirst();

        Location location = new Location("");
        location.setLatitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Parkings.COLUMN_NAME_PARKING_LATITUDE))));
        location.setLongitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Parkings.COLUMN_NAME_PARKING_LONGITUDE))));

        return location;
    }
}






