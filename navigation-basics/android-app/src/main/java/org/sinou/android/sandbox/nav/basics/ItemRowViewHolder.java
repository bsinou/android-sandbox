package org.sinou.android.sandbox.nav.basics;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * Manage a single row.
 */
public class ItemRowViewHolder extends RecyclerView.ViewHolder {

    private final static String TAG = "ItemRowVH";
    private Item item;

    private final TextView titleView;
    private final TextView descView;
    private final ImageView moreBtn;

    private NavController navController;

    public ItemRowViewHolder(@NonNull NavController navController, View v) {
        super(v);

        this.navController = navController;

        // Define click listener for the ViewHolder's View.
        v.setOnClickListener(new ItemClickedListener());
        titleView = (TextView) v.findViewById(R.id.listItemTitle);
        descView = (TextView) v.findViewById(R.id.listItemDesc);
        moreBtn = (ImageView) v.findViewById(R.id.listItemMore);
        moreBtn.setOnClickListener(new MoreClickedListener());
    }

    public void setData(Item item) {
        this.item = item;
        titleView.setText(item.title);
        //descView.setText();
    }

    private class ItemClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            System.out.println("Navigate to " + item.title);
            navController.navigate(R.id.recycler_fragment);
            // intent.setClass(App.context(), BrowserActivity.class);
            // intent.putExtra(GuiNames.EXTRA_STATE, (new State(parentState.getAccountID(), fileNode)).toString());
            // App.context().startActivity(intent);
        }
    }

    private class MoreClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            RecyclerFragment fragment = (RecyclerFragment) FragmentManager.findFragment(v);
            fragment.showMenuFor(item);

            // navController.navigate(R.id.recycler_fragment);
            // intent.setClass(App.context(), BrowserActivity.class);
            // intent.putExtra(GuiNames.EXTRA_STATE, (new State(parentState.getAccountID(), fileNode)).toString());
            // App.context().startActivity(intent);
        }
    }


}
