package com.example.myapplication;

public class Allocate {


    String department,sname,total,free,allocated,bed;

    public Allocate() {
    }

    public Allocate(String department, String sname, String total, String free, String allocated, String bed) {
        this.department = department;
        this.sname = sname;
        this.total = total;
        this.free = free;
        this.allocated = allocated;
        this.bed = bed;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }
}
