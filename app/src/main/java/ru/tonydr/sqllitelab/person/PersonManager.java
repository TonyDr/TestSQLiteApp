package ru.tonydr.sqllitelab.person;

/**
 * Управление информацие о личности
 *
 * Created by Tony on 07.02.2016.
 */
public class PersonManager {

    private static PersonManager instance;

    private PersonManager() {}

    public static PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return  instance;
    }

    /**
     * Скрипт создания таблицы личности
     *
     * @return скрипт
     */
    public String getTableScript() {
        return "create table " + Person.TABLE_NAME
                + " (_id integer primary key autoincrement, "
                + Person.FIRST_NAME_FIELD + "TEXT,"
                + Person.SECOND_NAME_FIELD + " TEXT,"
                + Person.NOTE_FIELD + "TEXT)";
    }
}
