package org.sinou.android.sandbox.nav.basics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * Experiment with latest {@link RecyclerView}
 */
public class RecyclerFragment extends Fragment {

    private static final String TAG = "BrowserViewGroup";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private NavController navController;

    // TODO make this dependant of screen width
    private static final int SPAN_COUNT = 4;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected View rootView;
    protected RecyclerView mView;
    protected RecyclerListItemAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    // Holds the list of items that is currently displayed and has been loaded from the local cache
    private Item[] items;

    // Also hold a reference to the parent State to ease update?
    private Item parentItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add dummy data
        setState(new Item("Test"));
    }

    /**
     * Tell the browser about the current Session. This method **must** be called
     * between onCreate() and onStart() life cycle hooks.
     *
     * @return true if session has changed
     */
    public boolean setState(@NonNull Item state) {
        boolean hasChanged = false;

        if (!state.equals(parentItem)) {
            updateParentState(state);
            hasChanged = true;
        }

        parentItem = state;
        return hasChanged;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        rootView.setTag(TAG);

        mView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        navController = NavHostFragment.findNavController(this);
        mAdapter = new RecyclerListItemAdapter(navController, parentItem, items);

        // Set CustomAdapter as the adapter for RecyclerView.
        mView.setAdapter(mAdapter);

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mView.setLayoutManager(mLayoutManager);
        mView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     *
     * @param state
     * @return true if the parent has changed
     */
    private boolean updateParentState(@NonNull Item state) {

        if (state.equals(parentItem)){
            return false;
        }

        parentItem = state;
        items = Item.getItems(state);
        // TODO Also notify background activities

        return true;
    }

    protected void showMenuFor(Item item){
        Log.i(TAG, "Show menu for " + item.title);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(rootView.findViewById(R.id.bottom_sheet));

        // behavior.

        // Finally show the menu
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        System.out.println("More button clicked for " + item.title);

    }

}
