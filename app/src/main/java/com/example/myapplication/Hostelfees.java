package com.example.myapplication;

public class Hostelfees {



    String name,number,total,remaning,date;


    public Hostelfees() {
    }

    public Hostelfees(String name, String number, String total, String remaning, String date) {
        this.name = name;
        this.number = number;
        this.total = total;
        this.remaning = remaning;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemaning() {
        return remaning;
    }

    public void setRemaning(String remaning) {
        this.remaning = remaning;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
