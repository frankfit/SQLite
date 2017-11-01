package frank.com.sqllitelab;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathew on 24/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";
    //contacts
    private static final String TABLE_CONTACTS = " contacts";

    //Vehicle
    private static final String TABLE_VEHICLE = " vehicle";
    //contacts
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    //Vehicle
    private static final String KEY_SLOT_NO = "slot_number";
    private static final String KEY_MODEL = "model";
    private static final String KEY_NO_PLATE = "number_plate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_VEHICLE_TABLE = " CREATE TABLE " + TABLE_VEHICLE + "(" + KEY_SLOT_NO
                + " INTEGER PRIMARY KEY, " + KEY_MODEL + " TEXT, " + KEY_NO_PLATE + " TEXT " + ")";
        db.execSQL(CREATE_VEHICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_VEHICLE);
        onCreate(db);
    }

    //Adding table contacts
    public void addContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName()); //Contact name
        values.put(KEY_PH_NO, contacts.getPhoneNumber()); // contact phone number

        // Inserting row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //Closing db connection

    }

    // adding table VEHICLE
    public void addVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MODEL, vehicle.getModel()); //Contact name
        values.put(KEY_NO_PLATE, vehicle.getNumberPlate()); // contact phone number

        // Inserting row Vehicle
        db.insert(TABLE_VEHICLE, null, values);
        db.close(); //Closing db connection


    }

    // Getting single contact
    public Contacts getContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return contacts

        return contacts;
    }
    // Getting single vehicle
    public Vehicle getVehicle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VEHICLE, new String[]{KEY_SLOT_NO, KEY_MODEL, KEY_NO_PLATE}, KEY_SLOT_NO + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Vehicle vehicle = new Vehicle(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return vehicle

        return vehicle;
    }


    //Getting all contacts
    public List<Contacts> getAllContacts(){
        List<Contacts> contactsList = new ArrayList<Contacts>();
        //select a query
        String selectQuery ="SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Contacts contacts = new Contacts ();
                contacts.setID(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                //Adding contacts list
                contactsList.add(contacts);
            }while (cursor.moveToNext());
        }

        //RETURN CONTACT LIST
        return contactsList;


    }
    //Getting all vehicle
    public List<Vehicle> getAllVehicle(){
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        //select a query
        String selectQuery ="SELECT * FROM " + TABLE_VEHICLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Vehicle vehicle = new Vehicle ();
                vehicle.setSlotNumber(Integer.parseInt(cursor.getString(0)));
                vehicle.setModel(cursor.getString(1));
                vehicle.setNumberPlate(cursor.getString(2));
                //Adding contacts list
                vehicleList.add(vehicle);
            }while (cursor.moveToNext());
        }

        //RETURN vehicle LIST
        return vehicleList;


    }

    //getting contacts count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM "+ TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }
    //getting vehicle count
    public int getVehicleCount() {
        String countQuery = "SELECT * FROM "+ TABLE_VEHICLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }
    //Updating single contact
    public int updateContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhoneNumber());


        //UPDATING ROW
        return db.update(TABLE_CONTACTS, values, KEY_ID +"=?",
                new String[] {String.valueOf (contacts.getID()) });

    }
    //Updating single vehicle
    public int updateVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MODEL, vehicle.getModel());
        values.put(KEY_NO_PLATE, vehicle.getNumberPlate());

        //updating row
        return db.update(TABLE_VEHICLE, values, KEY_SLOT_NO +"=?",
                new String[] {String.valueOf (vehicle.getSlotNumber()) });

    }

    //Deleting single contact
    public void deleteContacts (ContactsContract.Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[]{String.valueOf(contacts.getID())});
        db.close();
    }

    //Deleting single vehicle
    public void deleteVehicle (com.example.mathew.sqlitelaba.Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLE, KEY_SLOT_NO + "=?",
                new String[]{String.valueOf(vehicle.getSlotNumber())});
        db.close();
    }


    public void addContact(contacts contacts) {
    }

    public List<contacts> getALLContacts() {
        return ALLContacts;
    }
}
