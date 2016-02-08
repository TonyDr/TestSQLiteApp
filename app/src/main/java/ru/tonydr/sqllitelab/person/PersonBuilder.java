package ru.tonydr.sqllitelab.person;

import ru.tonydr.sqllitelab.AppSQLOpenHelper;

/**
 * Билдер для создания информации о человеке
 *
 * Created by Tony on 08.02.2016.
 */
public final class PersonBuilder {

    /**
     * Наименование
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Примечание
     */
    private String note;


    private PersonBuilder(){}

    public static PersonBuilder person() {
        return new PersonBuilder();
    }

    /**
     * Установка имени
     *
     * @param pName имя
     * @return билдер
     */
    public PersonBuilder withName(String pName) {
        this.name = pName;
        return this;
    }

    /**
     * Установка фамилии
     *
     * @param pSurname фамилия
     * @return билдер
     */
    public PersonBuilder withSurname(String pSurname) {
        this.surname = pSurname;
        return this;
    }

    public PersonBuilder withNote(String pNote) {
        this.note = pNote;
        return this;
    }

    /**
     * Создание объекта в БД
     *
     * @param db бд
     * @return новый объект
     */
    public Person getNewOne(AppSQLOpenHelper db) {

        return PersonManager.getInstance(db).create(this);
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNote() {
        return note;
    }
}
