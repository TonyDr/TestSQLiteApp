package ru.tonydr.sqllitelab.person;

/**
 * Информация о личности
 *
 * Created by Tony on 07.02.2016.
 */
public class Person {

    static final String TABLE_NAME="first_table";
    static final String FIRST_NAME_FIELD ="first_name";
    static final String SECOND_NAME_FIELD ="second_name";
    static final String NOTE_FIELD ="note";

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
}
