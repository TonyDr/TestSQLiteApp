package ru.tonydr.sqllitelab;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameFiled;
    private EditText surnameField;
    private EditText noteField;
    private AppSQLOpenHelper myHelper = null;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new AppSQLOpenHelper(this, "appDB", null, 1);
    }
}
