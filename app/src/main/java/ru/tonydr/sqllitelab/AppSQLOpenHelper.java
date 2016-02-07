package ru.tonydr.sqllitelab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ru.tonydr.sqllitelab.person.PersonManager;

/**
 * Доступ к БД
 *
 * Created by Tony on 07.02.2016.
 */
public class AppSQLOpenHelper extends SQLiteOpenHelper {


    public AppSQLOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonManager.getInstance().getTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myLogs", "| Upgrade |" + db.toString());
    }
}
