package im.dino.dbview.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import im.dino.dbview.R;

/**
 * Created by dino on 23/02/14.
 */
public class DatabaseListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ActionBarActivity) {

            ActionBarActivity activity = (ActionBarActivity) getActivity();

            activity.getSupportActionBar().setTitle("Databases");
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        List<String> databaseList = new ArrayList<>();

        for (String database : getActivity().databaseList()) {
            if (!database.endsWith("-journal")) {
                databaseList.add(database);
            }
        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                databaseList);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager fm = getActivity().getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,
                TableListFragment.newInstance((String) getListAdapter().getItem(position)));
        ft.addToBackStack(null).commit();

        fm.executePendingTransactions();
    }
}
