package com.klindziuk.personmanagement;

/**
 * This is a model class represents a phone entity
 *
 */
public class Phone {
    protected int id;
    protected int owner;
    protected String number;

    public Phone(int id) {
        this.id = id;
    }

    public Phone(int id, int owner, String number) {
        this(id);
        this.owner = owner;
        this.number = number;
    }

    public Phone(int owner, String number) {
        this(0,owner,number);
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


