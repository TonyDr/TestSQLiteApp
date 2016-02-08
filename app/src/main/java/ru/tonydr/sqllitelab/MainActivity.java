package ru.tonydr.sqllitelab;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import ru.tonydr.sqllitelab.adapter.PersonAdapter;
import ru.tonydr.sqllitelab.person.Person;
import ru.tonydr.sqllitelab.person.PersonManager;

public class MainActivity extends ListActivity {

    private AppSQLOpenHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        dbHelper = new AppSQLOpenHelper(this, "appDB", null, 1);

        ArrayAdapter<Person> listAdapter = new PersonAdapter(this,
                PersonManager.getInstance(dbHelper).getPersonList());
        setListAdapter(listAdapter);

    }


    public void onCreateButtonClick(View view) {
        Intent i = new Intent(MainActivity.this, InputPersonActivity.class);
        startActivity(i);
    }


}
