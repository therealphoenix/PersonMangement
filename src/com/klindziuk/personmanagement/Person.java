package com.klindziuk.personmanagement;

import java.util.List;

/**
 * This is a model class represents a person entity
 *
 */
public class Person {
    protected int id;
    protected String name;
    protected String surname;
    protected String middlename;
    protected List<Phone> phoneList;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String name, String surname, String middlename) {
        this(name, surname, middlename);
        this.id = id;
    }

    public Person(String name, String surname, String middlename) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }


}
