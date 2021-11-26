package org.sinou.android.sandbox.nav.basics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Basic fragment that display a dummy tree to experiment with back and home buttons.
 */
public class DummyTreeFragment extends Fragment {

    private static final String STATE_ID = "id";
    private static final String STATE_ITEMS = "items";

    private View fragmentRoot;

    private long mId;
    private ArrayList<String> mItems;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRoot = inflater.inflate(R.layout.fragment_dummy_tree, container, false);

        if (savedInstanceState != null) {
            mId = savedInstanceState.getLong(STATE_ID, 0);
        }

        // Get references to some views
        mListView = (ListView) fragmentRoot.findViewById(R.id.list_items);

        FloatingActionButton fab = fragmentRoot.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return fragmentRoot;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mItems = (ArrayList<String>) savedInstanceState.getSerializable(STATE_ITEMS);
        } else {
            mItems = createDummyValues();
        }

        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(mAdapter);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_ID, (int) (System.currentTimeMillis() / 1000000));
        outState.putSerializable(STATE_ITEMS, mItems);
    }

    private ArrayList<String> createDummyValues() {
        ArrayList<String> mItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mItems.add("List item #" + i);
        }
        return mItems;
    }
}

