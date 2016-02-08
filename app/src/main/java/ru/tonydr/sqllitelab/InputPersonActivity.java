package ru.tonydr.sqllitelab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ru.tonydr.sqllitelab.person.Person;
import ru.tonydr.sqllitelab.person.PersonBuilder;
import ru.tonydr.sqllitelab.person.PersonManager;

/**
 * Активность для ввода информации о человеке
 */
public class InputPersonActivity extends AppCompatActivity {

    /**
     * Поле имени
     */
    private TextView name;

    /**
     * Поле фамилии
     */
    private TextView surname;

    /**
     * Примечание
     */
    private TextView note;

    /**
     * БД
     */
    private AppSQLOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_field);
        dbHelper = new AppSQLOpenHelper(this, "appDB", null, 1);
        name = (TextView) findViewById(R.id.name_field);
        surname = (TextView) findViewById(R.id.surname_field);
        note = (TextView) findViewById(R.id.note_field);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Long personId = extras.getLong("id");
            Person person = PersonManager.getInstance(dbHelper).getPerson(personId);
            name.setText(person.getName());
            surname.setText(person.getSurname());
            note.setText(person.getNote());
        }
    }

    public void onDeleteButtonClick(View view) {
    }

    public void onCreatePersonButtonClick(View view) {
        if ("".equals(name.getText().toString())) {
            Toast.makeText(getApplicationContext(), getString(R.string.name_required),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(surname.getText().toString())) {
            Toast.makeText(getApplicationContext(), getString(R.string.surname_required),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            PersonBuilder.person()
                    .withName(name.getText().toString())
                    .withSurname(surname.getText().toString())
                    .withNote(note.getText().toString())
                    .getNewOne(dbHelper);
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        } else {
            Long personId = bundle.getLong("id");
            Person person = PersonManager.getInstance(dbHelper).getPerson(personId);
            person.setName(name.getText().toString());
            person.setSurname(surname.getText().toString());
            person.setNote(note.getText().toString());
            PersonManager.getInstance(dbHelper).update(person);
        }

    }
}
