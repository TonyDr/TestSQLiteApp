package ru.tonydr.sqllitelab;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import java.util.List;

import ru.tonydr.sqllitelab.person.Person;
import ru.tonydr.sqllitelab.person.PersonBuilder;
import ru.tonydr.sqllitelab.person.PersonManager;

/**
 * Тест для управления данными о человеке
 *
 * Created by Tony on 08.02.2016.
 */
public class PersonManagerTest extends AndroidTestCase {

    AppSQLOpenHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new AppSQLOpenHelper(context, "appDB", null, 1);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    /**
     * Тест создания объекта в БД
     */
    public void testCreate() {
        PersonManager manager = PersonManager.getInstance(db);
        List<Person> personList = manager.getPersonList();
        Assert.assertNotNull(personList);
        int firstSize = personList.size();

        String name = "TestName";
        String surname = "TestSurname";
        String testNote = "TestNote";
        PersonBuilder builder = PersonBuilder.person()
                .withName(name)
                .withSurname(surname)
                .withNote(testNote);

        Person person = builder.getNewOne(db);
        Assert.assertNotNull(person);

        Person findValue = null;
        List<Person> personList2 = manager.getPersonList();
        Assert.assertEquals(firstSize + 1, personList2.size());
        for(Person person1 : personList2) {
            if (person.getId().equals(person1.getId())) {
                findValue = person1;
            }
        }
        Assert.assertNotNull(findValue);
        Assert.assertEquals(name, findValue.getName());
        Assert.assertEquals(surname, findValue.getSurname());
        Assert.assertEquals(testNote, findValue.getNote());


        manager.delete(findValue);
        Assert.assertEquals(firstSize, manager.getPersonList().size());
    }
}
