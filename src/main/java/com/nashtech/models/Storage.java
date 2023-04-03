package com.nashtech.models;

public class Storage {
    private int id;
    private String nameStorage;
    private double length;
    private double width;
    private double height;
    private String addressStorage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStorage() {
        return nameStorage;
    }

    public void setNameStorage(String nameStorage) {
        this.nameStorage = nameStorage;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getAddressStorage() {
        return addressStorage;
    }

    public void setAddressStorage(String addressStorage) {
        this.addressStorage = addressStorage;
    }
}
