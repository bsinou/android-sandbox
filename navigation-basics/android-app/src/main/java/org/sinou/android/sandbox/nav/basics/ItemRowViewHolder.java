package org.sinou.android.sandbox.nav.basics;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Manage a single row.
 */
public class ItemRowViewHolder extends RecyclerView.ViewHolder {

    private final static String TAG = "ItemRowVH";
    private Item item;

    private final TextView titleView;
    private final TextView descView;

    private NavController navController;

    public ItemRowViewHolder(@NonNull NavController navController, View v) {
        super(v);

        this.navController = navController;

        // Define click listener for the ViewHolder's View.
        v.setOnClickListener(new ItemClickedListener());
        titleView = (TextView) v.findViewById(R.id.listItemTitle);
        descView = (TextView) v.findViewById(R.id.listItemDesc);
    }

    public void setData(Item item) {
        this.item = item;
        titleView.setText(item.title);
        //descView.setText();
    }

    private class ItemClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            System.out.println("Navigate to "+ item.title);
            navController.navigate(R.id.recycler_fragment);
            // intent.setClass(App.context(), BrowserActivity.class);
            // intent.putExtra(GuiNames.EXTRA_STATE, (new State(parentState.getAccountID(), fileNode)).toString());
            // App.context().startActivity(intent);
        }
    }


}
