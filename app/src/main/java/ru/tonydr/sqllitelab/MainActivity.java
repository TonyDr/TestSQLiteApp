package ru.tonydr.sqllitelab;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ru.tonydr.sqllitelab.adapter.PersonAdapter;
import ru.tonydr.sqllitelab.person.Person;
import ru.tonydr.sqllitelab.person.PersonManager;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        AppSQLOpenHelper dbHelper = new AppSQLOpenHelper(this, "appDB", null, 1);

        ArrayAdapter<Person> listAdapter = new PersonAdapter(this,
                PersonManager.getInstance(dbHelper).getPersonList());
        setListAdapter(listAdapter);


    }

    /**
     * Действие по нажатию на кнопку создать
     *
     * @param view вью
     */
    public void onCreateButtonClick(View view) {
        Intent i = new Intent(MainActivity.this, InputPersonActivity.class);
        startActivity(i);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // По короткому нажатие показываем примечание
        Person person = (Person) getListView().getItemAtPosition(position);
        Toast.makeText(getApplicationContext(),
                person.getNote(),
                Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Person person = (Person) getListView().getItemAtPosition(position);
        Bundle dataBundle = new Bundle();
        dataBundle.putLong("id", person.getId());

        Intent intent = new Intent(getApplicationContext(), InputPersonActivity.class);

        intent.putExtras(dataBundle);
        startActivity(intent);
        return true;
    }
}
