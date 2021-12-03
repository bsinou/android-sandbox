package org.sinou.android.sandbox.orientation.basics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * This shows how to handle life cycle when having a simple list. Thanks to https://medium.com/hootsuite-engineering/handling-orientation-changes-on-android-41a6b62cb43f
 */
public class ListFragment extends Fragment {

    private static final String STATE_ID = "id";
    private static final String STATE_ITEMS = "items";

    private long mId;
    private ArrayList<String> mItems;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore some state before we have even inflated our own layout
            // This could be generic things like an ID that our Fragment represents
            mId = savedInstanceState.getLong(STATE_ID, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);

        // Get references to some views
        mListView = (ListView) root.findViewById(R.id.list_items);

        if (savedInstanceState != null) {
            // Restore some state right after inflating our layout
            // Note: Our views haven't had their states restored yet
        }
        return root;
    }

    @SuppressWarnings("unchecked" )
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore some state that needs to happen after the Activity was created
            //
            // Note #1: Our views haven't had their states restored yet
            // This could be a good place to restore a ListView's contents (and it's your last
            // opportunity if you want your scroll position to be restored properly)
            //
            // Note #2:
            // The following line will cause an unchecked type cast compiler warning
            // It's impossible to actually check the type because of Java's type erasure:
            //      At runtime all generic types become Object
            // So the best you can do is add the @SuppressWarnings("unchecked") annotation
            // and understand that you must make sure to not use a different type anywhere
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

