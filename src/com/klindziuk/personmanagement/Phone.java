package com.klindziuk.personmanagement;

/**
 * Created by Hp on 23.02.2017.
 */
public class Phone {
    protected int id;
    protected int owner;
    protected String number;


    public Phone() {
    }
    public Phone(int id) {
        this.id = id;
    }

    public Phone(int id, int owner, String number) {
        this(owner, number);
        this.id = id;
    }

    public Phone(int owner, String number) {
        this.owner = owner;
        this.number = number;
            }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}


