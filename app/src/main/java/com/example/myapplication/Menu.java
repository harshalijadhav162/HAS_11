package com.example.myapplication;

public class Menu {

    String name,number,address,menuone,menutwo,menuthree,imageurl;

    public Menu() {
    }

    public Menu(String name, String number, String address, String menuone, String menutwo, String menuthree, String imageurl) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.menuone = menuone;
        this.menutwo = menutwo;
        this.menuthree = menuthree;
        this.imageurl = imageurl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMenuone() {
        return menuone;
    }

    public void setMenuone(String menuone) {
        this.menuone = menuone;
    }

    public String getMenutwo() {
        return menutwo;
    }

    public void setMenutwo(String menutwo) {
        this.menutwo = menutwo;
    }

    public String getMenuthree() {
        return menuthree;
    }

    public void setMenuthree(String menuthree) {
        this.menuthree = menuthree;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
