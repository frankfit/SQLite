package frank.com.sqllitelab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;


/**
 * Created by FRANK on 10/18/2017.
 */

public class AndroidSQLiteTutorialActivity extends Activity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         **/
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new contacts("Ravi", "9100000000"));
        db.addContact(new contacts("Srinivas", "9199999999"));
        db.addContact(new contacts("Tommy", "9522222222"));
        db.addContact(new contacts("Karthik", "9533333333"));

        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts ..");
        List<contacts> contacts = db.getALLContacts();

        for (contacts cn : contacts) {
            String log = "id: " + cn.getID() + " , Name: "+ cn.getName() + ",Phone: " + cn.getPhoneNumber();
            //  Writing Contacts  to log
            Log.d("Name: ", log);
        }
    }
}