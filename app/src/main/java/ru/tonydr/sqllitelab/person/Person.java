package ru.tonydr.sqllitelab.person;

import android.content.ContentValues;

/**
 * Информация о человеке
 *
 * Created by Tony on 07.02.2016.
 */
public class Person {

    static final String TABLE_NAME="first_table";
    static final String ID_FIELD = "_id";
    static final String FIRST_NAME_FIELD ="first_name";
    static final String SURNAME_NAME_FIELD ="second_name";
    static final String NOTE_FIELD ="note";

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Примечания
     */
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
       /* if (id != null) {
            contentValues.put(Person.ID_FIELD, getId());
        }*/
        contentValues.put(Person.FIRST_NAME_FIELD, getName());
        contentValues.put(Person.SURNAME_NAME_FIELD, getSurname());
        contentValues.put(Person.NOTE_FIELD, getNote());
        return contentValues;
    }


}
