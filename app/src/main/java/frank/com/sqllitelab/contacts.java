package frank.com.sqllitelab;

/**
 * Created by FRANK on 10/17/2017.
 */

public class contacts {
    //private variables

    int _id;
    String _name;
    String _phone_number;

    // Empty constructor
    public contacts () {

    }

    //constructor
    public contacts (int id, String name, String _phone_number){
        this. _id = id;
        this. _name = name;
        this. _phone_number = _phone_number;

    }
    //constructor
    public contacts (String name, String _phone_number) {
        this._name = name;
        this._phone_number = _phone_number;

    }

    // getting ID
    public int getID () {
        return this._id;

    }

    //setting id
    public void setID(int id){
        this._id = id;

    }

    //getting name
    public String getName(){
        return this._name;

    }

    //setting name
    public void  setName(String name){
        this._name = name;

    }
    //getting phone number
    public String get_phone_number() {
        return _phone_number;
    }

    //setting phone number
    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}



