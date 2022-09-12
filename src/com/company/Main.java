package com.company;
import DataBase.DataBase;

public class Main {
    public static void main(String[] args)  {
        DataBase dataBase = new DataBase();
        try {
            dataBase.commands();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
