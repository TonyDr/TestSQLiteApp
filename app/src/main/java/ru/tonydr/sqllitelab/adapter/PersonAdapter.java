package ru.tonydr.sqllitelab.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.tonydr.sqllitelab.R;
import ru.tonydr.sqllitelab.person.Person;

/**
 * Адаптер для отображения информации о личности
 *
 * Created by Tony on 08.02.2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    /**
     * Список
     */
    private final List<Person> list;

    /**
     * Контекст
     */
    private final Activity context;


    public PersonAdapter(Activity context, List<Person> list) {
        super(context, R.layout.person_row_layout, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.person_row_layout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.surname = (TextView) view.findViewById(R.id.surname);

            view.setTag(viewHolder);

        } else {
            view = convertView;

        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getName());
        holder.surname.setText(list.get(position).getSurname());
        return view;
    }

    static class ViewHolder {
        protected TextView name;
        protected TextView surname;
    }
}
