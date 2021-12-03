package org.sinou.android.sandbox.nav.basics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple list that display items, with an option menu and search feature.
 */
public class DummyListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String STATE_ID = "id";
    private static final String STATE_ITEMS = "items";

    private View fragmentRoot;

    private long mId;
    private final ArrayList<String> allItems;
    private ArrayList<String> items;

    private ListView listView;
    private MyAdapter adapter;

    public DummyListFragment() {
        // Necessary to have a custom menu only specific to this fragment
        setHasOptionsMenu(true);
        allItems = createDummyValues();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mId = savedInstanceState.getInt(STATE_ID, 0);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        fragmentRoot = inflater.inflate(R.layout.fragment_dummy_list, container, false);
        listView = (ListView) fragmentRoot.findViewById(R.id.list_items);
        fragmentRoot.findViewById(R.id.fab)
                .setOnClickListener(view ->
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show());

        setHasOptionsMenu(true);

        return fragmentRoot;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            items = (ArrayList<String>) savedInstanceState.getSerializable(STATE_ITEMS);
        } else {
            items = new ArrayList<>();
            items.addAll(allItems);
        }

        adapter = new MyAdapter(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore some state that needs to happen after our own views have had
            // their state restored
            // DON'T try to restore ListViews here because their scroll position will
            // not be restored properly
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.more_menu, menu);


        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        // See https://www.youtube.com/watch?v=qzbvDJqXeJs
        // super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_ID, (int) (System.currentTimeMillis() / 1000000));
        outState.putSerializable(STATE_ITEMS, items);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase(Locale.ROOT);
        List<String> newList = new ArrayList<>();

        for (String item : allItems) {
            if (item.toLowerCase(Locale.ROOT).contains(userInput)) {
                newList.add(item);
            }
        }
        adapter.updateList(newList);
        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        public void updateList(List<String> newList) {
            ArrayList<String> items = new ArrayList<>();
            items.addAll(newList);
            super.clear();
            super.addAll(items);
            notifyDataSetChanged();
        }
    }

    private ArrayList<String> createDummyValues() {

        ArrayList<String> mItems = new ArrayList<>();
        for (String name : androidNames) {
            mItems.add(name);
        }
//        for (String name : ubuntuNames) {
//            mItems.add(name);
//        }

        return mItems;
    }

    // Simply use some well known names for our tests
    private final static String[] androidNames = new String[]{
            "Cupcake (1.5)",
            "Donut (1.6)",
            "Eclair (2.0, 2.1)",
            "Froyo (2.2)",
            "Gingerbread (2.3)",
            "Honeycomb (3.0)",
            "Ice Cream Sandwich (4.0)",
            "Jelly Bean (4.1, 4.2, 4.3)",
            "KitKat (4.4)",
            "Lollipop (5.x)",
            "Marshmallow (6.x)",
            "Nougat (7.x)",
            "Oreo (8.x)",
            "Pie (9.0)",
            "Q (10)",
            "R (11)"
    };

    private final static String[] ubuntuNames = new String[]{
            "Impish Indri (21.10)",
            "Hirsute Hippo (21.04)",
            "Groovy Gorilla (20.10)",
            "Focal Fossa (20.04)",
            "Eoan Ermine (19.10)",
            "Disco Dingo (19.04)",
            "Cosmic Cuttlefish (18.10)",
            "Bionic Beaver (18.04)",
            "Artful Aardvark (17.10)",
            "Zesty Zapus (17.04)",
            "Yakkety Yak (16.10)",
            "Xenial Xerus (16.04)",
            "Wily Werewolf (15.10)",
            "Vivid Vervet (15.04)",
            "Utopic Unicorn (14.10)",
            "Trusty Tahr (14.04)",
            "Saucy Salamander (13.10)",
            "Raring Ringtail (13.04)",
            "Quantal Quetzal (12.10)",
            "Precise Pangolin (12.04)",
            "Oneiric Ocelot (11.10)",
            "Natty Narwal (11.04)",
            "Maverick Meerkat (10.10)",
            "Lucid Lynx (10.04)",
            "Karmic Koala (9.10)",
            "Jaunty Jackalope (9.04)",
            "Intrepid Ibex (8.10)",
            "Hardy Heron (8.04)",
            "Gutsy Gibbon (7.10)",
            "Fiesty Fawn (7.04)",
            "Edgy Eft (6.10)",
            "Dapper Drake (6.06)",
            "Breezy Badger (5.10)",
            "Hoary Hedgehog (5.04)",
            "Warty Warthog (4.10)",
    };

}

