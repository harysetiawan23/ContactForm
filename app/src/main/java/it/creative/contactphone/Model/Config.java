package it.creative.contactphone.Model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by agush on 10/4/2017.
 */

public class Config {
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 6;

    // Database Name
    public static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    public static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    public static final String CONTACT_ID = "contact_id";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_PHONE = "contact_phone";
    public static final String CONTACT_EMAIL = "contact_email";
    public static final String CONTACT_SEX = "contact_sex";
    public static final String CONTACT_HOBBIES = "contact_hobbies";
    public static final String CONTACT_BEAUTY = "contact_beauty";
    public static final String CONTACT_IMAGE = "contact_img_uri";
    private static Random rnd = new Random();

    public static int getRandomNumber() {
        StringBuilder sb = new StringBuilder(20);
        for(int i=0; i < 10; i++)
            sb.append((char)('0' + rnd.nextInt(10)));

        int value = Integer.parseInt(sb.toString());
        return value;
    }

    public static int randInt() {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((20 - 10) + 1) + 10;

        return randomNum;
    }
}
