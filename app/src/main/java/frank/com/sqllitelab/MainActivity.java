package frank.com.sqllitelab;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import frank.com.sqllitelab.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert:", "Inserting...");
        db.addContacts(new contacts("Ravi", "9100000000"));
        db.addContacts(new contacts("Srinivas", "9199999999"));
        db.addContacts(new Contacts("Tommy", "9522222222"));
        db.addContacts(new Contacts("Karthik", "9533333333"));

        //vehicle
        Log.d("Insert:", "Inserting...");
        db.addVehicle(new Vehicle("Toyota", "91000"));
        db.addVehicle(new Vehicle("Nissan", "91999"));
        db.addVehicle(new Vehicle("Mazda", "952222"));
        db.addVehicle(new Vehicle("Volkswagen", "953333"));

        Log.d("Reading: ", "Reading all contacts.. ");
        List<Contacts> contacts = db.getAllContacts();
        for (Contacts cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            Log.d("Name:", log);
        }

        //vehicle

        Log.d("Reading: ", "Reading all Vehicles.. ");
        List<frank.com.sqllitelab.Vehicle> vehicle = db.getAllVehicle();
        for (Vehicle vh : vehicle) {
            String log = "SlotNo: " + vh.getSlotNumber() + " ,Model: " + vh.getModel() + " ,NumberPlate: " + vh.getNumberPlate();
            Log.d("Model:", log);
        }
    }
}
