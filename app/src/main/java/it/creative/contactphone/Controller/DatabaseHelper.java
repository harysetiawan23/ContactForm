package it.creative.contactphone.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.creative.contactphone.Model.Config;
import it.creative.contactphone.Model.Contact;

import static it.creative.contactphone.Model.Config.TABLE_CONTACTS;

/**
 * Created by agush on 10/4/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        Log.d("STATUS ","Database on excute");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Config.TABLE_CONTACTS + " ( " +
                Config.CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Config.CONTACT_NAME + " TEXT," +
                Config.CONTACT_EMAIL + " TEXT," +
                Config.CONTACT_PHONE + " TEXT," +
                Config.CONTACT_IMAGE + " TEXT," +
                Config.CONTACT_BEAUTY + " TEXT,"+
                Config.CONTACT_SEX+" TEXT,"+
                Config.CONTACT_HOBBIES+" TEXT "+
                ")";
        db.execSQL(CREATE_TABLE);
        Log.d("STATUS ","Tabel Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
        Log.d("STATUS ","Tabel on upgraded");
    }

    // Adding new contact
    public void addContact(Contact contact) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Config.CONTACT_ID, contact.getContact_id()); // Contact ID
            values.put(Config.CONTACT_NAME, contact.getContact_name()); // Contact Name
            values.put(Config.CONTACT_EMAIL, contact.getContact_email());// Contact Email
            values.put(Config.CONTACT_PHONE, contact.getContact_phone());// Contact Phone
            values.put(Config.CONTACT_IMAGE, contact.getContact_img_uri());// Contact Image
            values.put(Config.CONTACT_SEX,contact.getContact_sex());//Contact Sex
            values.put(Config.CONTACT_BEAUTY,contact.getContact_beauty());//Contact Beauty
            values.put(Config.CONTACT_HOBBIES,contact.getContact_hobbies());//Contact Hobbies

            // Inserting Row
            db.insert(TABLE_CONTACTS, null, values);
            db.close(); // Closing database connection
            Log.d("STATUS TAMBAH ","SUKSES" );
        }catch (Exception e){
            Log.d("STATUS TAMBAH ",e.getMessage() );
        }

    }

    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                        Config.CONTACT_ID,
                        Config.CONTACT_NAME,
                        Config.CONTACT_EMAIL,
                        Config.CONTACT_PHONE,
                        Config.CONTACT_IMAGE,
                        Config.CONTACT_SEX,
                        Config.CONTACT_HOBBIES,
                        Config.CONTACT_BEAUTY
                }, Config.CONTACT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
                ,Integer.parseInt(cursor.getString(0)));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Config.TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContact_id(Integer.parseInt(cursor.getString(0)));
                contact.setContact_name(cursor.getString(1));
                contact.setContact_email(cursor.getString(2));
                contact.setContact_phone(cursor.getString(3));
                contact.setContact_img_uri(cursor.getString(4));
                contact.setContact_beauty(cursor.getString(5));
                contact.setContact_sex(cursor.getString(6));
                contact.setContact_hobbies(cursor.getString(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Config.CONTACT_ID, contact.getContact_id()); // Contact ID
        values.put(Config.CONTACT_NAME, contact.getContact_name()); // Contact Name
        values.put(Config.CONTACT_EMAIL, contact.getContact_email());// Contact Email
        values.put(Config.CONTACT_PHONE, contact.getContact_phone());// Contact Phone
        values.put(Config.CONTACT_IMAGE, contact.getContact_img_uri());// Contact Image
        values.put(Config.CONTACT_HOBBIES,contact.getContact_hobbies());//Contact Hobbies
        values.put(Config.CONTACT_SEX,contact.getContact_sex());//Contact Sex
        values.put(Config.CONTACT_BEAUTY,contact.getContact_beauty());//Contact Beauty


        // updating row
        return db.update(TABLE_CONTACTS, values, Config.CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getContact_id()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, Config.CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getContact_id()) });
        db.close();
    }
}
