package com.example.test.controller;
public class Flag {
    protected static boolean managerToString = false;
    protected static boolean customer = false;
    protected static boolean equipment = false;
    protected static boolean place = false;
    protected static boolean customerToString = false;
    protected static boolean equipmentToString = false;
    protected static boolean placeToString = false;
    protected static boolean applicationToString = false;
    protected static boolean brigadeToString = false;
    protected static boolean typeOfWorkToString = false;
    protected static boolean appForMatToString = false;
    protected static boolean warehouseToString = false;
    protected static boolean reportToString = false;
    protected static boolean admin = false;
    protected static boolean storekeeper = false;
    protected static boolean manager = false;
    protected static boolean planToString = false;
    public static boolean isManagerToString() {
        return managerToString;
    }
    public static boolean isCustomerToString(){
        return customerToString;
    }
    public static boolean isEquipmentToString(){
        return equipmentToString;
    }
    public static boolean isPlaceToString() {
        return placeToString;
    }
    public static boolean isApplicationToString() {
        return applicationToString;
    }
    public static boolean isBrigadeToString() {
        return brigadeToString;
    }
    public static boolean isTypeOfWorkToString() {
        return typeOfWorkToString;
    }
    public static boolean isAppForMatToString() {
        return appForMatToString;
    }
    public static boolean isReportToString() {
        return reportToString;
    }
    public static boolean isPlanToString() {
        return planToString;
    }
    public static boolean isWarehouseToString() {
        return warehouseToString;
    }
    public static void isAll(){
        customerToString = false;
        equipmentToString = false;
        placeToString = false;
    }
}


