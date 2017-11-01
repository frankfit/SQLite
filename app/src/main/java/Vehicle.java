package com.example.mathew.sqlitelaba;


/**
 * Created by Mathew on 24/10/2017.
 */

public class Vehicle {
    int _slot_number;
    String _model;
    String _number_plate;

    public Vehicle() {

    }


    public Vehicle(int _slot_number, String model, String _number_plate) {

        this._slot_number = _slot_number;
        this._model = model;
        this._number_plate = _number_plate;
    }

    public Vehicle(String _model, String _number_plate) {
        this._model = _model;
        this._number_plate = _number_plate;
    }

    public int getSlotNumber() {
        return this._slot_number;
    }

    public void setSlotNumber(int slot_number) {

        this._slot_number = slot_number;

    }

    public String getModel() {
        return this._model;
    }

    public void setModel(String model) {
        this._model = model;
    }

    public String getNumberPlate() {
        return this._number_plate;
    }

    public void setNumberPlate(String number_plate) {
        this._number_plate = number_plate;
    }
}
