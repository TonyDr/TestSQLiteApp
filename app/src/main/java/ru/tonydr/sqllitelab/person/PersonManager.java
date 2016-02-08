package ru.tonydr.sqllitelab.person;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.tonydr.sqllitelab.AppSQLOpenHelper;

/**
 * Управление информацие о человеке
 * <p/>
 * Created by Tony on 07.02.2016.
 */
public class PersonManager {

    private static PersonManager instance;

    private AppSQLOpenHelper dbHelper;

    /**
     * Конструктор менеджера
     *
     * @param db база данных
     */
    private PersonManager(AppSQLOpenHelper db) {
        dbHelper = db;
    }


    public static PersonManager getInstance(AppSQLOpenHelper db) {
        if (instance == null) {
            instance = new PersonManager(db);
        }
        return instance;
    }

    /**
     * Скрипт создания таблицы личности
     *
     * @return скрипт
     */
    public String getTableScript() {
        return "create table " + Person.TABLE_NAME
                + " (_id integer primary key autoincrement, "
                + Person.FIRST_NAME_FIELD + " TEXT,"
                + Person.SURNAME_NAME_FIELD + " TEXT,"
                + Person.NOTE_FIELD + " TEXT)";
    }

    /**
     * Получения списка людей
     *
     * @return список людей
     */
    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = dbHelper.getReadableDatabase().query(Person.TABLE_NAME,
                    new String[]{Person.ID_FIELD, Person.FIRST_NAME_FIELD,
                            Person.SURNAME_NAME_FIELD, Person.NOTE_FIELD},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setId(cursor.getLong(cursor.getColumnIndex(Person.ID_FIELD)));
                person.setName(cursor.getString(cursor.getColumnIndex(Person.FIRST_NAME_FIELD)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(Person.SURNAME_NAME_FIELD)));
                person.setNote(cursor.getString(cursor.getColumnIndex(Person.NOTE_FIELD)));
                personList.add(person);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return personList;
    }

    /**
     * Вставка новой информации о человеке
     *
     * @param personBuilder билдер
     * @return информация
     */
    public Person create(PersonBuilder personBuilder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Person person = new Person();
        person.setName(personBuilder.getName());
        person.setSurname(personBuilder.getSurname());
        person.setNote(personBuilder.getNote());

        person.setId(db.insert(Person.TABLE_NAME, null, person.getContentValues()));
        return person;
    }

    /**
     * Удаление записи
     *
     * @param person информация о человеке
     */
    public void delete(Person person) {
        dbHelper.getWritableDatabase().delete(Person.TABLE_NAME,
                Person.ID_FIELD + " = ? ",
                new String[]{person.getId().toString()});
    }
}
